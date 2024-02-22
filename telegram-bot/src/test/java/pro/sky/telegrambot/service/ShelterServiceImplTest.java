package pro.sky.telegrambot.service;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.request.SendMessage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pro.sky.telegrambot.buttons.ButtonsOfMenu;
import pro.sky.telegrambot.repository.ShelterRepository;
import pro.sky.telegrambot.repository.UserRepository;
import pro.sky.telegrambot.repository.VolunteerRepository;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ShelterServiceImplTest {
    @Mock
    TelegramBot telegramBot;
    @InjectMocks
    ShelterServiceImpl shelterServiceImpl;
    @Mock
    ShelterRepository repository;
    @Mock
    ButtonsOfMenu buttons;
    @Mock
    VolunteerRepository volunteerRepository;
    @Mock
    UserRepository userRepository;
    @Mock
    UserService userService;
    @Mock
    VolunteerService volunteerService;
//    private static final Pattern MESSAGE_PATTERN = Pattern.compile("^(\\+7)([0-9]{10})$");
//    private final ObjectMapper objectMapper;
    @Mock
    AnimalService animalService;
    @Mock
    ReportAboutAnimalService reportAboutAnimalService;
    // константы для тестов
    private String expectedMessageText = "EXPECTED_MESSAGE_TEXT";
    private Long chatId= 1L;
    private SendMessage expectedMessage = new SendMessage(chatId,expectedMessageText);

    @Test
    @DisplayName("")
    void process() {
    }
//тут будут остальные тесты на process
//    @Test
//    @DisplayName("")
//    void process_1() {
//    }
//
//    @Test
//    @DisplayName("")
//    void process_2() {
//    }
//
//    @Test
//    @DisplayName("")
//    void process_3() {
//    }
//
//    @Test
//    @DisplayName("")
//    void process_4() {
//    }
//
//    @Test
//    @DisplayName("")
//    void process_5() {
//    }
//
//    @Test
//    @DisplayName("")
//    void process_6() {
//    }
//
//    @Test
//    @DisplayName("")
//    void process_7() {
//    }
//
//    @Test
//    @DisplayName("")
//    void process_8() {
//    }
//
//    @Test
//    @DisplayName("")
//    void process_9() {
//    }
//
//    @Test
//    @DisplayName("")
//    void process_10() {
//    }
//
//    @Test
//    @DisplayName("")
//    void process_11() {
//    }
//
//    @Test
//    @DisplayName("")
//    void process_12() {
//    }
//
//    @Test
//    @DisplayName("")
//    void process_13() {
//    }
//
//    @Test
//    @DisplayName("")
//    void process_14() {
//    }
//
//    @Test
//    @DisplayName("")
//    void process_15() {
//    }
//
//    @Test
//    @DisplayName("")
//    void process_16() {
//    }
//
//    @Test
//    @DisplayName("")
//    void process_17() {
//    }
//
//    @Test
//    @DisplayName("")
//    void process_18() {
//    }
//
//    @Test
//    @DisplayName("")
//    void process_19() {
//    }
//
//    @Test
//    @DisplayName("")
//    void process_20() {
//    }

    @Test
    @DisplayName("Должен отработать метод sendMessage")
    void sendMessage() {
        doNothing().when(telegramBot.execute(expectedMessage)).notifyAll();
//      test
        shelterServiceImpl.sendMessage(chatId, expectedMessageText);
//      check
        verify(telegramBot,only()).execute(expectedMessage);
    }

    @Test
    @DisplayName("")
    void sendMenuButton() {
    }

    @Test
    @DisplayName("")
    void sendMenuVolunteer() {
    }

    @Test
    @DisplayName("")
    void sendButtonsOfStep0() {
    }

    @Test
    @DisplayName("")
    void changeMessage() {
    }
    @Test
    @DisplayName("")
    boolean isStartEntered(){return true;}
    @Test
    @DisplayName("")
    void callAVolunteer() {
    }

    @Test
    @DisplayName("")
    void sendMessageByKey() {
    }

    @Test
    @DisplayName("")
    void getInfo() {
    }

    @Test
    @DisplayName("")
    void callAVolunteerForConfirmationOfSelection() {
    }

    @Test
    @DisplayName("")
    void sendButtonChooseAnimal() {
    }
}