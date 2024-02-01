package pro.sky.telegrambot.service;

import org.springframework.stereotype.Service;
import pro.sky.telegrambot.model.Animal;
import pro.sky.telegrambot.repository.AnimalRepository;

import java.util.List;
import java.util.UUID;

@Service
public class AnimalServiceImpl implements AnimalService {
    private final AnimalRepository animalRepository;
    private final Animal animal;

    public AnimalServiceImpl(AnimalRepository animalRepository, Animal animal) {
        this.animalRepository = animalRepository;
        this.animal = animal;
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
    public Animal get(UUID id) {
        return animalRepository.findById(id).orElse(null);

    }

    /**
     * Метод обновления ифнормации о животном.
     */
    @Override
    public Animal update(Long id, Animal animal) {
        // создается новый объект животного.
        // передаётся ему ID существующего животного, которого необходимо отредактировать
//        Animal savedAnimal = get(id);
//        if (savedAnimal == null) {
//            return null;
//        }
//        // передаются новые параметры для животного
//        savedAnimal.setAgeMonth(animal.getAgeMonth());
//        savedAnimal.setName(animal.getName());
//        savedAnimal.setGender(animal.getGender());
//        savedAnimal.setPetType(animal.getPetType());
        return animalRepository.save(animal);
    }

    /**
     * Метод удаления животного надо доработать
     */
    @Override
    public void delete(UUID id) {

        // Удаляем животное по его айди
        animalRepository.deleteById(id);
//        return "Животное удалено";
    }

    /**
     * Метод выведения списка всех животных.
     */
    @Override
    public List<Animal> allAnimals() {
        return animalRepository.findAll();
    }

}

