package pro.sky.telegrambot.service;

import com.pengrad.telegrambot.model.Chat;
import com.pengrad.telegrambot.model.Message;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pro.sky.telegrambot.model.Report;
import pro.sky.telegrambot.model.User;
import pro.sky.telegrambot.repository.ReportRepository;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ReportServiceImplTest {
@Mock
    ReportRepository reportRepository;
@InjectMocks
ReportServiceImpl reportService;
byte[] photo = {1,2,3};
    User user = new User( "Slava", true,15, LocalDateTime.now());
private Report report1 = new Report(90L, LocalDateTime.now(),"ok",
        "photo",photo,false, user);
    @Test
    void reportAdd() {
        when(reportRepository.save(report1)).thenReturn(report1);
        Report result = reportService.reportAdd(report1);

        assertEquals(report1,result);
    }

    @Test
    void updateReport() {
        reportService.updateReport(report1);

        verify(reportRepository, times(1)).save(report1);
    }
    @Test
    public void testDailyReportCheckPhotoWithPhoto() {
        Message message = mock(Message.class);
        Chat chat  = mock(Chat.class);

        when(message.chat()).thenReturn(chat);
        when(message.chat().id()).thenReturn(123L);

        assertEquals(123L, message.chat().id());
    }
    @Test
    void dailyReportCheckPhoto() {
    }

    @Test
    void dailyReportCheckMessage() {
    }

    @Test
    void saveReportMessage() {
    }

    @Test
    void getPhoto() {
    }

    @Test
    void saveReportPhotoId() {
    }
}