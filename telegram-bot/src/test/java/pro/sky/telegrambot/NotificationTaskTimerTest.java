package pro.sky.telegrambot;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.BootstrapWith;
import org.springframework.transaction.annotation.Transactional;
import pro.sky.telegrambot.model.User;
import pro.sky.telegrambot.repository.UserRepository;
import pro.sky.telegrambot.service.ShelterService;


import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class NotificationTaskTimerTest {
        @Mock
            private UserRepository userRepository;
    @Mock
            private ShelterService shelterService;
        @InjectMocks
            private NotificationTaskTimer notificationTaskTimer;

    LocalDateTime localDateTime1 = LocalDateTime.of(2024, 1, 23, 8, 15, 12);
    LocalDateTime localDateTime2 = LocalDateTime.of(2024, 2, 23, 8, 15, 12);
    LocalDateTime localDateTime3 = LocalDateTime.of(2024, 2, 24, 8, 15, 12);
    private User user1 = new User("Pit", true, 123456, localDateTime1);
    private User user2 = new User("Pit2", true, 123456, localDateTime2);


    @Test
    void task_test() {
        doNothing().when(shelterService).changeMessage(anyLong(),anyString());
        userRepository.save(user2);
        notificationTaskTimer.task();

        verify(shelterService, only()).changeMessage(anyLong(),anyString());


    }

    @Test
    void task2() {
    }

    @Test
    void task3() {
    }
}