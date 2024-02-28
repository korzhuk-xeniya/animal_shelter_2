package pro.sky.telegrambot.buttons;

import com.pengrad.telegrambot.model.request.InlineKeyboardButton;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import pro.sky.telegrambot.model.Button;

/**
 * class for creating buttons of start menu
 */
@Component
public class ButtonsOfMenu {
    private final Logger logger = LoggerFactory.getLogger(ButtonsOfMenu.class);

    public InlineKeyboardMarkup buttonMenu() {
        logger.info("Был вызван метод создания кнопки Меню");

        InlineKeyboardButton menuButton = new InlineKeyboardButton(Button.MENU.getCode());
        menuButton.callbackData(Button.MENU.getCode());

        return new InlineKeyboardMarkup(new InlineKeyboardButton[]{menuButton});
    }

    public InlineKeyboardMarkup buttonsOfStart() {
        logger.info("Был вызван метод создания кнопок 0 этапа");

        InlineKeyboardButton checkInfoButton = new InlineKeyboardButton(Button.CHECK_INFO.getCode());
        InlineKeyboardButton callVolunteerButton = new InlineKeyboardButton(Button.CALL_VOLUNTEER.getCode());
        InlineKeyboardButton getReportAboutPet = new InlineKeyboardButton(Button.GET_REPORT_ABOUT_PET.getCode());
        InlineKeyboardButton howGetPet = new InlineKeyboardButton(Button.HOW_GET_PET.getCode());
        InlineKeyboardButton toStart = new InlineKeyboardButton(Button.TO_START.getCode());

        checkInfoButton.callbackData(Button.CHECK_INFO.getCode());
        callVolunteerButton.callbackData(Button.CALL_VOLUNTEER.getCode());
        getReportAboutPet.callbackData(Button.GET_REPORT_ABOUT_PET.getCode());
        howGetPet.callbackData(Button.HOW_GET_PET.getCode());
        toStart.callbackData(Button.TO_START.getCode());

        return new InlineKeyboardMarkup(new InlineKeyboardButton[]{checkInfoButton},
                new InlineKeyboardButton[]{getReportAboutPet},
                new InlineKeyboardButton[]{howGetPet},
                new InlineKeyboardButton[]{callVolunteerButton},
                new InlineKeyboardButton[]{toStart});
    }

    public InlineKeyboardMarkup buttonsInformationAboutShelter() {
        logger.info("Был вызван метод создания кнопок Инфомация о приюте");

        InlineKeyboardButton aboutShelterButton = new InlineKeyboardButton(Button.ABOUT_SHELTER.getCode());
        InlineKeyboardButton callVolunteerButton = new InlineKeyboardButton(Button.CALL_VOLUNTEER.getCode());
        InlineKeyboardButton timetableButton = new InlineKeyboardButton(Button.TIMETABLE.getCode());
        InlineKeyboardButton addressButton = new InlineKeyboardButton(Button.ADDRESS.getCode());
        InlineKeyboardButton locationMapButton = new InlineKeyboardButton(Button.LOCATION_MAP.getCode());
        InlineKeyboardButton securityButton = new InlineKeyboardButton(Button.SECURITY_PHONE.getCode());
        InlineKeyboardButton safetyButton = new InlineKeyboardButton(Button.SAFETY_RULES.getCode());
        InlineKeyboardButton leavePhoneNumberButton = new InlineKeyboardButton(Button.LEAVE_PHONE_NUMBER.getCode());
        InlineKeyboardButton toStart = new InlineKeyboardButton(Button.TO_START.getCode());

        aboutShelterButton.callbackData(Button.ABOUT_SHELTER.getCode());
        callVolunteerButton.callbackData(Button.CALL_VOLUNTEER.getCode());
        timetableButton.callbackData(Button.TIMETABLE.getCode());
        addressButton.callbackData(Button.ADDRESS.getCode());
        locationMapButton.callbackData(Button.LOCATION_MAP.getCode());
        securityButton.callbackData(Button.SECURITY_PHONE.getCode());
        safetyButton.callbackData(Button.SAFETY_RULES.getCode());
        leavePhoneNumberButton.callbackData(Button.LEAVE_PHONE_NUMBER.getCode());
        toStart.callbackData(Button.TO_START.getCode());

        return new InlineKeyboardMarkup(new InlineKeyboardButton[]{aboutShelterButton},
                new InlineKeyboardButton[]{timetableButton},
                new InlineKeyboardButton[]{addressButton},
                new InlineKeyboardButton[]{locationMapButton},
                new InlineKeyboardButton[]{securityButton},
                new InlineKeyboardButton[]{safetyButton},
                new InlineKeyboardButton[]{leavePhoneNumberButton},
                new InlineKeyboardButton[]{callVolunteerButton},
                new InlineKeyboardButton[]{toStart}
        );
    }

