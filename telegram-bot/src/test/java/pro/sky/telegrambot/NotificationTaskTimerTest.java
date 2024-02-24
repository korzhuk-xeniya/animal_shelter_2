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



    LocalDateTime oneDaysAgo = LocalDateTime.now().minusDays(1).truncatedTo(ChronoUnit.MINUTES);

    private User user1 = new User("Pit", true, 12, LocalDateTime.now().minusDays(2).truncatedTo(ChronoUnit.MINUTES));
    private User user2 = new User("Pit2", true, 1,LocalDateTime.now().minusDays(1).truncatedTo(ChronoUnit.MINUTES) );
    private User user3 = new User("Pit2", true, 13,LocalDateTime.now().minusDays(30).truncatedTo(ChronoUnit.MINUTES) );
    @Test
    void task_test() {
        MockitoAnnotations.openMocks(this);
        notificationTaskTimer.task();
        when(userRepository.findByDateTimeToTookBefore(oneDaysAgo)).thenReturn(Optional.of(user2));

        verify(userRepository, times(1)).findByDateTimeToTookBefore(oneDaysAgo);
//        verify(shelterService, times(1)).changeMessage(user2.getChatId(),
//                " «Дорогой усыновитель, мы заметили, что ты не отправил ежедневный отчет." +
//                        " Пожалуйста, подойди ответственнее" +
//                        " к этому занятию. В противном случае волонтеры приюта будут обязаны " +
//                        "самолично проверять условия содержания животного»");


    }

    @Test
    void task2() {
        MockitoAnnotations.openMocks(this);
        notificationTaskTimer.task();
        when(userRepository.findByDateTimeToTookBefore(oneDaysAgo)).thenReturn(Optional.of(user1));

        verify(userRepository, times(1)).findByDateTimeToTookBefore(oneDaysAgo);
//        verify(shelterService,times(1)).callAVolunteerForBadReports(user1.getChatId());
    }

    @Test
    void task3() {
        MockitoAnnotations.openMocks(this);
        notificationTaskTimer.task();
        when(userRepository.findByDateTimeToTookBefore(oneDaysAgo)).thenReturn(Optional.of(user3));

        verify(userRepository, times(1)).findByDateTimeToTookBefore(oneDaysAgo);
//        verify(shelterService,times(1)).callAVolunteerForEndPeriod(user3.getChatId());
    }
}