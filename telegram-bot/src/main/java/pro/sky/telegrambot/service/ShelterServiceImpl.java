package pro.sky.telegrambot.service;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.pengrad.telegrambot.request.EditMessageText;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.request.SendPhoto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import pro.sky.telegrambot.buttons.ButtonsOfMenu;
import pro.sky.telegrambot.model.Volunteer;
import pro.sky.telegrambot.repository.ShelterRepository;
import pro.sky.telegrambot.repository.UserRepository;
import pro.sky.telegrambot.repository.VolunteerRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import java.util.Map;

@Service
public class ShelterServiceImpl implements ShelterService {

    private final TelegramBot telegramBot;
    private final ShelterRepository repository;
    private final ButtonsOfMenu buttons;
    private final InfoService infoService;
    private final Logger logger = LoggerFactory.getLogger(pro.sky.telegrambot.service.ShelterServiceImpl.class);
    private final VolunteerRepository volunteerRepository;
    private final UserRepository userRepository;
    private final UserService userService;
    private final VolunteerService volunteerService;
    private static final Pattern MESSAGE_PATTERN = Pattern.compile("^(\\+7)([0-9]{10})$");

    public ShelterServiceImpl(TelegramBot telegramBot, ShelterRepository repository, ButtonsOfMenu buttons,
                              InfoService infoService, VolunteerRepository volunteerRepository, UserRepository userRepository, UserService userService, VolunteerService volunteerService) {
        this.telegramBot = telegramBot;
        this.repository = repository;
        this.buttons = buttons;
        this.infoService = infoService;
        this.volunteerRepository = volunteerRepository;
        this.userRepository = userRepository;
//        this.volunteerRepository = volunteerRepository;
//        this.userRepository = userRepository;
//        this.userService = userService;
//        this.volunteerService = volunteerService;
        this.userService = userService;
        this.volunteerService = volunteerService;
    }

    @Override
    public void process(Update update) {
        List<String> adminsVolunteers = new ArrayList<>();
        adminsVolunteers.add("le_murk");

            Map<String, String> infoMap = infoService.getInfo();


            if (update.message() == null && update.callbackQuery() == null) {
                logger.info("пользователь отправил пустое сообщение");
                return;
            }

            if (update.callbackQuery() == null) {
                Long chatId = update.message().chat().id();
                String message = update.message().text();
                Long userId = update.message().from().id();
                String userName = update.message().from().firstName();
                int messageId = update.message().messageId();
                Matcher matcher = MESSAGE_PATTERN.matcher(message);
                if (update.message() != null && matcher.find()) {
                    userRepository.updateNumber(update.message().chat().id().intValue(), update.message().text());
                    userService.saveUser(update, false);
                    sendMenuButton(chatId, "Номер записан, Вам обязательно позвонят!");



                } else if (update.message() != null && !update.message().text().equals("/start") && !matcher.find()) {
                    logger.info("пользователь отправил  сообщение  с неопределенным содержанием");
                    sendMessage(chatId, ("Содержание не определено. Для начала работы, отправь /start." +
                            "Для записи номера телефона, введите его в формате +71112223344")
                    );
                    return;
                }
//            if (!update.message().text().equals("/start")) {
//                logger.info("пользователь отправил  сообщение с неопределенным содержанием");
//                sendMessage(chatId, );
//                return;
//            }
                if (update.message().text().equals("/start")) {
                    logger.info("пользователь отправил /start");
                    sendMenuButton(chatId, " Добро пожаловать в PetShelterBot, "
                            + update.message().from().firstName() + "! Я помогаю взаимодействовать с приютами для животных!");
                    if (adminsVolunteers.contains(update.message().from().username())) {
                        logger.info("пользователь есть среди администраторов");
                        volunteerService.saveVolunteer(update);
                        sendMenuVolunteer(chatId, "Перед Вами меню волонтера");

                    } else {
                        userService.saveUser(update, false);
                    }
                }
            } else {
                if (update.callbackQuery() != null) {
                    logger.info("пользователь нажал на кнопку");
                    Long chatId = update.callbackQuery().message().chat().id();
                    Long userId = update.callbackQuery().from().id();
                    String userName = update.callbackQuery().from().firstName();
                    String message = update.callbackQuery().message().text();
                    int messageId = update.callbackQuery().message().messageId();
                    String receivedMessage = update.callbackQuery().data();

//
                    switch (receivedMessage) {
                        //Cтартовый блок
                        case "Меню" -> changeMessage(messageId, chatId, "Выберите запрос, который Вам подходит. " +
                                "Если ни один из вариантов не подходит, я могу позвать Волонтера!", buttons.buttonsOfStart());

                        //  блок определения запроса
                        case "Информация о приюте" ->
                                changeMessage(messageId, chatId, "Добро пожаловать в наш приют для собак!",
                                        buttons.buttonsInformationAboutShelter());
                        case "В начало" ->
                                changeMessage(messageId, chatId, "Вы вернулись в начало!", buttons.buttonMenu());


                        case "Как взять животное из приюта?" ->
                                changeMessage(messageId, chatId, "Вы вернулись в начало!", buttons.takeAnimalButton());

                        case "О приюте" -> sendMessageByKey(chatId, infoMap, "shelter.info");
                        case "График работы" -> sendMessageByKey(chatId, infoMap, "shelter.work.schedule");
                        case "Адрес приюта" -> sendMessageByKey(chatId, infoMap, "shelter.address");
                        case "Телефон охраны" -> sendMessageByKey(chatId, infoMap, "security.phone");
                        case "Схема проезда" -> new SendPhoto(chatId, "driving.directions");
                        case "Правила посещения приюта" -> sendMessageByKey(chatId, infoMap, "visiting.rules");
                        case "Правила знакомства" -> sendMessageByKey(chatId, infoMap, "dating.rules");
                        case "Причины отказа" -> sendMessageByKey(chatId, infoMap, "reasons.for.refusal");
                        case "Обустройство щенка" -> sendMessageByKey(chatId, infoMap, "conditions.for.puppy");
                        case "Обустройство для взрослой собаки" ->
                                sendMessageByKey(chatId, infoMap, "conditions.for.adult.dog");
                        case "Рекомендации по транспортировке" ->
                                sendMessageByKey(chatId, infoMap, "transportation.recommendations");

//                    case "Получить список животных для усыновления": {
//                        infoService.getPets().stream()
//                                .map(pet -> new SendPhoto(chatId, pet.getPhoto())
//                                        .caption(String.format("%s-%s", pet.getName(), pet.getDescription())))
//                                .forEach(telegramBot::execute);

                        case "Позвать волонтера" -> {
                            callAVolunteer(update);
                            changeMessage(messageId, chatId, "Волонтер скоро свяжется с Вами", buttons.buttonMenu());
                        }
                        case "Оставить телефон для связи" -> {
                            changeMessage(messageId, chatId,
                                    "Введите свой номер телефона в формате +71112223344", buttons.buttonMenu());
                        }


                    }
                }
            }


        }



