package pro.sky.telegrambot;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.BootstrapWith;
import org.springframework.test.context.TestContextBootstrapper;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;

import static org.awaitility.Awaitility.await;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
@BootstrapWith(value = TestContextBootstrapper.class)
//@SpringJUnitConfig
@RunWith(SpringRunner.class)
@SpringBootTest
class NotificationTaskTimerTest {
    @SpyBean
    private NotificationTaskTimer notificationTaskTimer;

    NotificationTaskTimerTest(NotificationTaskTimer notificationTaskTimer) {
        this.notificationTaskTimer = notificationTaskTimer;
    }

    @Test
    void task_test() {

        await().atMost(1, TimeUnit.DAYS)
                .untilAsserted(() -> verify(notificationTaskTimer, times(1)).task());
//        await().atMost(2, TimeUnit.DAYS).untilAsserted(() ->
//                verify(notificationTaskTimer, Mockito.atLeastOnce()).task());
    }

    @Test
    void task2() {
    }

    @Test
    void task3() {
    }
}