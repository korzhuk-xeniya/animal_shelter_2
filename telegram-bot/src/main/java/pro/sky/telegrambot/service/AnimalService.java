package pro.sky.telegrambot.service;

import pro.sky.telegrambot.model.Animal;
import pro.sky.telegrambot.repository.AnimalRepository;

import java.util.List;
import java.util.UUID;

public interface AnimalService {

    Animal add(Animal animal);

    Animal get(UUID id);

    Animal update(Long id, Animal animal);


    String delete(UUID id);

    List<Animal> allAnimals();
}
