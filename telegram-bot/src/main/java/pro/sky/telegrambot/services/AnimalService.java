package pro.sky.telegrambot.services;

import org.springframework.stereotype.Service;
import pro.sky.telegrambot.model.Animal;
import pro.sky.telegrambot.repository.AnimalRepository;

import java.util.Optional;

@Service
public class AnimalService {
    private final AnimalRepository animalRepository;

    public AnimalService( AnimalRepository animalRepository) {
        this.animalRepository = animalRepository;
    }

    //найти животного в базе данных
    public Optional<Object> findAnimal(Long id) {
        return animalRepository.findById(id);
    }

    //добавление животного
    public Animal addAnimal(Animal animal) {
        return animalRepository.save(animal);
    }

    // удаление животного из таблицы
    public void deleteAnimal(Long id) {
        animalRepository.deleteById(id);
    }}