    public InlineKeyboardMarkup takeAnimalButton() {
        logger.info("Был вызван метод создания кнопок Как взять животное из приюта");

        InlineKeyboardButton datingRulesButton = new InlineKeyboardButton(Button.DATING_RULES.getCode());
        InlineKeyboardButton listOfDocumentsButton = new InlineKeyboardButton(Button.LIST_OF_DOCUMENTS.getCode());
        InlineKeyboardButton transportationButton = new InlineKeyboardButton(Button.TRANSPORTATION.getCode());
        InlineKeyboardButton arrangementPuppyButton = new InlineKeyboardButton(Button.ARRANGEMENT_PUPPY.getCode());
        InlineKeyboardButton arrangementAdultButton = new InlineKeyboardButton(Button.ARRANGEMENT_ADULT.getCode());
        InlineKeyboardButton arrangementDisabledButton = new InlineKeyboardButton(Button.ARRANGEMENT_DISABLED.getCode());
        InlineKeyboardButton whyRefuseButton = new InlineKeyboardButton(Button.WHY_REFUSE.getCode());
        InlineKeyboardButton chooseAnimalButton = new InlineKeyboardButton(Button.CHOOSE_ANIMAL.getCode());
        InlineKeyboardButton callVolunteerButton = new InlineKeyboardButton(Button.CALL_VOLUNTEER.getCode());
        InlineKeyboardButton toStart = new InlineKeyboardButton(Button.TO_START.getCode());

        datingRulesButton.callbackData(Button.DATING_RULES.getCode());
        listOfDocumentsButton.callbackData(Button.LIST_OF_DOCUMENTS.getCode());
        transportationButton.callbackData(Button.TRANSPORTATION.getCode());
        arrangementPuppyButton.callbackData(Button.ARRANGEMENT_PUPPY.getCode());
        arrangementAdultButton.callbackData(Button.ARRANGEMENT_ADULT.getCode());
        arrangementDisabledButton.callbackData(Button.ARRANGEMENT_DISABLED.getCode());
        whyRefuseButton.callbackData(Button.WHY_REFUSE.getCode());
        chooseAnimalButton.callbackData(Button.CHOOSE_ANIMAL.getCode());
        callVolunteerButton.callbackData(Button.CALL_VOLUNTEER.getCode());
        toStart.callbackData(Button.TO_START.getCode());

        return new InlineKeyboardMarkup(new InlineKeyboardButton[]{datingRulesButton},
                new InlineKeyboardButton[]{listOfDocumentsButton},
                new InlineKeyboardButton[]{arrangementDisabledButton},
                new InlineKeyboardButton[]{whyRefuseButton},
                new InlineKeyboardButton[]{arrangementPuppyButton},
//                new InlineKeyboardButton[]{arrangementAdultButton},
                new InlineKeyboardButton[]{transportationButton},
                new InlineKeyboardButton[]{chooseAnimalButton},
                new InlineKeyboardButton[]{callVolunteerButton},
                new InlineKeyboardButton[]{toStart});
    }

    /**
     * Кнопки  волонтерской панели
     */
    public InlineKeyboardMarkup buttonsOfVolunteer() {
        logger.info("Был вызван метод создания кнопок Волонтера");

        InlineKeyboardButton reportButton = new InlineKeyboardButton(Button.REPORT.getCode());
        InlineKeyboardButton reportsButton = new InlineKeyboardButton(Button.REPORTS.getCode());
        InlineKeyboardButton toStart = new InlineKeyboardButton(Button.TO_START.getCode());

        reportButton.callbackData(Button.REPORT.getCode());
        reportsButton.callbackData(Button.REPORTS.getCode());
        toStart.callbackData(Button.TO_START.getCode());

        return new InlineKeyboardMarkup(new InlineKeyboardButton[]{reportsButton},
                new InlineKeyboardButton[]{toStart});
    }

