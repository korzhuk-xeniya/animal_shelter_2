package pro.sky.telegrambot.service;

import com.pengrad.telegrambot.model.Update;
import pro.sky.telegrambot.model.Animal;

import java.util.List;

public interface AnimalService {

    Animal add(Animal animal);

    Object get(long id);

//    Animal update(Long id, Animal animal);
//
//    void delete(long id);

    Animal update(Long id, Animal animal);

    void delete(long id);

    List<Animal> allAnimals();

    /**
     * Поиск пользователя по chatId, если он есть то добавляем к животному
     */
    void saveUserIdInAnimal(Update update, Animal animal);
}
