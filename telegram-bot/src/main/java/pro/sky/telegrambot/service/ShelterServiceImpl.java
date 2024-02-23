package pro.sky.telegrambot.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.CallbackQuery;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.pengrad.telegrambot.request.EditMessageText;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.request.SendPhoto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import pro.sky.telegrambot.buttons.ButtonsOfMenu;
import pro.sky.telegrambot.model.Animal;
import pro.sky.telegrambot.model.Volunteer;
import pro.sky.telegrambot.repository.ShelterRepository;
import pro.sky.telegrambot.repository.UserRepository;
import pro.sky.telegrambot.repository.VolunteerRepository;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Service
public class ShelterServiceImpl implements ShelterService {


    private final TelegramBot telegramBot;
    private final ShelterRepository repository;
    private final ButtonsOfMenu buttons;
    private final Logger logger = LoggerFactory.getLogger(pro.sky.telegrambot.service.ShelterServiceImpl.class);
    private final VolunteerRepository volunteerRepository;
    private final UserRepository userRepository;
    private final UserService userService;
    private final VolunteerService volunteerService;
    private static final Pattern MESSAGE_PATTERN = Pattern.compile("^(\\+7)([0-9]{10})$");
    private final ObjectMapper objectMapper;
    private final AnimalService animalService;
    private final ReportAboutAnimalService reportAboutAnimalService;

    public ShelterServiceImpl(TelegramBot telegramBot, ShelterRepository repository, ButtonsOfMenu buttons,
                              VolunteerRepository volunteerRepository, UserRepository userRepository,
                              UserService userService, VolunteerService volunteerService, ObjectMapper objectMapper,
                              AnimalService animalService, ReportAboutAnimalService reportAboutAnimalService) {
        this.telegramBot = telegramBot;
        this.repository = repository;
        this.buttons = buttons;
        this.volunteerRepository = volunteerRepository;
        this.userRepository = userRepository;
        this.userService = userService;
        this.volunteerService = volunteerService;
        this.objectMapper = objectMapper;
        this.animalService = animalService;
        this.reportAboutAnimalService = reportAboutAnimalService;
    }

    @Override
    public void process(Update update) {
        if (update.message() == null && update.callbackQuery() == null) {
            logger.info("пользователь отправил пустое сообщение");
            return;
        }

        if (update.callbackQuery() != null) {
            processCallbackQuery(update.callbackQuery());
        } else if (update.message() != null) {
            processMessage(update);
        }
    }

    private void processMessage(Update update) {
        Message message = update.message();
        Long chatId = message.chat().id();
        String text = message.text();

        Matcher matcher = MESSAGE_PATTERN.matcher(text);
        if (matcher.find()) {
            userRepository.updateNumber(chatId.intValue(), text);
            userService.saveUser(update, false);
            sendMenuButton(chatId, "Номер записан, Вам обязательно позвонят!");
        } else if (update.message() != null && update.message().text().equals("/start")) {
            logger.info("пользователь отправил /start");
            processStartCommand(update);
        } else if (message.photo() != null && message.caption() != null) {
            logger.info("пользователь отправил фото с заголовком");
            // reportAboutAnimalService.savePhoto(update, message); TODO
        } else {
            processTextMessage(update);
        }
    }

    private void processCallbackQuery(CallbackQuery callbackQuery) {
        Map<String, String> infoMap = getInfo();
        Long chatId = callbackQuery.message().chat().id();
        int messageId = callbackQuery.message().messageId();
        String receivedMessage = callbackQuery.data();

        switch (receivedMessage) {
            case "Меню":
                changeMessage(messageId, chatId, "Выберите запрос, который Вам подходит. " +
                        "Если ни один из вариантов не подходит, я могу позвать Волонтера!", buttons.buttonsOfStart());
                break;
            case "Информация о приюте":
                changeMessage(messageId, chatId, "Добро пожаловать в наш приют для собак!", buttons.buttonsInformationAboutShelter());
                break;
            case "В начало":
                changeMessage(messageId, chatId, "Вы вернулись в начало!", buttons.buttonMenu());
                break;
            case "Как взять животное из приюта?":
                changeMessage(messageId, chatId, "Вы вернулись в начало!", buttons.takeAnimalButton());
                break;
            case "О приюте":
                sendMessageByKey(chatId, messageId, infoMap, "shelter.info", buttons.buttonsInformationAboutShelter());
                break;
            case "График работы":
                sendMessageByKey(chatId, messageId, infoMap, "shelter.work.schedule", buttons.buttonsInformationAboutShelter());
                break;
            case "Адрес приюта":
                sendMessageByKey(chatId, messageId, infoMap, "shelter.address", buttons.buttonsInformationAboutShelter());
                break;
            case "Телефон охраны":
                sendMessageByKey(chatId, messageId, infoMap, "security.phone", buttons.buttonsInformationAboutShelter());
                break;
            case "Схема проезда":
                new SendPhoto(chatId, "driving.directions"); //TODO
                break;
            case "Правила посещения приюта":
                sendMessageByKey(chatId, messageId, infoMap, "visiting.rules", buttons.buttonsInformationAboutShelter());
                break;
            case "Правила знакомства":
                sendMessageByKey(chatId, messageId, infoMap, "dating.rules", buttons.takeAnimalButton());
                break;
            case "Причины отказа":
                sendMessageByKey(chatId, messageId, infoMap, "reasons.for.refusal", buttons.takeAnimalButton());
                break;
            case "Обустройство щенка":
                sendMessageByKey(chatId, messageId, infoMap, "conditions.for.puppy", buttons.takeAnimalButton());
                break;
            case "Обустройство для взрослой собаки":
                sendMessageByKey(chatId, messageId, infoMap, "conditions.for.adult.dog", buttons.takeAnimalButton());
                break;
            case "Рекомендации по транспортировке":
                sendMessageByKey(chatId, messageId, infoMap, "transportation.recommendations", buttons.takeAnimalButton());
            case "Позвать волонтера":
                changeMessage(messageId, chatId, "Волонтер скоро свяжется с Вами", buttons.buttonMenu());
                break;
            case "Оставить телефон для связи":
                changeMessage(messageId, chatId, "Введите свой номер телефона в формате +71112223344", buttons.buttonMenu());
                break;
            case "Выбрать животное":
                List<Animal> animalList = new ArrayList<Animal>(animalService.allAnimals());
                for (Animal animal2 : animalList) {
                    sendButtonChooseAnimal(chatId, "Кличка животного:" + animal2.getNameOfAnimal() +
                            "; Возраст: " + animal2.getAgeMonth() + " месяцев; Тип животного: " +
                            animal2.getPetType() + ";Фото:" + animal2.getPhotoLink());

                }
                break;
            case "Взять животное":
                changeMessage(messageId, chatId, "Отправьте кличку животного.", buttons.buttonMenu());
                break;
        }
    }

