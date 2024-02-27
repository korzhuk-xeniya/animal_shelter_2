package pro.sky.telegrambot.service;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import pro.sky.telegrambot.model.Animal;
import pro.sky.telegrambot.model.Report;
import pro.sky.telegrambot.model.User;
import pro.sky.telegrambot.model.Volunteer;

import java.time.LocalDateTime;
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

    /**
     * Обновляем в БД отчет и ставим, что отчет сдан
     */
    void reportSubmitted(Long idReport);

    /**
     * Позволяет распарсить SendMessage из метода reviewListOfReports что-бы достать ID репорта
     * с которым будем работать.
     *
     * @param reportString получаем строку SendMessage
     * @return вовзращает ID отчета
     */
    int parseReportNumber(String reportString);

    /**
     * Получаем непроверенный отчет из всех отчетов
     *
     * @return возвращает первый непроверенный отчет и кнопки действия с отчетом
     */
    long reviewListOfReports(Long chatIdOfVolunteer);


    /**
     * Загрузка отчета по определенным параметрам.
     *
     * @param photo      фото животного
     * @param wellBeing  здоровье
     * @param dateAdded дата создания
     * @param animal     животное взятое из приюта
     * @param user   хозяин приютившего животное
     */


    void uploadAnimalReport(
            byte[] photo
            , String wellBeing
            , LocalDateTime dateAdded
            , Animal animal
            , User user);

    /**
     * Поиск отчета по id.
     *
     * @param id идентификатор
     * @return {@link pro.sky.telegrambot.repository.ReportRepository#findById(Object)}
     */
    Report  findById(Integer id);

    /**
     * Метод для сохранения отчета.
     *
     * @param report отчет
     * @return {@link pro.sky.telegrambot.repository.ReportRepository#save(Object)}
     */
    Report save(Report  report);

    /**
     * Метод для удаления отчета по идентификатору.
     *
     * @param id идентификатор
     */
    void remove(long id);

    /**
     * Метод для получения всех отчетов.
     *
     * @return {@link pro.sky.telegrambot.repository.ReportRepository#findAll()}
     */
    List<Report > getAll();
}