    @Override
    public void sendMessage(Long chatId, String messageText) {
        logger.info("Был вызван метод для отправки сообщения", chatId, messageText);
        SendMessage sendMessage = new SendMessage(chatId, messageText);
        telegramBot.execute(sendMessage);
    }

    /**
     * метод для отправки кнопки "Меню"
     */
    @Override
    public void sendMenuButton(Long chatId, String messageText) {
        logger.info("Был вызван метод для отправки кнопки Меню", chatId, messageText);
        SendMessage sendMessage = new SendMessage(chatId, messageText);
        sendMessage.replyMarkup(buttons.buttonMenu());
        telegramBot.execute(sendMessage);
    }

    @Override
    public void sendMenuVolunteer(Long chatId, String messageText) {
        logger.info("Был вызван метод для отправки кнопок Меню волонтера", chatId, messageText);
        SendMessage sendMessage = new SendMessage(chatId, messageText);
        sendMessage.replyMarkup(buttons.buttonsOfVolunteer());
        telegramBot.execute(sendMessage);
    }

    /**
     * метод для отправки кнопок Этапа 0. Определение запроса
     */
    @Override
    public void sendButtonsOfStep0(Long chatId, String messageText) {
        logger.info("Был вызван метод для отправки кнопок 0 этапа", chatId, messageText);
        SendMessage sendMessage = new SendMessage(chatId, messageText);
        sendMessage.replyMarkup(buttons.buttonsOfStart());
        telegramBot.execute(sendMessage);
    }

    @Override
    /**
     * метод для изменения сообщения
     */ public void changeMessage(int messageId, long chatIdInButton, String messageText, InlineKeyboardMarkup
            keyboardMarkup) {
        logger.info("Был вызван метод для изменения сообщения", messageId, chatIdInButton, messageText, keyboardMarkup);
        EditMessageText editMessageText = new EditMessageText(chatIdInButton, messageId, messageText);

        editMessageText.replyMarkup(keyboardMarkup);

        telegramBot.execute(editMessageText);

    }


    /**
     * Провека ввода /start
     */
    private boolean isStartEntered(Update update) {
        return update.message().text() != null && update.message().text().equals("/start");
    }

    @Override
    /**
     * @param update Реализация кнопки "Позвать волонтера"
     */
    public void callAVolunteer(Update update) {
        List<Volunteer> volunteerList = volunteerRepository.findAll();
        for (Volunteer volunteer : volunteerList) {
            String user = update.callbackQuery().from().username();
            SendMessage sendMessage = new SendMessage(volunteer.getChatId(),
                    "Пользователь: @" + user + " просит с ним связаться.");
            telegramBot.execute(sendMessage);
        }
    }

    private void sendMessageByKey(Long chatId, Map<String, String> infoMap, String key) {
        String message = infoMap.get(key);
        SendMessage response = new SendMessage(chatId, message);
        telegramBot.execute(response);


    }
}