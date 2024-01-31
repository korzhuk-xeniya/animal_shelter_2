package pro.sky.telegrambot.service;

import pro.sky.telegrambot.model.Animal;
import pro.sky.telegrambot.repository.AnimalRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class AnimalServiceImpl implements AnimalService {
    private final AnimalRepository animalRepository;


    public AnimalServiceImpl(AnimalRepository animalRepository) {
        this.animalRepository = animalRepository;

    }

    /**
     * Добавление нового животного.

     */
    @Override
    public Animal add(Animal animal) {
        return animalRepository.save(animal);
    }

    /**
     * Получение информации по животному через ID
     */
    @Override
    public Animal get(Long id) {
        return animalRepository.findById(id).orElse(null);

    }

    /**
     * Метод обновления ифнормации о животном.
     */
    @Override
    public Animal update(Long id, Animal animal) {
        // создается новый объект животного.
        // передаётся ему ID существующего животного, которого необходимо отредактировать
        Animal savedAnimal = get(id);
        if (savedAnimal == null) {
            return null;
        }
        // передаются новые параметры для животного
        savedAnimal.setAgeMonth(animal.getAgeMonth());
        savedAnimal.setName(animal.getName());
        savedAnimal.setGender(animal.getGender());
        savedAnimal.setPetType(animal.getPetType());
        return animalRepository.save(savedAnimal);
    }

    /**
     * Метод удаления животного надо доработать
     */
    @Override
    public String delete(Long id) {


        // Удаляем животное по его айди
        animalRepository.deleteById(id);
        return "Животное удалено";
    }

    /**
     * Метод выведения списка всех животных.
     */
    @Override
    public List<Animal> allAnimals() {
        return animalRepository.findAll();
    }

}

