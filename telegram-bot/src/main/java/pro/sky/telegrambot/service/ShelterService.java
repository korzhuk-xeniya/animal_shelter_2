package pro.sky.telegrambot.service;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;

public interface ShelterService {
    void process(Update update);

    void sendMessage(Long chatId, String messageText);

    void sendMenuButton(Long chatId, String messageText);

    void sendMenuVolunteer(Long chatId, String messageText);

    void sendButtonsOfStep0(Long chatId, String messageText);

    /**
     * метод для изменения сообщения
     */
    void changeMessage(int messageId, long chatIdInButton, String messageText, InlineKeyboardMarkup keyboardMarkup);

    /**
     * @param update Реализация кнопки "Позвать волонтера"
     */
    void callAVolunteer(Update update);
}
