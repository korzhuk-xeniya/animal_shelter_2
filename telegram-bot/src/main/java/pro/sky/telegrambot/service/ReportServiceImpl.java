package pro.sky.telegrambot.service;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.File;
import com.pengrad.telegrambot.model.PhotoSize;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.GetFile;
import com.pengrad.telegrambot.request.SendMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pro.sky.telegrambot.model.Report;
import pro.sky.telegrambot.repository.ReportRepository;
import pro.sky.telegrambot.repository.UserRepository;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

@Service
public class ReportServiceImpl implements ReportService {

    private final UserService userService;
    private final UserRepository userRepository;
    private final ReportRepository reportRepository;
    private final Logger logger = LoggerFactory.getLogger(pro.sky.telegrambot.service.ReportServiceImpl.class);
    private final TelegramBot telegramBot;

    public ReportServiceImpl(UserService userService, UserRepository userRepository, ReportRepository reportRepository, TelegramBot telegramBot) {
        this.userService = userService;
        this.userRepository = userRepository;
        this.reportRepository = reportRepository;
        this.telegramBot = telegramBot;
    }


    public Report reportAdd(Report report) {
        return reportRepository.save(report);
    }

    @Override
    public void updateReport(Report report) {
        reportRepository.save(report);
    }

    @Override
    public Report findReport(long userId) {
        logger.info("Был вызван метод для поиска отчета по id пользователя", userId);
        return reportRepository.findById(userId)
                .orElse(new Report());
    }


//        private String getExtensions(String fileName) {
//            logger.info("Был вызван метод для получения расширения файла фотографии", fileName);
//            return fileName.substring(fileName.lastIndexOf(".") + 1);
//        }


    @Override
    public Page<Report> getAllReports(Integer pageNo, Integer pageSize) {
        logger.info("Был вызван метод для получения всех отчетов");
        Pageable paging = PageRequest.of(pageNo, pageSize);
        return reportRepository.findAll(paging);
    }

    @Override
    /**
     * Запрашива у пользователя фото
     */
    public SendMessage sendMessageDailyReportPhoto(long chatId) {
        SendMessage sendMessage = new SendMessage(chatId, "Пришлите фото питомца");

        return sendMessage;
    }

    @Override
    /**
     * Проверяем, что пользователь прислал фото для отчета.
     */
    public SendMessage dailyReportCheckPhoto(long chatId, Update update) {
        logger.info("Был вызван метод для отправки подтверждющего сообщения пользователю Фото сохранено");
            SendMessage sendMessage = new SendMessage(chatId, "Фото сохранено.");
            telegramBot.execute(sendMessage);
            return sendMessage;
    }

    @Override
    /**
     * Проверяем, что пользователь прислал текст для отчета.
     */
    public SendMessage dailyReportCheckMessage(long chatId, Update update, String namePhotoId) {
        logger.info("Был вызван метод для сохранения текстовой части отчета и " +
                "отправки подтверждющего сообщения пользователю Отчет сохранен");

        saveReportMessage(update, namePhotoId);
        SendMessage sendMessage = new SendMessage(chatId, "Отчет сохранен");
        telegramBot.execute(sendMessage);
        return sendMessage;
    }

//

    /**
     * Сохранение текствого отчет о питомце в БД
     */
    void saveReportMessage(Update update, String namePhotoId) {
        logger.info("Был вызван метод сохранения текстовой части отчета");
        Report report = reportRepository.findReportByPhotoNameId(namePhotoId).orElseThrow();
        report.setGeneralWellBeing(update.message().caption());
        updateReport(report);
    }
    public PhotoSize getPhoto(Update update) {
        logger.info("Был вызван метод для поиска самой большой фотографии", update);
        if (!(update.message() == null) && !(update.message().photo() == null)) {
            List<PhotoSize> photos = Arrays.stream(update.message().photo()).toList();
            return photos.stream().max(Comparator.comparing(PhotoSize::fileSize)).orElse(null);


        }
        return null;
    }
    private File downloadPhoto(String fileId) {
        logger.info("Был вызван метод для получения файла по его id", fileId);
        GetFile getFile = new GetFile(fileId);
        return telegramBot.execute(getFile).file();


    }
    @Override
    /**
     * Сохранение отчета в БД
     */
    public void saveReportPhotoId(Update update, String namePhotoId) throws IOException {
        logger.info("Был вызван метод для сохранения фото в бд без текста ");
        int chatId = update.message().chat().id().intValue();
        Report report = new Report();
        report.setDateAdded(LocalDateTime.now());
        report.setPhotoNameId(namePhotoId);
        report.setPhoto(telegramBot.getFileContent(downloadPhoto(getPhoto(update).fileId())));
        report.setGeneralWellBeing("No text provided");
        report.setCheckReport(false);
        report.setUser(userRepository.findUserByChatId(chatId));
        reportAdd(report);
    }
}
