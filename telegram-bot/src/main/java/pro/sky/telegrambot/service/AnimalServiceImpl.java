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
    private  AnimalRepository animalRepository;
@Autowired
    private  UserService userService;
//        private final User user;




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
    public Animal get(long id) {
        return (Animal) animalRepository.findById(id).orElse(null);

    }

    /**
     * Метод обновления ифнормации о животном.
     */
  @Override
    public Animal update(Long id, Animal animal) {
//             создается новый объект животного.
//             передаётся ему ID существующего животного, которого необходимо отредактировать
//             передаются новые параметры для животного
//            Animal savedAnimal = new Animal(id, animal.getAgeMonth(), animal.getNameOfAnimal(), animal.getPhotoLink(),
  //                        animal.getGender(), animal.getPetType());
 //            return animalRepository.save(savedAnimal);
    return animal ;

  }
    /**
     * Метод удаления животного
     */
    @Override
    public void delete(long id) {

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
            //          Animal pet = new Animal(animal.getId(), animal.getAgeMonth(), animal.getNameOfAnimal(),
            //                 animal.getPhotoLink(), animal.getGender(), animal.getPetType(),user);
            //          animalRepository.save(pet);
        }
    }

//        @Override
//        public void updateUserId(User user, long animalId) {
//            new Animal();
//            Animal animal = (animalRepository.findById(animalId).orElse(null));
//            animal.setUserId(user.getId());
//        }

//    private final AnimalRepository animalRepository;
//    private final UserRepository userRepository;
//
//
//    public AnimalServiceImpl(AnimalRepository animalRepository, UserRepository userRepository) {
//        this.animalRepository = animalRepository;
//
//        this.userRepository = userRepository;
//    }
//
//    /**
//     * Добавление нового животного.
//     */
//    @Override
//    public Animal add(Animal animal) {
//        return animalRepository.save(animal);
//    }
//
//    /**
//     * Получение информации по животному через ID
//     */
//    @Override
//    public Animal get(long id) {
//        return animalRepository.findById(id).orElse(null);
//
//    }
//
//    /**
//     * Метод обновления ифнормации о животном.
//     */
////    @Override
////    public Animal update(Long id, Animal animal) {
////        // создается новый объект животного.
////        // передаётся ему ID существующего животного, которого необходимо отредактировать
////        Animal savedAnimal = get(long);
////        if (savedAnimal == null) {
////            return null;
////        }
//        // передаются новые параметры для животного
////        savedAnimal.setAgeMonth(animal.getAgeMonth());
////        savedAnimal.setName(animal.getNameOfAnimal());
////        savedAnimal.setGender(animal.getGender());
////        savedAnimal.setPetType(animal.getPetType());
////        return animalRepository.save(animal);
////    }
//
//    /**
//     * Метод удаления животного
//     *
//     * @return
//     */
////    @Override
////    public String delete(long id) {
////        // Проверяем, что у животного есть усыновитель
////        if (get(long).getUser() != null) {
////            // Ищем чат айди усыновителя этого животного
////            long animalsParentChatId = get(java.util.UUID.randomUUID()).getUser().getChatId();
////            // Создаем усыновителя, который соответствует этому чат айди и переносим ему все данные
////            Optional<User> user = userRepository.findByChatId(animalsParentChatId);
////            // Новому усыновителю прописываем поле с животным как null
////            //user.setAnimal(null);
////            // Создаем новое животное, передаем все данные старого
////            Animal deletedAnimal = get(long);
////            // Новому животному убираем усыновителя
////            deletedAnimal.setParent(null);
////            // Перезаписываем в БД животное уже без усыновителя
////            animalRepository.save(deletedAnimal);
////
////            // Сохраняем нового усыновителя, перезаписываем старого
////            UserRepository.save(Optional.of(user));
////
////        }
//        // Удаляем животное по его айди
////        animalRepository.deleteById(id);
//////        return "Животное удалено";
////    }
//
//    /**
//     * Метод выведения списка всех животных.
//     */
//    @Override
//    public List<Animal> allAnimals() {
//        return animalRepository.findAll();
//    }

}
