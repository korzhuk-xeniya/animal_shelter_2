package pro.sky.telegrambot.service;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.data.domain.Page;
import pro.sky.telegrambot.model.Report;

import java.io.IOException;
import java.util.Optional;

public interface ReportService {


    void updateReport(Report report);

    /**
     * Проверяем, что пользователь прислал фото для отчета.
     */
    SendMessage dailyReportCheckPhoto(long chatId, Update update);

    /**
     * Проверяем, что пользователь прислал текст для отчета.
     */
    SendMessage dailyReportCheckMessage(long chatId, Update update, String namePhotoId);

    /**
     * Сохранение отчета в БД
     */
    void saveReportPhotoId(Update update, String namePhotoId) throws IOException;
}
