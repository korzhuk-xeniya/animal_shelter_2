package pro.sky.telegrambot.service;

import com.pengrad.telegrambot.model.Update;
import pro.sky.telegrambot.model.Volunteer;

import java.util.List;
import java.util.Optional;

public interface VolunteerService {
    Volunteer saveVolunteerInBd(Volunteer volunteer);

    Volunteer readVolunteer(long id);

    Volunteer updateVolunteerById(Volunteer volunteer);

    void deleteById(long id);

    List<Volunteer> findAllVolunteers();

    Optional<Volunteer> getVolunteerByChatId(long chatId);

    /**
     * Поиск волонтера по chatId, если он есть то обновляем, если нет, создается новый волонтер
     */
    void saveVolunteer(Update update);
}
