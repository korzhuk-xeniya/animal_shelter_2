package pro.sky.telegrambot.service;

import pro.sky.telegrambot.model.User;

import java.util.List;

public interface UserService {
    User create(User user);

    User getById(Long id);

    User update(User user);

    void delete(User user);

    void deleteById(Long id);

    List<User> getAll();

}