    public InlineKeyboardMarkup buttonOfChooseAnimal() {
        logger.info("Был вызван метод создания кнопок Выбрать животное");

        InlineKeyboardButton tookButton = new InlineKeyboardButton(Button.TOOK_ANIMAL.getCode());
        InlineKeyboardButton toStart = new InlineKeyboardButton(Button.TO_START.getCode());

        tookButton.callbackData(Button.TOOK_ANIMAL.getCode());
        toStart.callbackData(Button.TO_START.getCode());

        return new InlineKeyboardMarkup(new InlineKeyboardButton[]{tookButton});
    }

    public InlineKeyboardMarkup buttonsOfVolunteerForReports() {
        logger.info("Был вызван метод создания кнопок Волонтера для работы с отчетами");

        InlineKeyboardButton reportButtonOk = new InlineKeyboardButton(Button.REPORT_OK.getCode());
        InlineKeyboardButton reportButtonNotOk = new InlineKeyboardButton(Button.REPORTS_NOT_OK.getCode());
        InlineKeyboardButton toStart = new InlineKeyboardButton(Button.TO_START.getCode());

        reportButtonOk.callbackData(Button.REPORT_OK.getCode());
        reportButtonNotOk.callbackData(Button.REPORTS_NOT_OK.getCode());
        toStart.callbackData(Button.TO_START.getCode());

        return new InlineKeyboardMarkup(new InlineKeyboardButton[]{reportButtonOk, reportButtonNotOk},
                new InlineKeyboardButton[]{toStart});
    }

    public InlineKeyboardMarkup buttonsOfVolunteerForEnd30DaysPeriod() {
        logger.info("Был вызван метод создания кнопок Волонтера для принятия решения спустя 30 дней");

        InlineKeyboardButton probationPeriodCompletedButton = new InlineKeyboardButton(Button.PROBATION_PERIOD_COMPLETED.getCode());
        InlineKeyboardButton probationPeriodExtended14Button = new InlineKeyboardButton(Button.PROBATION_PERIOD_EXTENDED_14.getCode());
        InlineKeyboardButton probationPeriodExtended30Button = new InlineKeyboardButton(Button.PROBATION_PERIOD_EXTENDED_30.getCode());
        InlineKeyboardButton probationPeriodNotCompletedButton = new InlineKeyboardButton(Button.PROBATION_PERIOD_NOT_COMPLETED.getCode());

        probationPeriodCompletedButton.callbackData(Button.PROBATION_PERIOD_COMPLETED.getCode());
        probationPeriodExtended14Button.callbackData(Button.PROBATION_PERIOD_EXTENDED_14.getCode());
        probationPeriodExtended30Button.callbackData(Button.PROBATION_PERIOD_EXTENDED_30.getCode());
        probationPeriodNotCompletedButton.callbackData(Button.PROBATION_PERIOD_NOT_COMPLETED.getCode());

        return new InlineKeyboardMarkup(new InlineKeyboardButton[]{probationPeriodCompletedButton},
                new InlineKeyboardButton[]{probationPeriodExtended14Button},
                new InlineKeyboardButton[]{probationPeriodExtended30Button},
                new InlineKeyboardButton[]{probationPeriodNotCompletedButton}
        );
    }

    public InlineKeyboardMarkup buttonsOfOwner() {
        logger.info("Был вызван метод создания кнопок Владельца животного");

        InlineKeyboardButton dailyRepotrButton = new InlineKeyboardButton(Button.DAILY_REPORT.getCode());
        InlineKeyboardButton callVolunteerButton = new InlineKeyboardButton(Button.CALL_VOLUNTEER.getCode());
        InlineKeyboardButton toStart = new InlineKeyboardButton(Button.TO_START.getCode());

        dailyRepotrButton.callbackData(Button.DAILY_REPORT.getCode());
        callVolunteerButton.callbackData(Button.CALL_VOLUNTEER.getCode());
        toStart.callbackData(Button.TO_START.getCode());

        return new InlineKeyboardMarkup(new InlineKeyboardButton[]{dailyRepotrButton},
                new InlineKeyboardButton[]{callVolunteerButton},
                new InlineKeyboardButton[]{toStart});
    }
}

