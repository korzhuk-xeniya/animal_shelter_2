package pro.sky.telegrambot.service;

import com.pengrad.telegrambot.model.Update;
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

import java.time.LocalDateTime;

@Service
public class ReportServiceImpl implements ReportService {

        private final UserService userService;
        private final UserRepository userRepository;
        private final ReportRepository reportRepository;
        private final Logger logger = LoggerFactory.getLogger(pro.sky.telegrambot.service.ReportServiceImpl.class);

        public ReportServiceImpl(UserService userService, UserRepository userRepository, ReportRepository reportRepository) {
            this.userService = userService;
            this.userRepository = userRepository;
            this.reportRepository = reportRepository;
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


        private String getExtensions(String fileName) {
            logger.info("Был вызван метод для получения расширения файла фотографии", fileName);
            return fileName.substring(fileName.lastIndexOf(".") + 1);
        }


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


            if (update.message().photo() != null) {
                return new SendMessage(chatId, "Фото сохранено, пришлите текстовую часть отчета.");
            } else {
                return new SendMessage(chatId, "Вы прислали не фото!");
            }

        }

        @Override
        /**
         * Проверяем, что пользователь прислал текст для отчета.
         */
        public SendMessage dailyReportCheckMessage(long chatId, Update update, String namePhotoId) {
            if (update.message().text() != null) {
                saveReportMessage(update, namePhotoId);
                return new SendMessage(chatId, "Отчет сохранен");
            } else {
                return new SendMessage(chatId, "Вы не прислали текстовую часть отчета!");
            }
        }

//

        /**
         * Сохранение текствого отчет о питомце в БД
         */
        void saveReportMessage(Update update, String namePhotoId) {
            Report report = reportRepository.findReportByPhotoNameId(namePhotoId).orElseThrow();
            report.setGeneralWellBeing(update.message().text());
            updateReport(report);
        }

        @Override
        /**
         * Сохранение отчета в БД
         */
        public void saveReportPhotoId(Update update, String namePhotoId) {
            int chatId = update.message().chat().id().intValue();
            Report report = new Report();
            report.setDateAdded(LocalDateTime.now());
            report.setPhotoNameId(namePhotoId);
            report.setGeneralWellBeing("No text provided");
            report.setCheckReport(false);
            report.setUser(userRepository.findUserByChatId(chatId));
            reportAdd(report);
        }
}
