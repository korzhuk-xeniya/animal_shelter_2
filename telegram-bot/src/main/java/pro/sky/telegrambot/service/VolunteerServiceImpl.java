package pro.sky.telegrambot.service;

import com.pengrad.telegrambot.model.Update;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import pro.sky.telegrambot.exceptions.VolunteerNotFoundException;
import pro.sky.telegrambot.model.Animal;
import pro.sky.telegrambot.model.ReportAboutAnimal;
import pro.sky.telegrambot.model.User;
import pro.sky.telegrambot.model.Volunteer;
import pro.sky.telegrambot.repository.ReportAboutAnimalRepository;
import pro.sky.telegrambot.repository.VolunteerRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class VolunteerServiceImpl implements VolunteerService {
    private final VolunteerRepository volunteerRepository;

    private final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    private final ReportAboutAnimalRepository animalReportRepository;


    public VolunteerServiceImpl(VolunteerRepository volunteerRepository, ReportAboutAnimalRepository animalReportRepository) {
        this.volunteerRepository = volunteerRepository;
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
                orElseThrow(() -> new VolunteerNotFoundException("Волонтер с id " + id + " не найден в базе данных"));
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
        public void uploadAnimalReport(
                String photo
                , String wellBeing
                , LocalDateTime dateAdded
                , Animal animal
                , User user) {
            ReportAboutAnimal animalReport = new ReportAboutAnimal();
            animalReport.setPhoto(photo);
            animalReport.setWellBeing(wellBeing);
            animalReport.setDateAdded(dateAdded);
            animalReport.setAnimal(animal);
            animalReport.setUser(user);
            this.animalReportRepository.save(animalReport);
        }
        @Override
        public ReportAboutAnimal findById(Integer id) {
            return this.animalReportRepository
                    .findById(id).orElseThrow();
        }

        @Override
        public ReportAboutAnimal save(ReportAboutAnimal report) {
            if (report != null) {
                this.animalReportRepository.save(report);
            }
            return report;
        }

        @Override
        public void remove(long id) {
            Optional<ReportAboutAnimal> byId = animalReportRepository.findById(id);
            if (byId.isPresent()) {
                this.animalReportRepository.deleteById(id);
            }
        }
        @Override
        public List<ReportAboutAnimal> getAll() {
            return this.animalReportRepository.findAll();
        }
    }


