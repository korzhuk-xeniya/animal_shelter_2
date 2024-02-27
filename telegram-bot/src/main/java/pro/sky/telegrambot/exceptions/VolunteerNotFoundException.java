package pro.sky.telegrambot.exceptions;

public class VolunteerNotFoundException extends RuntimeException {
    public VolunteerNotFoundException(long id) {
        super("Волонтер с id " + id + " не найден в базе данных");
    }
}
