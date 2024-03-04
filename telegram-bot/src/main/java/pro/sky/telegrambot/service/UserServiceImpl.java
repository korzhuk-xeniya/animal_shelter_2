package pro.sky.telegrambot.service;


import com.pengrad.telegrambot.model.Update;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import pro.sky.telegrambot.exceptions.NotFoundException;
import pro.sky.telegrambot.model.User;
import pro.sky.telegrambot.repository.UserRepository;


import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Service
public class UserServiceImpl implements UserService {
    public UserRepository userRepository;
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    private final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    @Override
    public User createUser(User user) {
        //Добавить нового пользователя в БД
        logger.info("Был вызван метод для добавления нового пользователя в базу данных{}", user);
        return userRepository.save(user);
    }

    @Override
    public User getById(long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty()) {
            throw new NotFoundException();
        }
        return optionalUser.get();
    }

    @Override
    //Обновить пользователя в бд
    public User updateUser(User user) {
        logger.info("Был вызван метод для обновления пользователя в базе данных{}", user);
        userRepository.save(user);
        return user;
    }

    @Override
    public void delete(User user) {
        deleteById(user.getId());
    }

    @Override
    public void deleteById(long id) {
        userRepository.deleteById(getById(id).getChatId());
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }
    @Override
    //Получить юзера из бд
    public Optional<User> getUserByChatId(long chatId) {
        logger.info("Был вызван метод для получения пользователя из базы данных{}", chatId);
        return userRepository.findByChatId(chatId);
    }
    @Override
    /**
     * Поиск пользователя по chatId, если он есть то обновляем dateTimeToTook, если нет, создается новый пользователь
     */
    public void saveUser(Update update, boolean tookAPET) {
        int chatId = update.message().chat().id().intValue();
        Optional<User> userOptional = getUserByChatId(chatId);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setDateTimeToTook(LocalDateTime.now());
            user.setTookAPet(tookAPET);
            updateUser(user);
        } else {
            User newUser = new User(update.message().from().firstName(),tookAPET,chatId, LocalDateTime.now());
            createUser(newUser);
            newUser.setTookAPet(tookAPET);
        }
    }

}
