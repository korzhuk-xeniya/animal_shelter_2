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

        InlineKeyboardButton menuButton = new InlineKeyboardButton(Buttons.MENU.getButtonName());
        menuButton.callbackData(Buttons.MENU.getButtonName());

        return new InlineKeyboardMarkup(new InlineKeyboardButton[]{menuButton});
    }

    public InlineKeyboardMarkup buttonsOfStart() {
        logger.info("Был вызван метод создания кнопок 0 этапа");

        InlineKeyboardButton checkInfoButton = new InlineKeyboardButton(Buttons.CHECK_INFO.getButtonName());
        InlineKeyboardButton callVolunteerButton = new InlineKeyboardButton(Buttons.CALL_VOLUNTEER.getButtonName());
        InlineKeyboardButton getReportAboutPetButton = new InlineKeyboardButton(Buttons.GET_REPORT_ABOUT_PET.getButtonName());
        InlineKeyboardButton howGetPetButton = new InlineKeyboardButton(Buttons.HOW_GET_PET.getButtonName());
        InlineKeyboardButton toStartButton = new InlineKeyboardButton(Buttons.TO_START.getButtonName());

        checkInfoButton.callbackData(Buttons.CHECK_INFO.getButtonName());
        callVolunteerButton.callbackData(Buttons.CALL_VOLUNTEER.getButtonName());
        getReportAboutPetButton.callbackData(Buttons.GET_REPORT_ABOUT_PET.getButtonName());
        howGetPetButton.callbackData(Buttons.HOW_GET_PET.getButtonName());
        toStartButton.callbackData(Buttons.TO_START.getButtonName());

        return new InlineKeyboardMarkup(new InlineKeyboardButton[]{checkInfoButton}, new InlineKeyboardButton[]{getReportAboutPetButton}, new InlineKeyboardButton[]{howGetPetButton}, new InlineKeyboardButton[]{callVolunteerButton}, new InlineKeyboardButton[]{toStartButton});
    }

    public InlineKeyboardMarkup buttonsInformationAboutShelter() {
        logger.info("Был вызван метод создания кнопок Инфомация о приюте");

        InlineKeyboardButton aboutShelterButton = new InlineKeyboardButton(Buttons.ABOUT_SHELTER.getButtonName());
        InlineKeyboardButton callVolunteerButton = new InlineKeyboardButton(Buttons.CALL_VOLUNTEER.getButtonName());
        InlineKeyboardButton timetableButton = new InlineKeyboardButton(Buttons.TIMETABLE.getButtonName());
        InlineKeyboardButton addressButton = new InlineKeyboardButton(Buttons.ADDRESS.getButtonName());
        InlineKeyboardButton locationMapButton = new InlineKeyboardButton(Buttons.LOCATION_MAP.getButtonName());
        InlineKeyboardButton securityButton = new InlineKeyboardButton(Buttons.SECURITY_PHONE.getButtonName());
        InlineKeyboardButton safetyButton = new InlineKeyboardButton(Buttons.SAFETY_RULES.getButtonName());
        InlineKeyboardButton leavePhoneNumberButton = new InlineKeyboardButton(Buttons.LEAVE_PHONE_NUMBER.getButtonName());
        InlineKeyboardButton toStartButton = new InlineKeyboardButton(Buttons.TO_START.getButtonName());

        aboutShelterButton.callbackData(Buttons.ABOUT_SHELTER.getButtonName());
        callVolunteerButton.callbackData(Buttons.CALL_VOLUNTEER.getButtonName());
        timetableButton.callbackData(Buttons.TIMETABLE.getButtonName());
        addressButton.callbackData(Buttons.ADDRESS.getButtonName());
        locationMapButton.callbackData(Buttons.LOCATION_MAP.getButtonName());
        securityButton.callbackData(Buttons.SECURITY_PHONE.getButtonName());
        safetyButton.callbackData(Buttons.SAFETY_RULES.getButtonName());
        leavePhoneNumberButton.callbackData(Buttons.LEAVE_PHONE_NUMBER.getButtonName());
        toStartButton.callbackData(Buttons.TO_START.getButtonName());

        return new InlineKeyboardMarkup(new InlineKeyboardButton[]{aboutShelterButton}, new InlineKeyboardButton[]{timetableButton}, new InlineKeyboardButton[]{addressButton}, new InlineKeyboardButton[]{locationMapButton}, new InlineKeyboardButton[]{securityButton}, new InlineKeyboardButton[]{safetyButton}, new InlineKeyboardButton[]{leavePhoneNumberButton}, new InlineKeyboardButton[]{callVolunteerButton}, new InlineKeyboardButton[]{toStartButton});
    }

    public InlineKeyboardMarkup takeAnimalButton() {
        logger.info("Был вызван метод создания кнопок Как взять животное из приюта");

        InlineKeyboardButton datingRulesButton = new InlineKeyboardButton(Buttons.DATING_RULES.getButtonName());
        InlineKeyboardButton listOfDocumentsButton = new InlineKeyboardButton(Buttons.LIST_OF_DOCUMENTS.getButtonName());
        InlineKeyboardButton transportationButton = new InlineKeyboardButton(Buttons.TRANSPORTATION.getButtonName());
        InlineKeyboardButton arrangementPuppyButton = new InlineKeyboardButton(Buttons.ARRANGEMENT_PUPPY.getButtonName());
        InlineKeyboardButton arrangementAdultButton = new InlineKeyboardButton(Buttons.ARRANGEMENT_ADULT.getButtonName());
        InlineKeyboardButton arrangementDisabledButton = new InlineKeyboardButton(Buttons.ARRANGEMENT_DISABLED.getButtonName());
        InlineKeyboardButton whyRefuseButton = new InlineKeyboardButton(Buttons.WHY_REFUSE.getButtonName());
        InlineKeyboardButton chooseAnimalButton = new InlineKeyboardButton(Buttons.CHOOSE_ANIMAL.getButtonName());
        InlineKeyboardButton callVolunteerButton = new InlineKeyboardButton(Buttons.CALL_VOLUNTEER.getButtonName());
        InlineKeyboardButton toStartButton = new InlineKeyboardButton(Buttons.TO_START.getButtonName());

        datingRulesButton.callbackData(Buttons.DATING_RULES.getButtonName());
        listOfDocumentsButton.callbackData(Buttons.LIST_OF_DOCUMENTS.getButtonName());
        transportationButton.callbackData(Buttons.TRANSPORTATION.getButtonName());
        arrangementPuppyButton.callbackData(Buttons.ARRANGEMENT_PUPPY.getButtonName());
        arrangementAdultButton.callbackData(Buttons.ARRANGEMENT_ADULT.getButtonName());
        arrangementDisabledButton.callbackData(Buttons.ARRANGEMENT_DISABLED.getButtonName());
        whyRefuseButton.callbackData(Buttons.WHY_REFUSE.getButtonName());
        chooseAnimalButton.callbackData(Buttons.CHOOSE_ANIMAL.getButtonName());
        callVolunteerButton.callbackData(Buttons.CALL_VOLUNTEER.getButtonName());
        toStartButton.callbackData(Buttons.TO_START.getButtonName());

        return new InlineKeyboardMarkup(new InlineKeyboardButton[]{datingRulesButton}, new InlineKeyboardButton[]{listOfDocumentsButton}, new InlineKeyboardButton[]{arrangementDisabledButton}, new InlineKeyboardButton[]{whyRefuseButton}, new InlineKeyboardButton[]{arrangementPuppyButton}, new InlineKeyboardButton[]{arrangementAdultButton}, new InlineKeyboardButton[]{transportationButton}, new InlineKeyboardButton[]{chooseAnimalButton}, new InlineKeyboardButton[]{callVolunteerButton}, new InlineKeyboardButton[]{toStartButton});
    }

    /**
     * Кнопки  волонтерской панели
     */
    public InlineKeyboardMarkup buttonsOfVolunteer() {
        logger.info("Был вызван метод создания кнопок Волонтера");

        InlineKeyboardButton reportButton = new InlineKeyboardButton(Buttons.REPORT.getButtonName());
        InlineKeyboardButton toStartButton = new InlineKeyboardButton(Buttons.TO_START.getButtonName());

        reportButton.callbackData(Buttons.REPORT.getButtonName());
        toStartButton.callbackData(Buttons.TO_START.getButtonName());

        return new InlineKeyboardMarkup(new InlineKeyboardButton[]{reportButton}, new InlineKeyboardButton[]{toStartButton});
    }

    public InlineKeyboardMarkup buttonOfChooseAnimal() {
        logger.info("Был вызван метод создания кнопок Выбрать животное");

        InlineKeyboardButton tookButton = new InlineKeyboardButton(Buttons.TOOK_ANIMAL.getButtonName());
        InlineKeyboardButton toStartButton = new InlineKeyboardButton(Buttons.TO_START.getButtonName());

        tookButton.callbackData(Buttons.TOOK_ANIMAL.getButtonName());
        toStartButton.callbackData(Buttons.TO_START.getButtonName());

        return new InlineKeyboardMarkup(new InlineKeyboardButton[]{tookButton});
    }
}

