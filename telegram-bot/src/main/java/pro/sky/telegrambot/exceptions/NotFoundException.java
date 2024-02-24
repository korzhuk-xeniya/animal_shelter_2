package pro.sky.telegrambot.exceptions;

public class NotFoundException extends RuntimeException {
    public NotFoundException() {
        super("Пользователь не найден!");
    }

}
