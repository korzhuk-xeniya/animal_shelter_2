package pro.sky.telegrambot.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pro.sky.telegrambot.model.User;
import pro.sky.telegrambot.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

    @ExtendWith(MockitoExtension.class)
    class UserServiceImplTest {
        @Mock
        private UserRepository userRepository;
        @InjectMocks
        private UserServiceImpl userService;
        @Mock
        private User user;
        User newUser = new User("Elena", false, 4L, LocalDateTime.now());



        @Test
        void createUser_shouldReturnNewUser() {
            when(userRepository.save((User) any())).thenReturn(newUser);
            User result = userRepository.save(newUser);
            assertEquals(newUser, result);

        }

            @Test
            void getById () {
            }

            @Test
            void update_shouldReturn () {
            }

            @Test
            void updateUser () {
            }

            @Test
            void delete () {
            }

            @Test
            void deleteById () {
            }

            @Test
            void getAll_shouldReturnCollectionUsers () {



            }

            @Test
            void getUserByChatId () {
            }

            @Test
            void saveUser () {
            }
        }