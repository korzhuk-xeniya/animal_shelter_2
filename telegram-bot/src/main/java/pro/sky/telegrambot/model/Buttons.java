package pro.sky.telegrambot.model;

import com.pengrad.telegrambot.model.request.InlineKeyboardButton;

public enum Buttons {
    START("/start"),
    MENU("Меню"),
    CHECK_INFO("Информация о приюте"),
    CALL_VOLUNTEER("Позвать волонтера"),
    GET_REPORT_ABOUT_PET("Прислать отчет о питомце"),
    HOW_GET_PET("Как взять животное из приюта?"),
    TO_START("В начало"),
    ABOUT_SHELTER("О приюте"),
    TIMETABLE("График работы"),
    ADDRESS("Адрес приюта"),
    LOCATION_MAP("Схема проезда"),
    SECURITY_PHONE("Телефон охраны"),
    SAFETY_RULES("Правила посещения приюта"),
    LEAVE_PHONE_NUMBER("Оставить телефон для связи"),
    DATING_RULES("Правила знакомства"),
    LIST_OF_DOCUMENTS("Список документов"),
    TRANSPORTATION("Рекомендации по транспортировке"),
    ARRANGEMENT_PUPPY("Обустройство щенка/котенка"),
    ARRANGEMENT_ADULT("Обустройство для взрослой собаки/кошки"),
    ARRANGEMENT_DISABLED("Животное с ОВЗ"),
    WHY_REFUSE("Причины отказа"),
    CHOOSE_ANIMAL("Выбрать животное"),
    REPORT("Просмотр отчетов"),
    TOOK_ANIMAL("Взять животное");
    private final String code;

    Buttons(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
