package pro.sky.telegrambot.service;

import pro.sky.telegrambot.model.Animal;

import java.util.List;
import java.util.UUID;

public interface AnimalService {

    Animal add(Animal animal);

    Animal get(UUID id);


    List<Animal> allAnimals();

    Animal update(UUID id, Animal animal);

    void delete(UUID id);
}
