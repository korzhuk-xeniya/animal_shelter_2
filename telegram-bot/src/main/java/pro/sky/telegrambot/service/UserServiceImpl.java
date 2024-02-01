package pro.sky.telegrambot.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import pro.sky.telegrambot.exceptions.NotFoundException;
import pro.sky.telegrambot.model.User;
import pro.sky.telegrambot.repository.UserRepository;


import java.lang.reflect.Field;
import java.util.List;
import java.util.Optional;


@Service
public class UserServiceImpl implements UserService {
    public UserRepository userRepository;
    private static final String EXCEPTION_NOT_FOUND_USER = "Пользователь не найден!";
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User create(User user) {
        return userRepository.save(user);
    }

    @Override
    public User getById(Long id) {
        Optional<User> optionalUser = userRepository.findByTelegramId(id);
        if (optionalUser.isEmpty()) {
            throw new NotFoundException(EXCEPTION_NOT_FOUND_USER);
        }
        return optionalUser.get();
    }

    @Override
    public User update(User user) {
        User currentUser = getById(user.getChatId());
        EntityUtils.copyNonNullFields(user, currentUser);
        return userRepository.save(currentUser);
    }

    @Override
    public void delete(User user) {
        deleteById(user.getChatId());
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(getById(id).getChatId());
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
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
