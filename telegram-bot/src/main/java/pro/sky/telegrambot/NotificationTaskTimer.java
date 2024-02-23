package pro.sky.telegrambot;

import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import pro.sky.telegrambot.model.Volunteer;
import pro.sky.telegrambot.repository.UserRepository;
import pro.sky.telegrambot.service.ShelterService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.TimeUnit;


import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import pro.sky.telegrambot.repository.UserRepository;
import pro.sky.telegrambot.service.ShelterService;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

@EnableScheduling
@Component
public class NotificationTaskTimer {


    private final UserRepository userRepository;
    private final ShelterService shelterService;

    public NotificationTaskTimer(UserRepository userRepository, ShelterService shelterService) {
        this.userRepository = userRepository;
        this.shelterService = shelterService;
    }

    /**
     * Проверяет юзеров на отчеты, если отчета нет > 1 дня, то отправляет пользователю сообщение
     */
    @Scheduled(fixedDelay = 1, timeUnit = TimeUnit.HOURS)
    public void task() {
        LocalDateTime oneDaysAgo = LocalDateTime.now().minusDays(1);
        String message = " «Дорогой усыновитель, мы заметили, что ты не отправил ежедневный отчет." +
                " Пожалуйста, подойди ответственнее" +
                " к этому занятию. В противном случае волонтеры приюта будут обязаны " +
                "самолично проверять условия содержания животного»";
        userRepository.findByDateTimeToTookBefore(oneDaysAgo)
                .ifPresent(user -> {
                    if (user.getDateTimeToTook().isBefore(oneDaysAgo)) {
                        shelterService.changeMessage(user.getChatId(), message);
                    }
                });
    }

    @Scheduled(fixedDelay = 2 , timeUnit = TimeUnit.HOURS)
    public void task2() {
        LocalDateTime twoDaysAgo = LocalDateTime.now().minusDays(2);
//        String message = " «Дорогой усыновитель, мы заметили, что ты заполняешь " +
//                "отчет не так подробно, как необходимо. Пожалуйста, подойди ответственнее" +
//                " к этому занятию. В противном случае волонтеры приюта будут обязаны " +
//                "самолично проверять условия содержания животного»";
        userRepository.findByDateTimeToTookBefore(twoDaysAgo)
                .ifPresent(user -> {
                    if (user.getDateTimeToTook().isBefore(twoDaysAgo)) {
                        shelterService.callAVolunteerForBadReports(user.getChatId());
                        }
                    });
                }

    }


