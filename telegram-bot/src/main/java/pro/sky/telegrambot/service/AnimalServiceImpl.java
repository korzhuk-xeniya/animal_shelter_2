package pro.sky.telegrambot.service;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.Parent;
import org.springframework.stereotype.Service;
import pro.sky.telegrambot.model.Animal;
import pro.sky.telegrambot.model.User;
import pro.sky.telegrambot.repository.AnimalRepository;
import pro.sky.telegrambot.repository.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
public class AnimalServiceImpl implements AnimalService {

    private final AnimalRepository animalRepository;
    private final Animal animal;

    private java.util.UUID UUID;

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
    public Animal update(UUID id, Animal animal) {
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
        return animalRepository.save(animal);
    }

    /**
     * Метод удаления животного
     */
    @Override
    public void delete(UUID id) {
        if (get(UUID).getUser() != null) {
            long animalsUserChatId = get(UUID).getUser().getChatId();
            Optional<User> user = UserRepository.findByChatId(animalsUserChatId);
            user.setAnimal(null);
            // Создаем новое животное, передаем все данные старого
            Animal deletedAnimal = get(id);
            // Новому животному убираем усыновителя
            deletedAnimal.setUser(null);
            // Перезаписываем в БД животное уже без усыновителя
            animalRepository.save(deletedAnimal);
         
            // Сохраняем нового усыновителя, перезаписываем старого
            userRepository.save(user);
          
        }
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

