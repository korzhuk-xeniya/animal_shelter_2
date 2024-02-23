package pro.sky.telegrambot.buttons;

import com.pengrad.telegrambot.model.request.InlineKeyboardButton;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.pengrad.telegrambot.model.request.Keyboard;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * class for creating buttons of start menu
 */
@Component
public class ButtonsOfMenu {
    private final Logger logger = LoggerFactory.getLogger(ButtonsOfMenu.class);

    public InlineKeyboardMarkup buttonMenu() {
        logger.info("Был вызван метод создания кнопки Меню");
        InlineKeyboardButton menuButton = new InlineKeyboardButton("Меню");
        menuButton.callbackData("Меню");
        return new InlineKeyboardMarkup(new InlineKeyboardButton[]{menuButton});
    }

    public InlineKeyboardMarkup buttonsOfStart() {
        logger.info("Был вызван метод создания кнопок 0 этапа");
        InlineKeyboardButton checkInfoButton = new InlineKeyboardButton("Информация о приюте");
        InlineKeyboardButton callVolunteerButton = new InlineKeyboardButton("Позвать волонтера");
        InlineKeyboardButton getReportAboutPet = new InlineKeyboardButton("Прислать отчет о питомце");
        InlineKeyboardButton howGetPet = new InlineKeyboardButton("Как взять животное из приюта?");
        InlineKeyboardButton toStart = new InlineKeyboardButton("В начало");
        checkInfoButton.callbackData("Информация о приюте");
        callVolunteerButton.callbackData("Позвать волонтера");
        getReportAboutPet.callbackData("Прислать отчет о питомце");
        howGetPet.callbackData("Как взять животное из приюта?");
        toStart.callbackData("В начало");

            return new InlineKeyboardMarkup(new InlineKeyboardButton[]{checkInfoButton},
                    new InlineKeyboardButton[]{getReportAboutPet},
                    new InlineKeyboardButton[]{howGetPet},
                    new InlineKeyboardButton[]{callVolunteerButton},
                    new InlineKeyboardButton[]{toStart});
        }
        public InlineKeyboardMarkup buttonsInformationAboutShelter() {
            logger.info("Был вызван метод создания кнопок Инфомация о приюте");
            InlineKeyboardButton aboutShelterButton = new InlineKeyboardButton("О приюте");
            InlineKeyboardButton callVolunteerButton = new InlineKeyboardButton("Позвать волонтера");
            InlineKeyboardButton timetableButton = new InlineKeyboardButton("График работы");
            InlineKeyboardButton addressButton = new InlineKeyboardButton("Адрес приюта");
            InlineKeyboardButton locationMapButton = new InlineKeyboardButton("Схема проезда");
            InlineKeyboardButton securityButton = new InlineKeyboardButton("Телефон охраны");
            InlineKeyboardButton safetyButton = new InlineKeyboardButton("Правила посещения приюта");
            InlineKeyboardButton leavePhoneNumberButton = new InlineKeyboardButton("Оставить телефон для связи");
            InlineKeyboardButton toStart = new InlineKeyboardButton("В начало");
            aboutShelterButton.callbackData("О приюте");
            callVolunteerButton.callbackData("Позвать волонтера");
            timetableButton.callbackData("График работы");
            addressButton.callbackData("Адрес приюта");
            locationMapButton.callbackData("Схема проезда");
            securityButton.callbackData("Телефон охраны");
            safetyButton.callbackData("Правила посещения приюта");
            leavePhoneNumberButton.callbackData("Оставить телефон для связи");
            toStart.callbackData("В начало");

        return new InlineKeyboardMarkup(new InlineKeyboardButton[]{aboutShelterButton}
                ,
                new InlineKeyboardButton[]{timetableButton}
                ,
                new InlineKeyboardButton[]{addressButton}
                ,
                new InlineKeyboardButton[]{locationMapButton}
                ,
                new InlineKeyboardButton[]{securityButton}
                ,
                new InlineKeyboardButton[]{safetyButton},
                new InlineKeyboardButton[]{leavePhoneNumberButton},
                new InlineKeyboardButton[]{callVolunteerButton},
                new InlineKeyboardButton[]{toStart}
        );
    }

