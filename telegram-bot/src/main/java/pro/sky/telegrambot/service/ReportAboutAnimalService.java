package pro.sky.telegrambot.service;

import pro.sky.telegrambot.model.ReportAboutAnimal;
import pro.sky.telegrambot.model.TrialPeriod;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ReportAboutAnimalService {
        /**
         * Сохранение отчёта в базе данных (Он же отвечает за обновление уже существующего отчёта)<br>
         * Используется метод репозитория {@link pro.sky.telegrambot.repository.ReportAboutAnimalRepository#save(Object)}
         *
         * @param report Отчёт для сохранения в базе данных
         * @return Созданный отчёт
         */
        ReportAboutAnimal create(ReportAboutAnimal report);

        /**
         * Получение отчёта из базы данных по id<br>
         * Используется метод репозитория {@link pro.sky.telegrambot.repository.ReportAboutAnimalRepository#findById(Object)}
         *
         * @param id id отчёта
         * @return Отчёт
         * @throws pro.sky.telegrambot.exceptions.NotFoundException Если в базе нет отчёта с указанным id
         */
        ReportAboutAnimal getById(UUID id);

        /**
         * Получение отчёта из базы данных по дате<br>
         * Используется метод репозитория {@link pro.sky.telegrambot.repository.ReportAboutAnimalRepository#findByReceiveDateAndTrialPeriodId(LocalDate, UUID)}
         *
         * @param date Дата создания отчёта
         * @param id   id Испытательного срока
         * @return Отчёт
         * @throws pro.sky.telegrambot.exceptions.NotFoundException Если в базе нет отчёта с указанным id и датой
         */
        ReportAboutAnimal getByDateAndTrialId(LocalDate date, UUID id);

        /**
         * Получение всех отчётов<br>
         * Используется метод репозитория {@link pro.sky.telegrambot.repository.ReportAboutAnimalRepository#findAll()}
         *
         * @return Список всех отчётов
         * @throws pro.sky.telegrambot.exceptions.NotFoundException Если база с отчётами пустая
         */
        List<ReportAboutAnimal> getAll();

        /**
         * Получение всех отчётов по id испытательного срока<br>
         * Используется метод репозитория {@link pro.sky.telegrambot.repository.ReportAboutAnimalRepository#findAllByTrialPeriodId(UUID)}
         *
         * @param id id испытательного срока из базы данных
         * @return Список отчётов по испытательному сроку
         * @throws pro.sky.telegrambot.exceptions.NotFoundException Если в базе нет отчётов по указанному id испытательного срока
         */
        List<ReportAboutAnimal> gelAllByTrialPeriodId(UUID id);

        /**
         * Изменение существующего отчёта<br>
         * Используется метод этого же сервиса {@link ReportAboutAnimalServiceImpl#getById(UUID)}
         *
         * @param report Отчёт
         * @return Изменённый отчёт
         * @throws pro.sky.telegrambot.exceptions.NotFoundException Если у передаваемого отчёта нет id или в базе нет отчёта с указанным id
         */
        ReportAboutAnimal update(ReportAboutAnimal report);

        /**
         * Удаление полученного из базы данных отчёта<br>
         * Используется метод этого же сервиса {@link ReportAboutAnimalServiceImpl#getById(UUID)}
         *
         * @param report Отчёт, который уже есть в бд
         * @throws pro.sky.telegrambot.exceptions.NotFoundException Если в базе нет отчёта с указанным id
         */
        void delete(ReportAboutAnimal report);

        /**
         * Удаление отчёта по id<br>
         * Используется метод этого же сервиса {@link ReportAboutAnimalServiceImpl#getById(UUID)}
         *
         * @param id id отчёта
         * @throws pro.sky.telegrambot.exceptions.NotFoundException Если в базе нет отчёта с указанным id
         */
        void deleteById(UUID id);

        /**
         * Создание отчёта по данным из телеграма<br>
         * Используются методы из сервиса испытательных сроков {@link TrialPeriodService#getAllByOwnerId(Long)} и
         * {@link TrialPeriodService#update(TrialPeriod)}
         * Так же методы этого же сервиса {@link ReportAboutAnimalServiceImpl#create(ReportAboutAnimal)}
         *
         * @param photoLink Id фотографии из чата в телеграме
         * @param caption Описание под фотографией
         * @param id      id пользователя
         * @return Созданный отчёт
         * @throws IllegalArgumentException Если описание пустое или равно null, а так же, если формат данных не совпадает с regEx
         * @throws pro.sky.telegrambot.exceptions.AlreadyExistsException  Если отчёт уже отправляли в этот день
         */
        ReportAboutAnimal createFromTelegram(String photoLink, String caption, UUID id);
    }