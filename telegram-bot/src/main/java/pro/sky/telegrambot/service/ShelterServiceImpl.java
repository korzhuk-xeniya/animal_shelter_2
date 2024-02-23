package pro.sky.telegrambot.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.File;
import com.pengrad.telegrambot.model.PhotoSize;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.pengrad.telegrambot.request.EditMessageText;
import com.pengrad.telegrambot.request.GetFile;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.request.SendPhoto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import pro.sky.telegrambot.buttons.ButtonsOfMenu;
import pro.sky.telegrambot.model.Animal;
import pro.sky.telegrambot.model.Report;
import pro.sky.telegrambot.model.Volunteer;
import pro.sky.telegrambot.repository.ReportRepository;
import pro.sky.telegrambot.repository.ShelterRepository;
import pro.sky.telegrambot.repository.UserRepository;
import pro.sky.telegrambot.repository.VolunteerRepository;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
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
    private final ReportService reportService;
    private final ReportRepository reportRepository;
    private boolean photoCheckButton = false; // флаг на проверку нажатия кнопки
    private boolean reportCheckButton = false; // флаг на проверку нажатия кнопки
    private String namePhotoId;
    int sendMessageReport;

    public ShelterServiceImpl(TelegramBot telegramBot, ShelterRepository repository, ButtonsOfMenu buttons,
                              VolunteerRepository volunteerRepository, UserRepository userRepository,
                              UserService userService, VolunteerService volunteerService, ObjectMapper objectMapper,
                              AnimalService animalService, ReportService reportService, ReportRepository reportRepository) {
        this.telegramBot = telegramBot;
        this.repository = repository;
        this.buttons = buttons;
        this.volunteerRepository = volunteerRepository;
        this.userRepository = userRepository;
        this.userService = userService;
        this.volunteerService = volunteerService;
        this.objectMapper = objectMapper;
        this.animalService = animalService;
        this.reportService = reportService;
        this.reportRepository = reportRepository;
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

            if (update.message().photo() != null&& update.message().caption() != null) {
                logger.info("пользователь отправил фото с заголовком");
//                reportService.savePhoto(update, update.message()); TODO
            }
            List<Animal> animalList1 = new ArrayList<Animal>(animalService.allAnimals());
            for (Animal pet : animalList1) {
                if (update.message().text().equals(pet.getNameOfAnimal().toString())) {
                    sendMessage(chatId,
                            "Волонтер скоро свяжется с Вами, чтобы подтвердить Ваш выбор");
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
                    case "Меню" -> changeMessage(messageId, chatId, "Выберите запрос, который Вам подходит. " +
                            "Если ни один из вариантов не подходит, я могу позвать Волонтера!", buttons.buttonsOfStart());

                    //  блок определения запроса
                    case "Информация о приюте" -> changeMessage(messageId, chatId, "Добро пожаловать в наш приют для собак!",
                            buttons.buttonsInformationAboutShelter());
                    case "В начало" -> changeMessage(messageId, chatId, "Вы вернулись в начало!", buttons.buttonMenu());

//                    break;
                    case "Как взять животное из приюта?" -> changeMessage(messageId, chatId, "Вы вернулись в начало!", buttons.takeAnimalButton());

                    case "О приюте" -> sendMessageByKey(chatId, messageId, infoMap, "shelter.info", buttons.buttonsInformationAboutShelter());
                    case "График работы" -> sendMessageByKey(chatId, messageId, infoMap, "shelter.work.schedule", buttons.buttonsInformationAboutShelter());
                    case "Адрес приюта" -> sendMessageByKey(chatId, messageId, infoMap, "shelter.address", buttons.buttonsInformationAboutShelter());
                    case "Телефон охраны" -> sendMessageByKey(chatId, messageId, infoMap, "security.phone", buttons.buttonsInformationAboutShelter());
                    case "Схема проезда" -> new SendPhoto(chatId, "driving.directions"); //TODO
//                    case "Список документа" -> ; //TODO
                    case "Правила посещения приюта" -> sendMessageByKey(chatId, messageId, infoMap, "visiting.rules", buttons.buttonsInformationAboutShelter());
                    case "Правила знакомства" -> sendMessageByKey(chatId, messageId, infoMap, "dating.rules", buttons.takeAnimalButton());
                    case "Причины отказа" -> sendMessageByKey(chatId, messageId, infoMap, "reasons.for.refusal", buttons.takeAnimalButton());
                    case "Обустройство щенка" -> sendMessageByKey(chatId, messageId, infoMap, "conditions.for.puppy", buttons.takeAnimalButton());
                    case "Обустройство для взрослой собаки" -> sendMessageByKey(chatId, messageId, infoMap, "conditions.for.adult.dog", buttons.takeAnimalButton());
                    case "Рекомендации по транспортировке" -> sendMessageByKey(chatId, messageId, infoMap, "transportation.recommendations", buttons.takeAnimalButton());


                    case "Позвать волонтера" -> {
                        callAVolunteer(update);
                        changeMessage(messageId, chatId, "Волонтер скоро свяжется с Вами", buttons.buttonMenu());
                    }
                    case "Оставить телефон для связи" -> changeMessage(messageId, chatId,
                            "Введите свой номер телефона в формате +71112223344", buttons.buttonMenu());
                    case "Выбрать животное" -> {
                        List<Animal> animalList = new ArrayList<Animal>(animalService.allAnimals());
                        for (Animal animal2 : animalList) {
                            sendButtonChooseAnimal(chatId, "Кличка животного:" + animal2.getNameOfAnimal() +
                                    "; Возраст: " + animal2.getAgeMonth() + " месяцев; Тип животного: " +
                                    animal2.getPetType() + ";Фото:" + animal2.getPhotoLink());

                        }
                    }
                    case "Взять животное" ->
                            changeMessage(messageId, chatId, "Отправьте кличку животного.", buttons.buttonMenu());
                    //блок “Прислать отчет о питомце”
                    case "Форма ежедневного отчета" -> {
                        takeDailyReportFormPhoto(chatId);
                        photoCheckButton = true; // Устанавливаем флаг в true после нажатия кнопки
                    }
                    case "Прислать отчет о питомце" -> petReportSelection(messageId, chatId);

                    //блок Волонтера
                    case "Отчеты" -> {
                        reviewListOfReports(update.callbackQuery().message().chat().id());
//                        sendImageFromFileId(String.valueOf(update.callbackQuery().message().chat().id()));
                    }
                    case "Отчет сдан" -> {
                        reportSubmitted(update);
                        reviewListOfReports(update.callbackQuery().message().chat().id());
                    }


                }
                if (photoCheckButton) { // Проверяем флаг перед выполнением checkDailyReport(update) и проверяеем, что пользователь прислал фото
                    if (!(update.message() == null)) {
                        PhotoSize photoSize = getPhoto(update);
                        File file = downloadPhoto(photoSize.fileId());

//                        savePhotoToLocalFolder(file, update);TODO
                        checkDailyReportPhoto(update);
                        photoCheckButton = false;
                        reportCheckButton = true;
                    }
                }
                if (reportCheckButton) { // Проверяем флаг перед выполнением checkDailyReport(update)
                    // и проверяеем, что пользователь прислал текст отчета
                    if (!(update.message() == null)) {
                        checkDailyReportMessage(update);
                        reportCheckButton = false;
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
     * @param chatIdInButton
     * @param messageText    метод для изменения сообщения
     */
    @Override
    public void changeMessage(long chatIdInButton, String messageText) {
        logger.info("Был вызван метод для изменения сообщения ", chatIdInButton, messageText);
//        EditMessageText editMessageText = new EditMessageText(chatIdInButton,, messageText);
        SendMessage sendMessage = new SendMessage(chatIdInButton, messageText);
        telegramBot.execute(sendMessage);
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
        logger.info("Был вызван метод получения информации по ключу", chatId, infoMap, key, keyboardMarkup );
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
     * @param update
     * Отправка запроса на подтверждение выбора животного волонтером
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
    //Если отчет сдан
    private void reportSubmitted(Update update) {
        logger.info("Был вызван метод для отправки сообщения Отчет сдан", update);
        changeMessage(update.callbackQuery().message().chat().id(), "Отчет сдан");
        System.out.println(sendMessageReport);
        volunteerService.reportSubmitted((long) sendMessageReport);
    }


    /**
     * @param chatId запрос у пользователя "Прислать фото питомца"
     */
    private void takeDailyReportFormPhoto(Long chatId) {
        logger.info("Был вызван метод для отправки сообщения Пришлите фото питомца", chatId);
        sendMessage(chatId, "Пришлите фото питомца");

    }
    /**
     * @param chatId
     * @param messageText метод для отправки кнопок Волонтера для работы с отчетами
     */
    @Override
    public void sendButtonOfVolunteerForReports(Long chatId, String messageText) {
        logger.info("Был вызван метод для отправки кнопок Выбора животного", chatId, messageText);
        SendMessage sendMessage = new SendMessage(chatId, messageText);
        sendMessage.replyMarkup(buttons.buttonsOfVolunteerForReports());
        telegramBot.execute(sendMessage);
    }
    /**
     * Извлекает из update список объектов PhotoSize, которые представляют разный размер фотографий
     * Через стрим ищет самую большую фотографию и возвращает её.
     */
    public PhotoSize getPhoto(Update update) {
        logger.info("Был вызван метод для поиска самой большой фотографии", update);
        if (!(update.message() == null) && !(update.message().photo() == null)) {
            List<PhotoSize> photos = Arrays.stream(update.message().photo()).toList();
            return photos.stream().max(Comparator.comparing(PhotoSize::fileSize)).orElse(null);


        }
        return null;
    }

    /**
     * Получаеем объект File содержащий информацию о файле по его индефикатору.
     */
    private File downloadPhoto(String fileId) {
        logger.info("Был вызван метод для получения файла по его id", fileId);
        GetFile getFile = new GetFile(fileId);
        return telegramBot.execute(getFile).file();


    }
    /**
     * Скачиваем файл, генерируем уникальное имя для него,
     * перемещаем в целевую директорию и возвращаем путь к сохраненному файлу
     */
    private Path savePhotoToLocalFolder(File file, Update update) throws IOException {//TODO
        logger.info("Был вызван метод для скачивания файла, присвоения ему имени," +
                " перемещения в директорию и возвращения пути к нему", file, update);
        PhotoSize photoSize = getPhoto(update);
        File photo = telegramBot.execute(new GetFile(photoSize.fileId())).file();
        String filePath = photo.filePath();

        // Генерируем уникальное имя файла с сохранением расширения
        namePhotoId = photoSize.fileId() + "." + "jpg";
        Path targetPath = Path.of("src/main/resources/pictures", namePhotoId);
        userService.saveUser(update, true);
//        reportService.saveReportPhotoId(update, namePhotoId);
        Files.move(Path.of(filePath), targetPath, StandardCopyOption.REPLACE_EXISTING);
        return targetPath;
    }
    /**
     * @param update проверка что пользователь прислал текстовую часть отчета и сохроняем в БД
     */

    private void checkDailyReportMessage(Update update) {
        logger.info("Был вызван метод для приверки, что пользователь сдал текстовую часть отчета", update);
        long chatId = update.message().chat().id();
        reportService.dailyReportCheckMessage(chatId, update, namePhotoId);

    }

    /**
     * @param update проверка что пользователь прислал фото для отчета и сохроняем в БД
     */
    private void checkDailyReportPhoto(Update update) {
        logger.info("Был вызван метод для проверки, что пользователь прислал фото для отчета " +
                "и сохранения его в бд", update);
        long chatId = update.message().chat().id();
        reportService.dailyReportCheckPhoto(chatId, update);
    }

    //метод кнопки "Прислать отчет о питомце"
    private void petReportSelection(int messageId, long chatId) {
        logger.info("Был вызван метод для отправки кнопок Прислать отчет о питомце", messageId,chatId);
        changeMessage(messageId, chatId, "Выберите одну из кнопок", buttons.buttonsOfOwner());
    }
    @Override
    /**
     * Получаем непроверенный отчет из всех отчетов
     * @return возвращает первый непроверенный отчет и кнопки действия с отчетом
     */
    public void reviewListOfReports(Long chatIdOfVolunteer) {
        logger.info("Был вызван метод для получения непроверенных отчетов", chatIdOfVolunteer);
        List<Report> reportList = reportRepository.findReportByCheckReportIsFalse();
        if (reportList.isEmpty()) {
            sendMessage(chatIdOfVolunteer, "Нет непроверенных отчетов.");


        } else {
            for (Report report : reportList) {
                sendMessage(chatIdOfVolunteer, "Отчет #" + report.getId() + "\n" +
                        "Текстовая часть отчета: " + report.getGeneralWellBeing());
                sendButtonOfVolunteerForReports(chatIdOfVolunteer,"Необходимо проверить отчет!");


            }
        }

    }
}