    public InlineKeyboardMarkup takeAnimalButton() {
        logger.info("Был вызван метод создания кнопок Как взять животное из приюта");
        InlineKeyboardButton datingRulesButton = new InlineKeyboardButton("Правила знакомства");
        InlineKeyboardButton listOfDocumentsButton = new InlineKeyboardButton("Список документов");
        InlineKeyboardButton transportationButton = new InlineKeyboardButton("Рекомендации по транспортировке");
        InlineKeyboardButton arrangementPuppyButton = new InlineKeyboardButton("Обустройство щенка");
        InlineKeyboardButton arrangementAdultButton = new InlineKeyboardButton("Обустройство для взрослой собаки");
        InlineKeyboardButton arrangementDisabledButton = new InlineKeyboardButton("Животное с ОВЗ");
        InlineKeyboardButton whyRefuseButton = new InlineKeyboardButton("Причины отказа");
        InlineKeyboardButton chooseAnimalButton = new InlineKeyboardButton("Выбрать животное");
        InlineKeyboardButton callVolunteerButton = new InlineKeyboardButton("Позвать волонтера");
        InlineKeyboardButton toStart = new InlineKeyboardButton("В начало");

        arrangementAdultButton.callbackData("Обустройство для взрослой собаки");
        arrangementPuppyButton.callbackData("Обустройство щенка");
        datingRulesButton.callbackData("Правила знакомства");
        listOfDocumentsButton.callbackData("Список документов");
        transportationButton.callbackData("Рекомендации по транспортировке");
        arrangementDisabledButton.callbackData("Животное с ОВЗ");
        whyRefuseButton.callbackData("Причины отказа");
        chooseAnimalButton.callbackData("Выбрать животное");
        callVolunteerButton.callbackData("Позвать волонтера");
        toStart.callbackData("В начало");
        return new InlineKeyboardMarkup(new InlineKeyboardButton[]{datingRulesButton},
                new InlineKeyboardButton[]{listOfDocumentsButton},
                new InlineKeyboardButton[]{arrangementDisabledButton},
                new InlineKeyboardButton[]{whyRefuseButton},
                new InlineKeyboardButton[]{arrangementPuppyButton},
                new InlineKeyboardButton[]{arrangementAdultButton},
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
        InlineKeyboardButton reportButton = new InlineKeyboardButton("Просмотр отчетов");
        InlineKeyboardButton reportsButton = new InlineKeyboardButton("Отчеты");
        InlineKeyboardButton toStart = new InlineKeyboardButton("В начало");
        reportButton.callbackData("Просмотр отчетов");
        reportsButton.callbackData("Отчеты");

        toStart.callbackData("В начало");
        return new InlineKeyboardMarkup(new InlineKeyboardButton[]{reportsButton},
                new InlineKeyboardButton[]{toStart});
    }
    public InlineKeyboardMarkup buttonOfChooseAnimal() {
        logger.info("Был вызван метод создания кнопок Выбрать животное");
        InlineKeyboardButton tookButton = new InlineKeyboardButton("Взять животное");
        InlineKeyboardButton toStart = new InlineKeyboardButton("В начало");
        tookButton.callbackData("Взять животное");
        toStart.callbackData("В начало");
        return new InlineKeyboardMarkup(new InlineKeyboardButton[]{tookButton});
    }
    public InlineKeyboardMarkup buttonsOfVolunteerForReports() {
        logger.info("Был вызван метод создания кнопок Волонтера для работы с отчетами");
        InlineKeyboardButton reportButtonOk = new InlineKeyboardButton("Отчет сдан");
        InlineKeyboardButton reportButtonNotOk = new InlineKeyboardButton("Отчет не сдан");
        InlineKeyboardButton toStart = new InlineKeyboardButton("В начало");


        reportButtonOk.callbackData("Отчет сдан");
        reportButtonNotOk.callbackData("Отчет не сдан");

        toStart.callbackData("В начало");
        return new InlineKeyboardMarkup(new InlineKeyboardButton[]{reportButtonOk, reportButtonNotOk},
                new InlineKeyboardButton[]{toStart});
    }
    public InlineKeyboardMarkup buttonsOfVolunteerForEnd30DaysPeriod() {
        logger.info("Был вызван метод создания кнопок Волонтера для принятия решения спустя 30 дней");
        InlineKeyboardButton probationPeriodCompletedButton = new InlineKeyboardButton("Испытательный срок пройден");
        InlineKeyboardButton probationPeriodExtended14Button = new InlineKeyboardButton("Продлить на 14 дней");
        InlineKeyboardButton probationPeriodExtended30Button = new InlineKeyboardButton("Продлить на 30 дней");
        InlineKeyboardButton probationPeriodNotCompletedButton = new InlineKeyboardButton("Испытательный срок не пройден");


        probationPeriodCompletedButton.callbackData("Испытательный срок пройден");
        probationPeriodExtended14Button.callbackData("Продлить на 14 дней");
        probationPeriodExtended30Button.callbackData("Продлить на 30 дней");
        probationPeriodNotCompletedButton.callbackData("Испытательный срок не пройден");

        return new InlineKeyboardMarkup(new InlineKeyboardButton[]{probationPeriodCompletedButton}
                ,
                new InlineKeyboardButton[]{probationPeriodExtended14Button}
                ,
                new InlineKeyboardButton[]{probationPeriodExtended30Button}
                ,
                new InlineKeyboardButton[]{probationPeriodNotCompletedButton}
        );
    }
    public InlineKeyboardMarkup buttonsOfOwner() {
        logger.info("Был вызван метод создания кнопок Владельца животного");
        InlineKeyboardButton toStart = new InlineKeyboardButton("В начало");
        InlineKeyboardButton dailyRepotrButton = new InlineKeyboardButton("Форма ежедневного отчета");
        InlineKeyboardButton callVolunteerButton = new InlineKeyboardButton("Позвать волонтера");
        callVolunteerButton.callbackData("Позвать волонтера");
        dailyRepotrButton.callbackData("Форма ежедневного отчета");
        toStart.callbackData("В начало");
        return new InlineKeyboardMarkup(new InlineKeyboardButton[]{dailyRepotrButton},
                new InlineKeyboardButton[]{callVolunteerButton},
                new InlineKeyboardButton[]{toStart});
    }
}

