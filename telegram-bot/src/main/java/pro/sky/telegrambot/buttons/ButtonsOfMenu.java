package pro.sky.telegrambot.buttons;

import com.pengrad.telegrambot.model.request.InlineKeyboardButton;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.pengrad.telegrambot.model.request.Keyboard;
import liquibase.pro.packaged.B;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import pro.sky.telegrambot.model.Buttons;

import java.util.List;

/**
 * class for creating buttons of start menu
 */
@Component
public class ButtonsOfMenu {
    private final Logger logger = LoggerFactory.getLogger(ButtonsOfMenu.class);

    public InlineKeyboardMarkup buttonMenu() {
        logger.info("Был вызван метод создания кнопки Меню");

        InlineKeyboardButton menuButton = new InlineKeyboardButton(Buttons.MENU.getCode());
        menuButton.callbackData(Buttons.MENU.getCode());

        return new InlineKeyboardMarkup(new InlineKeyboardButton[]{menuButton});
    }

    public InlineKeyboardMarkup buttonsOfStart() {
        logger.info("Был вызван метод создания кнопок 0 этапа");

        InlineKeyboardButton checkInfoButton = new InlineKeyboardButton(Buttons.CHECK_INFO.getCode());
        InlineKeyboardButton callVolunteerButton = new InlineKeyboardButton(Buttons.CALL_VOLUNTEER.getCode());
        InlineKeyboardButton getReportAboutPetButton = new InlineKeyboardButton(Buttons.GET_REPORT_ABOUT_PET.getCode());
        InlineKeyboardButton howGetPetButton = new InlineKeyboardButton(Buttons.HOW_GET_PET.getCode());
        InlineKeyboardButton toStartButton = new InlineKeyboardButton(Buttons.TO_START.getCode());

        checkInfoButton.callbackData(Buttons.CHECK_INFO.getCode());
        callVolunteerButton.callbackData(Buttons.CALL_VOLUNTEER.getCode());
        getReportAboutPetButton.callbackData(Buttons.GET_REPORT_ABOUT_PET.getCode());
        howGetPetButton.callbackData(Buttons.HOW_GET_PET.getCode());
        toStartButton.callbackData(Buttons.TO_START.getCode());

        return new InlineKeyboardMarkup(new InlineKeyboardButton[]{checkInfoButton}, new InlineKeyboardButton[]{getReportAboutPetButton}, new InlineKeyboardButton[]{howGetPetButton}, new InlineKeyboardButton[]{callVolunteerButton}, new InlineKeyboardButton[]{toStartButton});
    }

    public InlineKeyboardMarkup buttonsInformationAboutShelter() {
        logger.info("Был вызван метод создания кнопок Инфомация о приюте");

        InlineKeyboardButton aboutShelterButton = new InlineKeyboardButton(Buttons.ABOUT_SHELTER.getCode());
        InlineKeyboardButton callVolunteerButton = new InlineKeyboardButton(Buttons.CALL_VOLUNTEER.getCode());
        InlineKeyboardButton timetableButton = new InlineKeyboardButton(Buttons.TIMETABLE.getCode());
        InlineKeyboardButton addressButton = new InlineKeyboardButton(Buttons.ADDRESS.getCode());
        InlineKeyboardButton locationMapButton = new InlineKeyboardButton(Buttons.LOCATION_MAP.getCode());
        InlineKeyboardButton securityButton = new InlineKeyboardButton(Buttons.SECURITY_PHONE.getCode());
        InlineKeyboardButton safetyButton = new InlineKeyboardButton(Buttons.SAFETY_RULES.getCode());
        InlineKeyboardButton leavePhoneNumberButton = new InlineKeyboardButton(Buttons.LEAVE_PHONE_NUMBER.getCode());
        InlineKeyboardButton toStartButton = new InlineKeyboardButton(Buttons.TO_START.getCode());

        aboutShelterButton.callbackData(Buttons.ABOUT_SHELTER.getCode());
        callVolunteerButton.callbackData(Buttons.CALL_VOLUNTEER.getCode());
        timetableButton.callbackData(Buttons.TIMETABLE.getCode());
        addressButton.callbackData(Buttons.ADDRESS.getCode());
        locationMapButton.callbackData(Buttons.LOCATION_MAP.getCode());
        securityButton.callbackData(Buttons.SECURITY_PHONE.getCode());
        safetyButton.callbackData(Buttons.SAFETY_RULES.getCode());
        leavePhoneNumberButton.callbackData(Buttons.LEAVE_PHONE_NUMBER.getCode());
        toStartButton.callbackData(Buttons.TO_START.getCode());

        return new InlineKeyboardMarkup(new InlineKeyboardButton[]{aboutShelterButton}, new InlineKeyboardButton[]{timetableButton}, new InlineKeyboardButton[]{addressButton}, new InlineKeyboardButton[]{locationMapButton}, new InlineKeyboardButton[]{securityButton}, new InlineKeyboardButton[]{safetyButton}, new InlineKeyboardButton[]{leavePhoneNumberButton}, new InlineKeyboardButton[]{callVolunteerButton}, new InlineKeyboardButton[]{toStartButton});
    }

