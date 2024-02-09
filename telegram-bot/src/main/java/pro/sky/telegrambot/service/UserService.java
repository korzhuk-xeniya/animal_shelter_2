package pro.sky.telegrambot.service;

import com.pengrad.telegrambot.model.Update;
import pro.sky.telegrambot.model.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserService {
    /**
     * Создание и сохранение пользователя в бд
     *
     * @param user Пользователь для сохранения в бд
     * @return Сохранённый пользователь
     */
    User createUser(User user);
    /**
     * Получение пользователя по id
     *
     * @param id Id пользователя
     * @return Полученный из бд пользователь
     */



    User getById(long id);

    /**
     * Изменение пользователя
     *
     * @param user пользователь
     * @return Изменённый пользователь
     */
//    User update(User user);

    //Обновить пользователя в бд
    User updateUser(User user);

    /**
     * @param user Пользователь, который уже есть в бд
     */
    void delete(User user);
    /**
     * Удаление пользователя по id
     *
     * @param id Id пользователя
     */


    void deleteById(long id);

    /**
     * @return Список всех пользователей
     */
    List<User> getAll();



    //Получить юзера из бд
    Optional<User> getUserByChatId(long chatId);

    /**
     * Поиск пользователя по chatId, если он есть то обновляем dateTimeToTook, если нет, создается новый пользователь
     */
    void saveUser(Update update, boolean tookAPET);
}
