package pro.sky.telegrambot.service;

import pro.sky.telegrambot.model.Animal;
import pro.sky.telegrambot.repository.AnimalRepository;

import java.util.Optional;
import java.util.UUID;

public class AnimalServiceImpl implements AnimalService {
    private final AnimalRepository animalRepository;

    public AnimalServiceImpl( AnimalRepository animalRepository) {
        this.animalRepository = animalRepository;
    }

    //найти животного в базе данных
    public Optional<Object> findAnimal(UUID id) {
        return animalRepository.findById(id);
    }

    //добавление животного
    public Animal addAnimal(Animal animal) {
        return animalRepository.save(animal);
    }

    // удаление животного из таблицы
    public void deleteAnimal(UUID id) {
        animalRepository.deleteById(id);
    }}
}
