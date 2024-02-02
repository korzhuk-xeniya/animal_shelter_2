package pro.sky.telegrambot.listener;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pro.sky.telegrambot.service.ShelterService;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
public class TelegramBotUpdatesListener implements UpdatesListener {

    private final Logger logger = LoggerFactory.getLogger(TelegramBotUpdatesListener.class);

    private final ShelterService shelterService;
    private final TelegramBot telegramBot;
    public TelegramBotUpdatesListener(ShelterService shelterService, TelegramBot telegramBot) {
        this.shelterService = shelterService;
        this.telegramBot = telegramBot;
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
            shelterService.process(update);
        });
        return UpdatesListener.CONFIRMED_UPDATES_ALL;
    }

}
