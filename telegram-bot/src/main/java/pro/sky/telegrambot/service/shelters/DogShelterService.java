package pro.sky.telegrambot.service.shelters;

import pro.sky.telegrambot.model.animals.Dog;
import pro.sky.telegrambot.model.shelters.DogShelter;

import java.util.List;

public interface DogShelterService {
    /**
     * Сохранить собачий приют в БД
     *
     * @param shelter объект собачий приют
     * @return сохранение собачьего приюта в БД
     */

    DogShelter addShelter(DogShelter shelter);

    /**
     * Обновление данных собачьего приюта
     *
     * @param shelter объект собачий приют
     * @return shelter объект собачий приют
     */

    DogShelter updateShelter(DogShelter shelter);

    /**
     * Получение собачьего приюта по id
     *
     * @param id собачьего приюта
     * @return собачий приют
     */
    DogShelter getSheltersId(long id);

    DogShelter getShelterByName(String name);

    /**
     * Выдача списка собачьих приютов
     *
     * @return List со списком собачьих приютов
     */

    List<DogShelter> getShelter();

    /**
     * Выдача списка животных собачьих приютов
     *
     * @param index индекс собачьего приюта
     * @return List со списком собачий приютов
     */
    List<Dog> getAnimal(long index);


    /**
     * Удаление собачьего приюта
     *
     * @param index номер
     */
    String deleteShelter(long index);
}
