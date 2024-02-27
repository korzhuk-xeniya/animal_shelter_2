package pro.sky.telegrambot.service;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.request.SendPhoto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import pro.sky.telegrambot.buttons.ButtonsOfMenu;
import pro.sky.telegrambot.exceptions.VolunteerNotFoundException;
import pro.sky.telegrambot.model.Report;
import pro.sky.telegrambot.model.Animal;

import pro.sky.telegrambot.model.User;
import pro.sky.telegrambot.model.Volunteer;
import pro.sky.telegrambot.repository.ReportRepository;
import pro.sky.telegrambot.repository.VolunteerRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class VolunteerServiceImpl implements VolunteerService {
    private final VolunteerRepository volunteerRepository;

    private final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    private final ReportRepository reportRepository;
    private final ReportService reportService;
    private final ButtonsOfMenu buttons;
    private final TelegramBot telegramBot;
    private final ReportRepository animalReportRepository;

    public VolunteerServiceImpl(VolunteerRepository volunteerRepository, ReportRepository reportRepository, 
                                ReportService reportService, ButtonsOfMenu buttons, TelegramBot telegramBot, 
                                ReportRepository animalReportRepository) {
        this.volunteerRepository = volunteerRepository;
        this.reportRepository = reportRepository;
        this.reportService = reportService;
        this.buttons = buttons;
        this.telegramBot = telegramBot;
        this.animalReportRepository = animalReportRepository;
    }

    /**
     * @param volunteer
     * @return волонтера, который был добавлен в базу данных
     * <p>
     * добавление нового волонтера
     */
    @Override
    public Volunteer saveVolunteerInBd(Volunteer volunteer) {
        logger.info("Был вызван метод для добавления нового волонтера в базу данных", volunteer);

        return volunteerRepository.save(volunteer);
    }

    /**
     * @param id
     * @return волонтера по id
     */
    @Override
    public Volunteer readVolunteer(long id) {
        logger.error("Был вызван метод для выбрасывания ошибки если волонтер не найден по id", id);
        return (Volunteer) volunteerRepository.findById(id).
                orElseThrow(() -> new VolunteerNotFoundException(id));
    }

    /**
     * @param volunteer
     * @return волонтера с обновленными данными
     */
    @Override
    public Volunteer updateVolunteerById(Volunteer volunteer) {
        logger.info("Был вызван метод для обновления волонтера", volunteer);
        readVolunteer(volunteer.getId());
        return volunteerRepository.save(volunteer);
    }

    /**
     * @param id удаление волонтера из базы данных
     */
    @Override
    public void deleteById(long id) {
        logger.info("Был вызван метод для удаления волонтера", id);
        volunteerRepository.deleteById(id);
    }


    /**
     * @return список всех волонтеров
     * <p>
     * ищет всех волонтеров
     */
    @Override

    public List<Volunteer> findAllVolunteers() {
        logger.info("Был вызван метод для поиска всех волонтеров");
        return volunteerRepository.findAll().stream().toList();
    }

    /**
     * @param chatId
     * @return получение волонтера из базы данных
     */
    @Override

    public Optional<Volunteer> getVolunteerByChatId(long chatId) {
        logger.info("Был вызван метод для получения пользователя из базы данных", chatId);
        return volunteerRepository.findByChatId(chatId);
    }

    @Override
    /**
     * Поиск волонтера по chatId, если он есть то обновляем, если нет, создается новый волонтер
     */
    public void saveVolunteer(Update update) {
        logger.info("Был вызван метод для сохранения волонтера в базу данных", update);
        int chatId = update.message().chat().id().intValue();
        Optional<Volunteer> volunteerOptional = getVolunteerByChatId(chatId);

        if (volunteerOptional.isPresent()) {
            logger.info("Волонтер уже есть в базе данных");
            Volunteer volunteer = volunteerOptional.get();

            updateVolunteerById(volunteer);
        } else {
            Volunteer newVolunteer = new Volunteer(update.message().from().firstName(), update.message().from().lastName(),
                    chatId);
            saveVolunteerInBd(newVolunteer);
        }
    }

    @Override
    /**
     * Обновляем в БД отчет и ставим, что отчет сдан
     */
    public void reportSubmitted(Long idReport) {
        Report report = reportRepository.findById(idReport).orElse(null);
        report.setCheckReport(true);
        reportService.updateReport(report);
    }

    @Override
    /**
     * Позволяет распарсить SendMessage из метода reviewListOfReports что-бы достать ID репорта
     * с которым будем работать.
     *
     * @param reportString получаем строку SendMessage
     * @return вовзращает ID отчета
     */
    public int parseReportNumber(String reportString) {
        Pattern pattern = Pattern.compile("Отчет #(\\d+)");
        Matcher matcher = pattern.matcher(reportString);

        if (matcher.find()) {
            String numberStr = matcher.group(1);
            return Integer.parseInt(numberStr);
        }
        return -1; // В случае, если не удалось извлечь номер отчета
    }

    @Override
    /**
     * Получаем непроверенный отчет из всех отчетов
     * @return возвращает первый непроверенный отчет и кнопки действия с отчетом
     */
    public long reviewListOfReports(Long chatId) {
        List<Report> reportList = reportRepository.findReportByCheckReportIsFalse();

        if (reportList.isEmpty()) {
            SendMessage noReportsMessage = new SendMessage(chatId, "Нет непроверенных отчетов.");
            telegramBot.execute(noReportsMessage);


        } else {
            long reportId = reportList.get(0).getId();
            Report report = reportList.get(0);

            String reportInfo = "Отчет #" + report.getId() + "\n" +
                    "Текстовая часть отчета: " + report.getGeneralWellBeing();
            SendMessage reportMessage = new SendMessage(chatId, reportInfo);
            SendPhoto sendPhoto = new SendPhoto(chatId,report.getPhoto() );

            telegramBot.execute(sendPhoto);
            reportMessage.replyMarkup(buttons.buttonsOfVolunteerForReports());
            telegramBot.execute(reportMessage);
            return reportId;


        }
        return -1;
    }





        @Override
        public void uploadAnimalReport(
                byte[] photo
                , String wellBeing
                , LocalDateTime dateAdded
                , Animal animal
                , User user) {
            Report animalReport = new Report();
            animalReport.setPhoto(photo);
            animalReport.setGeneralWellBeing(wellBeing);
            animalReport.setDateAdded(dateAdded);
            animalReport.setUser(user);
            this.animalReportRepository.save(animalReport);
        }
        @Override
        public Report findById(Integer id) {
            return this.animalReportRepository
                    .findById(id).orElseThrow();
        }

        @Override
        public Report save(Report report) {
            if (report != null) {
                this.animalReportRepository.save(report);
            }
            return report;
        }

        @Override
        public void remove(long id) {
            Optional<Report> byId = animalReportRepository.findById(id);
            if (byId.isPresent()) {
                this.animalReportRepository.deleteById(id);
            }
        }
        @Override
        public List<Report> getAll() {
            return this.animalReportRepository.findAll();
        }
    }


