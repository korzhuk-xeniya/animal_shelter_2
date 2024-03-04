package pro.sky.telegrambot.service;

import com.pengrad.telegrambot.model.Update;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pro.sky.telegrambot.model.Animal;
import pro.sky.telegrambot.model.User;
import pro.sky.telegrambot.repository.AnimalRepository;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@NoArgsConstructor
@Slf4j
public class AnimalServiceImpl implements AnimalService {

    @Autowired
    private AnimalRepository animalRepository;
    @Autowired
    private UserService userService;


    /**
     * Метод выведения списка всех животных.
     */
    @Override
    public List<Animal> allAnimals() {
        return animalRepository.findAll();
    }

    @Override
    /**
     * Поиск пользователя по chatId, если он есть то добавляем к животному
     */
    public void saveUserIdInAnimal(Update update, Animal animal) {
        log.info("Был вызван метод для усыновителя животного в базе данных{}{}", update, animal);
        int chatId = update.message().chat().id().intValue();
        Optional<User> userOptional = userService.getUserByChatId(chatId);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            Animal pet = new Animal(animal.getId(), animal.getAgeMonth(), animal.getNameOfAnimal(),
                    animal.getPhotoLink(), animal.getGender(), animal.getPetType(), user);
            animalRepository.save(pet);
        }

    }
}