    public InlineKeyboardMarkup takeAnimalButton() {
        logger.info("Был вызван метод создания кнопок Как взять животное из приюта");

        InlineKeyboardButton datingRulesButton = new InlineKeyboardButton(Buttons.DATING_RULES.getCode());
        InlineKeyboardButton listOfDocumentsButton = new InlineKeyboardButton(Buttons.LIST_OF_DOCUMENTS.getCode());
        InlineKeyboardButton transportationButton = new InlineKeyboardButton(Buttons.TRANSPORTATION.getCode());
        InlineKeyboardButton arrangementPuppyButton = new InlineKeyboardButton(Buttons.ARRANGEMENT_PUPPY.getCode());
        InlineKeyboardButton arrangementAdultButton = new InlineKeyboardButton(Buttons.ARRANGEMENT_ADULT.getCode());
        InlineKeyboardButton arrangementDisabledButton = new InlineKeyboardButton(Buttons.ARRANGEMENT_DISABLED.getCode());
        InlineKeyboardButton whyRefuseButton = new InlineKeyboardButton(Buttons.WHY_REFUSE.getCode());
        InlineKeyboardButton chooseAnimalButton = new InlineKeyboardButton(Buttons.CHOOSE_ANIMAL.getCode());
        InlineKeyboardButton callVolunteerButton = new InlineKeyboardButton(Buttons.CALL_VOLUNTEER.getCode());
        InlineKeyboardButton toStartButton = new InlineKeyboardButton(Buttons.TO_START.getCode());

        datingRulesButton.callbackData(Buttons.DATING_RULES.getCode());
        listOfDocumentsButton.callbackData(Buttons.LIST_OF_DOCUMENTS.getCode());
        transportationButton.callbackData(Buttons.TRANSPORTATION.getCode());
        arrangementPuppyButton.callbackData(Buttons.ARRANGEMENT_PUPPY.getCode());
        arrangementAdultButton.callbackData(Buttons.ARRANGEMENT_ADULT.getCode());
        arrangementDisabledButton.callbackData(Buttons.ARRANGEMENT_DISABLED.getCode());
        whyRefuseButton.callbackData(Buttons.WHY_REFUSE.getCode());
        chooseAnimalButton.callbackData(Buttons.CHOOSE_ANIMAL.getCode());
        callVolunteerButton.callbackData(Buttons.CALL_VOLUNTEER.getCode());
        toStartButton.callbackData(Buttons.TO_START.getCode());

        return new InlineKeyboardMarkup(new InlineKeyboardButton[]{datingRulesButton}, new InlineKeyboardButton[]{listOfDocumentsButton}, new InlineKeyboardButton[]{arrangementDisabledButton}, new InlineKeyboardButton[]{whyRefuseButton}, new InlineKeyboardButton[]{arrangementPuppyButton}, new InlineKeyboardButton[]{arrangementAdultButton}, new InlineKeyboardButton[]{transportationButton}, new InlineKeyboardButton[]{chooseAnimalButton}, new InlineKeyboardButton[]{callVolunteerButton}, new InlineKeyboardButton[]{toStartButton});
    }

    /**
     * Кнопки  волонтерской панели
     */
    public InlineKeyboardMarkup buttonsOfVolunteer() {
        logger.info("Был вызван метод создания кнопок Волонтера");

        InlineKeyboardButton reportButton = new InlineKeyboardButton(Buttons.REPORT.getCode());
        InlineKeyboardButton toStartButton = new InlineKeyboardButton(Buttons.TO_START.getCode());

        reportButton.callbackData(Buttons.REPORT.getCode());
        toStartButton.callbackData(Buttons.TO_START.getCode());

        return new InlineKeyboardMarkup(new InlineKeyboardButton[]{reportButton}, new InlineKeyboardButton[]{toStartButton});
    }

    public InlineKeyboardMarkup buttonOfChooseAnimal() {
        logger.info("Был вызван метод создания кнопок Выбрать животное");

        InlineKeyboardButton tookButton = new InlineKeyboardButton(Buttons.TOOK_ANIMAL.getCode());
        InlineKeyboardButton toStartButton = new InlineKeyboardButton(Buttons.TO_START.getCode());

        tookButton.callbackData(Buttons.TOOK_ANIMAL.getCode());
        toStartButton.callbackData(Buttons.TO_START.getCode());

        return new InlineKeyboardMarkup(new InlineKeyboardButton[]{tookButton});
    }
}

