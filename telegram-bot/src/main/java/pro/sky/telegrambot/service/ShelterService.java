package pro.sky.telegrambot.service;

import com.pengrad.telegrambot.model.CallbackQuery;
import com.pengrad.telegrambot.model.PhotoSize;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;

import java.io.IOException;
import java.util.Map;

public interface ShelterService {
    void process(Update update) throws IOException;

    void sendMessage(Long chatId, String messageText);

    void sendMenuButton(Long chatId, String messageText);

    void sendMenuVolunteer(Long chatId, String messageText);


    /**
     * метод для изменения сообщения
     */
    void changeMessage(int messageId, long chatIdInButton, String messageText, InlineKeyboardMarkup
            keyboardMarkup);

    void changeMessage(long chatIdInButton, String messageText);

    /**
     * @param callbackQuery Реализация кнопки "Позвать волонтера"
     */
    void callAVolunteer(CallbackQuery callbackQuery);

    void sendMessageByKey(long chatId, int messageId, Map<String, String> infoMap, String key,
                          InlineKeyboardMarkup keyboardMarkup);

    Map<String, String> getInfo();

    void callAVolunteerForBadReports(Long chatIdOfUser);

    void callAVolunteerForEndPeriod(Long chatIdOfUser);

    void sendButtonChooseAnimal(Long chatId, String messageText);

    void sendButtonOfVolunteerForEnd30DaysPeriod(Long chatId, String messageText);

    /**
     * Извлекает из update список объектов PhotoSize, которые представляют разный размер фотографий
     * Через стрим ищет самую большую фотографию и возвращает её.
     */
    PhotoSize getPhoto(Update update);

    //просмотр отчетов питомцев
    void reviewListOfReports(long chatId);
}
