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
    private static final String EXCEPTION_NOT_FOUND_USER = "Пользователь не найден!";
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    private final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    @Override
    public User createUser(User user) {
        //Добавить нового пользователя в БД
        logger.info("Был вызван метод для добавления нового пользователя в базу данных", user);
        return userRepository.save(user);
    }

    @Override
    public User getById(long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty()) {
            throw new NotFoundException(EXCEPTION_NOT_FOUND_USER);
        }
        return optionalUser.get();
    }

//    @Override
//    public User update(User user) {
//        User currentUser = getById(user.getId());
//        EntityUtils.copyNonNullFields(user, currentUser);
//        return userRepository.save(currentUser);
//    }
    @Override
    //Обновить пользователя в бд
    public User updateUser(User user) {
        logger.info("Был вызван метод для обновления пользователя в базе данных", user);
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
        logger.info("Был вызван метод для получения пользователя из базы данных", chatId);
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
            updateUser(user);
        } else {
            User newUser = new User(update.message().from().firstName(),tookAPET,chatId, LocalDateTime.now());
            createUser(newUser);
        }
    }
    public static class EntityUtils {
        /**
         * Метод для копирования из одного объекта в другой
         * игнорируя поля в которых есть null
         *
         * @param fromObj объект от которого копируем
         * @param toObj   объект в который копируем
         */

        public static void copyNonNullFields(Object fromObj, Object toObj) {
            Logger logger = (Logger) LoggerFactory.getLogger(EntityUtils.class);
            Field[] fields = fromObj.getClass().getDeclaredFields();
            for (Field field : fields) {
                try {
                    field.setAccessible(true);
                    Object value = field.get(fromObj);
                    if (value != null) {
                        field.set(toObj, value);
                    }
                } catch (IllegalAccessException e) {
                    logger.error(e.getMessage(), e);
                }
            }
        }

    }
}
