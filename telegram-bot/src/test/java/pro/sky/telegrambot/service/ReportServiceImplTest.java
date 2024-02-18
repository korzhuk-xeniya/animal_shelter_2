package pro.sky.telegrambot.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pro.sky.telegrambot.model.Report;
import pro.sky.telegrambot.model.TrialPeriod;
import pro.sky.telegrambot.repository.ReportRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ReportServiceImplTest {
    private final Long id = 1234L;
    private final Long secondId = 1234L + 1;
    private final String text = "ваш текст";
    private final String photoId = "qewgsdjgsdoiypofsdffhnsu";
    private final String caption = "Рацион: ваш текст"+"Самочувствие: ваш текст"+"Поведение: ваш текст";
    private final LocalDate dateNow = LocalDate.now();
    private final Report validReport = new Report(id, photoId, text, text, text, dateNow, id);
    private final Report validReportWithoutId = new Report(photoId, text, text, text, dateNow, id);
    private final Report secondValidReport = new Report(id, photoId, null, null, null, null, secondId);
    private final Report thirdValidReport = new Report(id, photoId, text, text, text, dateNow, secondId);
    private final List<Report> reportList = List.of(validReport);
    private final List<Report> emptyList = new ArrayList<>();
    private final TrialPeriod trialPeriodWithReport = new TrialPeriod(id, dateNow, dateNow, dateNow,
            reportList, TrialPeriod.Result.IN_PROGRESS, id, TrialPeriod.AnimalType.CAT, id);
    private final TrialPeriod trialPeriodWithoutReport = new TrialPeriod(id, dateNow, dateNow, dateNow.minusDays(1),
            new ArrayList<>(), TrialPeriod.Result.IN_PROGRESS, id, TrialPeriod.AnimalType.CAT, id);
    private final List<TrialPeriod> trialPeriodListWithReport = List.of(trialPeriodWithReport);
    private final List<TrialPeriod> trialPeriodListWithoutReport = List.of(trialPeriodWithoutReport);
    @Mock
    ReportRepository reportRepoMock;

    @Mock
    TrialPeriodService trialPeriodService;

    @InjectMocks
    ReportServiceImpl reportService;

    @Test
    void create_shouldCreateAndReturnReportWithAllArgs() {
        when(reportRepoMock.save(validReport)).thenReturn(validReport);
        Report actual = reportService.create(validReport);
        assertEquals(validReport, actual);
        verify(reportRepoMock, times(1)).save(validReport);
    }

    @Test
    void getById() {
    }

    @Test
    void getByDateAndTrialId() {
    }

    @Test
    void getAll() {
    }

    @Test
    void gelAllByTrialPeriodId() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }

    @Test
    void deleteById() {
    }

    @Test
    void createFromTelegram() {
    }
}