package pro.sky.telegrambot.service;
import pro.sky.telegrambot.model.Volunteer;

import java.util.List;
import java.util.UUID;

public interface VolunteerService {
    /**
     * Создание и сохранение волонтёра в бд<br>
     * Используется метод репозитория {@link pro.sky.telegrambot.repository.VolunteerRepository#save(Object)}
     *
     * @param volunteer Волонтёр для сохранения в бд, не может быть null
     * @return Сохранённый волонтёр
     */
    Volunteer create(Volunteer volunteer);

    /**
     * Получение волонтёра по id<br>
     * Используется метод репозитория {@link pro.sky.telegrambot.repository.VolunteerRepository#findById(Object)}
     *
     * @param id Id волонтёра, не может быть null
     * @return Полученный из бд волонтёр
     * @throws pro.sky.telegrambot.exceptions.NotFoundException Если в базе нет волонтёра с указанным id
     */
    Volunteer getById(UUID id);

    /**
     * Получение волонтёра по id<br>
     * Используется метод репозитория {@link pro.sky.telegrambot.repository.VolunteerRepository#findAll()}
     *
     * @return Список всех волонтёров
     * @throws pro.sky.telegrambot.exceptions.NotFoundException Если база с волонтёрами пустая
     */
    List<Volunteer> getAll();

    /**
     * Изменение волонтёра<br>
     * Используется метод этого же сервиса {@link VolunteerServiceImpl#getById(UUID)}
     *
     * @param volunteer Волонтёр, не может быть null
     * @return Изменённый волонтёр
     * @throws pro.sky.telegrambot.exceptions.NotFoundException Если у передаваемого волонтёра нет id или в базе нет волонтёра с указанным id
     */
    Volunteer update(Volunteer volunteer);

    /**
     * Удаление волонтёра через объект<br>
     * Используется метод этого же сервиса {@link VolunteerServiceImpl#getById(UUID)}
     *
     * @param volunteer Волонтёр, который уже есть в бд
     * @throws pro.sky.telegrambot.exceptions.NotFoundException Если в базе нет волонтёра с указанным id
     */
    void delete(Volunteer volunteer);

    /**
     * Удаление волонтёра по id<br>
     * Используется метод этого же сервиса {@link VolunteerServiceImpl#getById(UUID)}
     *
     * @param id Id волонтёра
     * @throws pro.sky.telegrambot.exceptions.NotFoundException Если в базе нет волонтёра с указанным id
     */
    void deleteById(UUID id);
}