package pro.sky.telegrambot.buttons;

import com.pengrad.telegrambot.model.request.InlineKeyboardButton;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * class for creating buttons of start menu
 */
@Component
public class  ButtonsOfMenu {
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
            InlineKeyboardButton safetyButton = new InlineKeyboardButton("Безопасность на территории приюта");
            InlineKeyboardButton leavePhoneNumberButton = new InlineKeyboardButton("Оставить телефон для связи");
            InlineKeyboardButton toStart = new InlineKeyboardButton("В начало");
            aboutShelterButton.callbackData("О приюте");
            callVolunteerButton.callbackData("Позвать волонтера");
            timetableButton.callbackData("График работы");
            addressButton.callbackData("Адрес приюта");
            locationMapButton.callbackData("Схема проезда");
            securityButton.callbackData("Телефон охраны");
            safetyButton.callbackData("Безопасность на территории приюта");
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
            InlineKeyboardButton datingRulesButton = new InlineKeyboardButton("Правила знакомства");
            InlineKeyboardButton listOfDocumentsButton = new InlineKeyboardButton("Список документов");
            InlineKeyboardButton transportationButton = new InlineKeyboardButton("Рекомендации по транспортировке");
            InlineKeyboardButton arrangementPuppyButton = new InlineKeyboardButton("Обустройство щенка");
            InlineKeyboardButton arrangementAdultButton = new InlineKeyboardButton("Обустройство для взрослой собаки");
            InlineKeyboardButton arrangementDisabledButton = new InlineKeyboardButton("Животное с ограниченными возможностями");
            InlineKeyboardButton whyRefuseButton = new InlineKeyboardButton("Причины отказа");
            InlineKeyboardButton callVolunteerButton = new InlineKeyboardButton("Позвать волонтера");
            InlineKeyboardButton toStart = new InlineKeyboardButton("В начало");

            arrangementAdultButton.callbackData("Обустройство для взрослой собаки");
            arrangementPuppyButton.callbackData("Обустройство щенка");
            datingRulesButton.callbackData("Правила знакомства");
            listOfDocumentsButton.callbackData("Список документов");
            transportationButton.callbackData("Рекомендации по транспортировке");
            arrangementDisabledButton.callbackData("Животное ограниченными возможностями");
            whyRefuseButton.callbackData("Причины отказа");
            callVolunteerButton.callbackData("Позвать волонтера");
            toStart.callbackData("В начало");
            return new InlineKeyboardMarkup(new InlineKeyboardButton[]{datingRulesButton},
                    new InlineKeyboardButton[]{listOfDocumentsButton},
//                new InlineKeyboardButton[]{arrangementDisabledButton},
                    new InlineKeyboardButton[]{whyRefuseButton},
                    new InlineKeyboardButton[]{arrangementPuppyButton},
                    new InlineKeyboardButton[]{arrangementAdultButton},
                    new InlineKeyboardButton[]{transportationButton},
                    new InlineKeyboardButton[]{callVolunteerButton},
                    new InlineKeyboardButton[]{toStart});
        }
}
