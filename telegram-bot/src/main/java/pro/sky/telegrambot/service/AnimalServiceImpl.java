package pro.sky.telegrambot.service;

import org.springframework.stereotype.Service;
import pro.sky.telegrambot.model.Animal;
import pro.sky.telegrambot.model.User;
import pro.sky.telegrambot.repository.AnimalRepository;
import pro.sky.telegrambot.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class AnimalServiceImpl implements AnimalService {

    private final AnimalRepository animalRepository;
    private final UserRepository userRepository;


    public AnimalServiceImpl(AnimalRepository animalRepository, UserRepository userRepository) {
        this.animalRepository = animalRepository;

        this.userRepository = userRepository;
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
    public Animal get(long id) {
        return animalRepository.findById(id).orElse(null);

    }

    /**
     * Метод обновления ифнормации о животном.
     */
//    @Override
//    public Animal update(Long id, Animal animal) {
//        // создается новый объект животного.
//        // передаётся ему ID существующего животного, которого необходимо отредактировать
//        Animal savedAnimal = get(long);
//        if (savedAnimal == null) {
//            return null;
//        }
        // передаются новые параметры для животного
//        savedAnimal.setAgeMonth(animal.getAgeMonth());
//        savedAnimal.setName(animal.getNameOfAnimal());
//        savedAnimal.setGender(animal.getGender());
//        savedAnimal.setPetType(animal.getPetType());
//        return animalRepository.save(animal);
//    }

    /**
     * Метод удаления животного
     *
     * @return
     */
//    @Override
//    public String delete(long id) {
//        // Проверяем, что у животного есть усыновитель
//        if (get(long).getUser() != null) {
//            // Ищем чат айди усыновителя этого животного
//            long animalsParentChatId = get(java.util.UUID.randomUUID()).getUser().getChatId();
//            // Создаем усыновителя, который соответствует этому чат айди и переносим ему все данные
//            Optional<User> user = userRepository.findByChatId(animalsParentChatId);
//            // Новому усыновителю прописываем поле с животным как null
//            //user.setAnimal(null);
//            // Создаем новое животное, передаем все данные старого
//            Animal deletedAnimal = get(long);
//            // Новому животному убираем усыновителя
//            deletedAnimal.setParent(null);
//            // Перезаписываем в БД животное уже без усыновителя
//            animalRepository.save(deletedAnimal);
//
//            // Сохраняем нового усыновителя, перезаписываем старого
//            UserRepository.save(Optional.of(user));
//
//        }
        // Удаляем животное по его айди
//        animalRepository.deleteById(id);
////        return "Животное удалено";
//    }

    /**
     * Метод выведения списка всех животных.
     */
    @Override
    public List<Animal> allAnimals() {
        return animalRepository.findAll();
    }

}

