package pro.sky.telegrambot.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import pro.sky.telegrambot.model.Animal;
import pro.sky.telegrambot.model.Buttons;
import pro.sky.telegrambot.model.Volunteer;
import pro.sky.telegrambot.repository.ShelterRepository;
import pro.sky.telegrambot.repository.UserRepository;
import pro.sky.telegrambot.repository.VolunteerRepository;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import java.util.Map;

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

    public ShelterServiceImpl(TelegramBot telegramBot, ShelterRepository repository, ButtonsOfMenu buttons, VolunteerRepository volunteerRepository, UserRepository userRepository, UserService userService, VolunteerService volunteerService, ObjectMapper objectMapper, AnimalService animalService, ReportAboutAnimalService reportAboutAnimalService) {
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
        Map<String, String> infoMap = getInfo();
        List<String> adminsVolunteers = new ArrayList<>();
        adminsVolunteers.add("xeny_sk");

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
            }
//
            if (update.message().text().equals(Buttons.START.getCode())) {
                logger.info("пользователь отправил /start");
                sendMenuButton(chatId, update.message().from().firstName() + ", добро пожаловать в PetShelterBot! Я помогаю взаимодействовать с приютами для животных!");
                if (adminsVolunteers.contains(update.message().from().username())) {
                    logger.info("пользователь есть среди администраторов");
                    volunteerService.saveVolunteer(update);
                    sendMenuVolunteer(chatId, "Перед Вами меню волонтера");

                } else {
                    userService.saveUser(update, false);
                }
            }

            if (update.message().photo() != null && update.message().caption() != null) {
                logger.info("пользователь отправил фото с заголовком");
//                reportAboutAnimalService.savePhoto(update, update.message()); TODO
            }

            List<Animal> animalList1 = new ArrayList<Animal>(animalService.allAnimals());
            for (Animal pet : animalList1) {
                if (update.message().text().equals(pet.getNameOfAnimal().toString())) {
                    sendMessage(chatId, "Волонтер скоро свяжется с Вами, чтобы подтвердить Ваш выбор");
                    callAVolunteerForConfirmationOfSelection(update, pet);
                    userService.saveUser(update, true);
                    animalService.saveUserIdInAnimal(update, pet);
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
                    case "Меню" ->
                            changeMessage(messageId, chatId, "Выберите запрос, который Вам подходит. Если ни один из вариантов не подходит, я могу позвать Волонтера!", buttons.buttonsOfStart());
                    //  блок определения запроса
                    case "Информация о приюте" ->
                            changeMessage(messageId, chatId, "Добро пожаловать в наш приют для собак!", buttons.buttonsInformationAboutShelter());
                    case "В начало" -> changeMessage(messageId, chatId, "Вы вернулись в начало!", buttons.buttonMenu());

//                    break;
                    case "Как взять животное из приюта?" ->
                            changeMessage(messageId, chatId, "Вы вернулись в начало!", buttons.takeAnimalButton());

                    case "О приюте" ->
                            sendMessageByKey(chatId, messageId, infoMap, "shelter.info", buttons.buttonsInformationAboutShelter());
                    case "График работы" ->
                            sendMessageByKey(chatId, messageId, infoMap, "shelter.work.schedule", buttons.buttonsInformationAboutShelter());
                    case "Адрес приюта" ->
                            sendMessageByKey(chatId, messageId, infoMap, "shelter.address", buttons.buttonsInformationAboutShelter());
                    case "Телефон охраны" ->
                            sendMessageByKey(chatId, messageId, infoMap, "security.phone", buttons.buttonsInformationAboutShelter());
                    case "Схема проезда" -> new SendPhoto(chatId, "driving.directions"); //TODO
//                    case "Список документа" -> ; //TODO
                    case "Правила посещения приюта" ->
                            sendMessageByKey(chatId, messageId, infoMap, "visiting.rules", buttons.buttonsInformationAboutShelter());
                    case "Правила знакомства" ->
                            sendMessageByKey(chatId, messageId, infoMap, "dating.rules", buttons.takeAnimalButton());
                    case "Причины отказа" ->
                            sendMessageByKey(chatId, messageId, infoMap, "reasons.for.refusal", buttons.takeAnimalButton());
                    case "Обустройство щенка/котенка" ->
                            sendMessageByKey(chatId, messageId, infoMap, "conditions.for.puppy", buttons.takeAnimalButton());
                    case "Обустройство для взрослой собаки/кошки" ->
                            sendMessageByKey(chatId, messageId, infoMap, "conditions.for.adult.dog", buttons.takeAnimalButton());
                    case "Рекомендации по транспортировке" ->
                            sendMessageByKey(chatId, messageId, infoMap, "transportation.recommendations", buttons.takeAnimalButton());


                    case "Позвать волонтера" -> {
                        callAVolunteer(update);
                        changeMessage(messageId, chatId, "Волонтер скоро свяжется с Вами", buttons.buttonMenu());
                    }
                    case "Оставить телефон для связи" ->
                            changeMessage(messageId, chatId, "Введите свой номер телефона в формате +71112223344", buttons.buttonMenu());
                    case "Выбрать животное" -> {
                        List<Animal> animalList = new ArrayList<Animal>(animalService.allAnimals());
                        for (Animal animal2 : animalList) {
                            sendButtonChooseAnimal(chatId, "Кличка животного:" + animal2.getNameOfAnimal() + "; Возраст: " + animal2.getAgeMonth() + " месяцев; Тип животного: " + animal2.getPetType() + ";Фото:" + animal2.getPhotoLink());

                        }
                    }
                    case "Взять животное" ->
                            changeMessage(messageId, chatId, "Отправьте кличку животного.", buttons.buttonMenu());

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
     */ public void changeMessage(int messageId, long chatIdInButton, String messageText, InlineKeyboardMarkup keyboardMarkup) {
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
     */ public void callAVolunteer(Update update) {
        List<Volunteer> volunteerList = volunteerRepository.findAll();
        for (Volunteer volunteer : volunteerList) {
            String user = update.callbackQuery().from().username();
            SendMessage sendMessage = new SendMessage(volunteer.getChatId(), "Пользователь: @" + user + " просит с ним связаться.");
            telegramBot.execute(sendMessage);
        }
    }

    @Override
    public void sendMessageByKey(long chatId, int messageId, Map<String, String> infoMap, String key, InlineKeyboardMarkup keyboardMarkup) {
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
            SendMessage sendMessage = new SendMessage(volunteer.getChatId(), "Пользователь: @" + user + " хочет усыновить животное " + pet.getNameOfAnimal() + " id: " + pet.getId());
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


