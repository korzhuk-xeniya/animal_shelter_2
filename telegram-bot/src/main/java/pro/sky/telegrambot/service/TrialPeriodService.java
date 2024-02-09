package pro.sky.telegrambot.service;

import pro.sky.telegrambot.model.TrialPeriod;

import java.util.List;
import java.util.UUID;

public interface TrialPeriodService {
    /**
     * Сохранение испытательного периода в бд <br>
     * Используется метод репозитория {@link pro.sky.telegrambot.repository.TrialPeriodRepository#save(Object)}
     *
     * @param trialPeriod Испытательный срок для сохранения в бд
     * @return Созданный испытательный срок
     */
    TrialPeriod create(TrialPeriod trialPeriod);

    /**
     * Сохранение испытательного периода в бд при создании владельца<br>
     * Используется метод репозитория {@link pro.sky.telegrambot.repository.TrialPeriodRepository#save(Object)}
     *
     * @param trialPeriod Испытательный срок для сохранения в бд
     * @param Animal.getPetType()  Тип животного
     * @return Созданный испытательный срок
     */

    TrialPeriod getById(UUID id);

    /**
     * Получение всех отчётов<br>
     * Используется метод репозитория {@link pro.sky.telegrambot.repository.TrialPeriodRepository#findAll()}
     *
     * @return Список всех отчётов
     * @throws pro.sky.telegrambot.exceptions.NotFoundException Если база с испытательными сроками пустая
     */
    List<TrialPeriod> getAll();

    /**
     * Получение всех отчётов конкретного пользователя<br>
     * Используется метод репозитория {@link pro.sky.telegrambot.repository.TrialPeriodRepository#findAllByOwnerId(UUID)}
     *
     * @param ownerId id хозяина, по которому будет идти поиск
     * @return Список испытательных сроков по хозяину
     * @throws pro.sky.telegrambot.exceptions.NotFoundException Если у пользователя отсутствуют испытательные сроки
     */
    List<TrialPeriod> getAllByOwnerId(UUID ownerId);

    /**
     * Изменение существующего испытательного срока<br>
     * Используется метод этого же сервиса {@link TrialPeriodServiceImpl#getById(UUID)}
     *
     * @param trialPeriod Испытательный срок
     * @return Обновлённый испытательный срок
     * @throws pro.sky.telegrambot.exceptions.NotFoundException Если у передаваемого испытательного срока нет id или в базе нет испытательного срока с указанным id
     */
    TrialPeriod update(TrialPeriod trialPeriod);

    /**
     * Удаление полученного из бд испытательного срока<br>
     * Используется метод этого же сервиса {@link TrialPeriodServiceImpl#getById(UUID)}
     *
     * @param trialPeriod Испытательный срок, который уже есть в бд
     * @throws pro.sky.telegrambot.exceptions.NotFoundException Если в базе нет испытательного срока с указанным id
     */
    void delete(TrialPeriod trialPeriod);

    /**
     * Удаление испытательного срока по id<br>
     * Используется метод этого же сервиса {@link TrialPeriodServiceImpl#getById(UUID)}
     *
     * @param id id испытательного срока
     * @throws pro.sky.telegrambot.exceptions.NotFoundException Если в базе нет испытательного срока с указанным id
     */
    void deleteById(UUID id);
}
