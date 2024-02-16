package pro.sky.telegrambot.service.shelters;
import pro.sky.telegrambot.model.animals.Cat;
import pro.sky.telegrambot.model.shelters.CatShelter;

import java.util.List;
public interface CatShelterService {

        /**
         * Сохранить кошачий приют в БД
         *
         * @param shelter объект кошачий приют
         * @return сохранение кошачьего приюта в БД
         */

        CatShelter addShelter(CatShelter shelter);

        /**
         * Обновление данных кошачьего приюта
         *
         * @param shelter объект кошачий приют
         * @return shelter объект кошачий приют
         */

         CatShelter updateShelter(CatShelter shelter);

        /**
         * Получение кошачьего приюта по id
         *
         * @param id кошачьего приюта
         * @return кошачий приют
         */
        CatShelter getSheltersId(long id);

        CatShelter getShelterByName(String name);

        /**
         * Выдача списка кошачьих приютов
         *
         * @return List со списком кошачьих приютов
         */

        List<CatShelter> getShelter();

        /**
         * Выдача списка животных кошачьих приютов
         *
         * @param index индекс кошачьего приюта
         * @return List со списком кошачий приютов
         */
        List<Cat> getAnimal(long index);


        /**
         * Удаление кошачьего приюта
         *
         * @param index номер
         */
        String deleteShelter(long index);
    }

