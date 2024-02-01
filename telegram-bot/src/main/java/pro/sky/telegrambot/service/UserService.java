package pro.sky.telegrambot.service;

import pro.sky.telegrambot.model.User;

import java.util.List;

public interface UserService {
    /**
     * Создание и сохранение пользователя в бд
     *
     * @param user Пользователь для сохранения в бд
     * @return Сохранённый пользователь
     */
    User create(User user);
    /**
     * Получение пользователя по id
     *
     * @param id Id пользователя
     * @return Полученный из бд пользователь
     */
    User getById(Long id);
    /**
     * Изменение пользователя
     *
     * @param user пользователь
     * @return Изменённый пользователь
     */
    User update(User user);
    /**
     * @param user Пользователь, который уже есть в бд
     */
    void delete(User user);
    /**
     * Удаление пользователя по id
     *
     * @param id Id пользователя
     */
    void deleteById(Long id);
    /**
     * @return Список всех пользователей
     */
    List<User> getAll();

}
