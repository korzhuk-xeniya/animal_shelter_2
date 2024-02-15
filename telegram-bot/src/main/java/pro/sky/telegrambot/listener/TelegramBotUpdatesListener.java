package pro.sky.telegrambot.listener;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.GetFile;
import com.pengrad.telegrambot.request.SendPhoto;
import com.pengrad.telegrambot.response.GetFileResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import pro.sky.telegrambot.model.TrialPeriod;
import pro.sky.telegrambot.service.ReportService;
import pro.sky.telegrambot.service.ShelterService;
import pro.sky.telegrambot.service.TrialPeriodService;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.List;

@Service
@Component
@RequiredArgsConstructor
public class TelegramBotUpdatesListener implements UpdatesListener {

    private final Logger logger = LoggerFactory.getLogger(TelegramBotUpdatesListener.class);

    private final ShelterService shelterService;
    private final TrialPeriodService trialPeriodService;
    private final ReportService reportService;
    private final TelegramBot telegramBot;
    public TelegramBotUpdatesListener(ShelterService shelterService, TelegramBot telegramBot,
                                      TrialPeriodService trialPeriodService,ReportService reportService) {
        this.shelterService = shelterService;
        this.telegramBot = telegramBot;
        this.trialPeriodService=trialPeriodService;
        this.reportService=reportService;
    }
//    @Autowired
//    private TelegramBot telegramBot;

    
    @PostConstruct
    public void init() {
        telegramBot.setUpdatesListener(this);
    }


    @Override
    public int process(List<Update> updates) {
        updates.forEach(update -> {
            logger.info("Processing update: {}", update);
            try {
                shelterService.process(update);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        return UpdatesListener.CONFIRMED_UPDATES_ALL;
    }
    public void sendReportPhotoToVolunteer(Long reportId, Long volunteerId) {
        GetFile request = new GetFile(reportService.getById(reportId).getPhotoId());
        GetFileResponse getFileResponse = telegramBot.execute(request);
        TrialPeriod trialPeriod = trialPeriodService.getById(reportService.getById(reportId).getTrialPeriodId());
        if (getFileResponse.isOk()) {
            try {
                byte[] image = telegramBot.getFileContent(getFileResponse.file());
                SendPhoto sendPhoto = new SendPhoto(volunteerId, image);
                sendPhoto.caption("Id владельца: " + trialPeriod.getOwnerId() + "\n" +
                        "Id испытательного срока: " + trialPeriod.getId() + "\n" +
                        "Id отчёта:" + reportId);
                telegramBot.execute(sendPhoto);
            } catch (IOException e) {
                logger.error(e.getMessage(), e);
            }
        }
    }


}
