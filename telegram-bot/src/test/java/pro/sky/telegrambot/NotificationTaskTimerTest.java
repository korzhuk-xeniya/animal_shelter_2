package pro.sky.telegrambot;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.BootstrapWith;
import org.springframework.transaction.annotation.Transactional;
import pro.sky.telegrambot.model.User;
import pro.sky.telegrambot.repository.UserRepository;
import pro.sky.telegrambot.service.ShelterService;


import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;


//@ExtendWith(MockitoExtension.class)
class NotificationTaskTimerTest {
        @Mock
            private UserRepository userRepository;
    @Mock
            private ShelterService shelterService;
        @InjectMocks
            private NotificationTaskTimer notificationTaskTimer;


    LocalDateTime localDateTime2 = LocalDateTime.of(2024, 2, 21, 8, 15, 12);
    LocalDateTime localDateTime3 = LocalDateTime.of(2024, 2, 24, 8, 15, 12);
    LocalDateTime oneDaysAgo = LocalDateTime.now().minusDays(1).truncatedTo(ChronoUnit.MINUTES);
//            LocalDateTime.of(2024,2,23,8,15,12);
    private User user1 = new User("Pit", true, 123456, localDateTime3);
    private User user2 = new User("Pit2", true, 1,LocalDateTime.now().minusDays(2).truncatedTo(ChronoUnit.MINUTES) );
    @Test
    void task_test() {
        MockitoAnnotations.openMocks(this);


//        User user = new User();
//        user.setDateTimeToTook(oneDaysAgo.minusDays(2)); // User took the pets more than 2 days ago
//        user.setChatId(1);

        notificationTaskTimer.task();

        when(userRepository.findByDateTimeToTookBefore(oneDaysAgo)).thenReturn(Optional.of(user2));

        verify(userRepository, times(1)).findByDateTimeToTookBefore(oneDaysAgo);
        verify(shelterService, times(1)).changeMessage(user2.getChatId(), " «Дорогой усыновитель, мы заметили, что ты не отправил ежедневный отчет." +
                " Пожалуйста, подойди ответственнее" +
                " к этому занятию. В противном случае волонтеры приюта будут обязаны " +
                "самолично проверять условия содержания животного»");


    }

    @Test
    void task2() {
    }

    @Test
    void task3() {
    }
}