    private void processStartCommand(Update update) {
        Long chatId = update.message().chat().id();
        String userName = update.message().from().firstName();
        sendMenuButton(chatId, "Добро пожаловать в PetShelterBot, " +
                userName + "! Я помогаю взаимодействовать с приютами для животных!");
        if (isAdminOrVolunteer(userName)) {
            logger.info("пользователь есть среди администраторов");
            volunteerService.saveVolunteer(update);
            sendMenuVolunteer(chatId, "Перед Вами меню волонтера");
        } else {
            userService.saveUser(update, false);
        }
    }

    private void processTextMessage(Update update) {
        Message message = update.message();
        Long chatId = message.chat().id();
        String text = message.text();
        List<Animal> animalList = new ArrayList<>(animalService.allAnimals());
        for (Animal pet : animalList) {
            if (text.equals(pet.getNameOfAnimal().toString())) {
                sendMessage(chatId, "Волонтер скоро свяжется с Вами, чтобы подтвердить Ваш выбор");
                callAVolunteerForConfirmationOfSelection(update, pet);
                userService.saveUser(update, true);
                animalService.saveUserIdInAnimal(update, pet);
            }
        }
    }

    private boolean isAdminOrVolunteer(String userName) {
        List<String> adminsVolunteers = Arrays.asList("xeny_sk");
        return adminsVolunteers.contains(userName);
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

    @Override
    public void sendMessageByKey(long chatId, int messageId, Map<String, String> infoMap, String key,
                                 InlineKeyboardMarkup keyboardMarkup) {
        logger.info("Был вызван метод получения информации по ключу", chatId, infoMap, key, keyboardMarkup);
        String message = infoMap.get(key);
        EditMessageText editMessageText = new EditMessageText(chatId, messageId, message).replyMarkup(keyboardMarkup);
        telegramBot.execute(editMessageText);
    }

    @Override
    public Map<String, String> getInfo() {
        Map<String, String> infoMap = new HashMap<>();
        try (InputStream infoStream = getClass().getClassLoader().getResourceAsStream("info.json")) {
            TypeReference<Map<String, String>> typeRef = new TypeReference<Map<String, String>>() {

            };
            return objectMapper.readValue(infoStream, typeRef);


        } catch (IOException e) {
            return infoMap;
        }
    }

    /**
     * @param update Отправка запроса на подтверждение выбора животного волонтером
     */
    public void callAVolunteerForConfirmationOfSelection(Update update, Animal pet) {
        logger.info("Был вызван метод для отправки запроса волонтеру на подтверждение выбора животного", update);
        List<Volunteer> volunteerList = volunteerRepository.findAll();
        for (Volunteer volunteer : volunteerList) {
            String user = update.message().from().username();
            SendMessage sendMessage = new SendMessage(volunteer.getChatId(),
                    "Пользователь: @" + user + " хочет усыновить животное " + pet.getNameOfAnimal() + " id: " +
                            pet.getId());
            telegramBot.execute(sendMessage);
        }
    }

    /**
     * метод для отправки кнопок "Выбрать животное"
     */
    @Override
    public void sendButtonChooseAnimal(Long chatId, String messageText) {
        logger.info("Был вызван метод для отправки кнопок Выбора животного", chatId, messageText);
        SendMessage sendMessage = new SendMessage(chatId, messageText);
        sendMessage.replyMarkup(buttons.buttonOfChooseAnimal());
        telegramBot.execute(sendMessage);
    }
}


