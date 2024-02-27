package pro.sky.telegrambot.model;

import com.pengrad.telegrambot.model.request.InlineKeyboardButton;

public enum Button {
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
    TOOK_ANIMAL("Взять животное"),
    REPORT("Просмотр отчетов"),
    REPORTS("Отчеты"),
    REPORT_OK("Отчет сдан"),
    REPORTS_NOT_OK("Отчет не сдан"),
    PROBATION_PERIOD_COMPLETED("Испытательный срок пройден"),
    PROBATION_PERIOD_EXTENDED_14("Продлить на 14 дней"),
    PROBATION_PERIOD_EXTENDED_30("Продлить на 30 дней"),
    PROBATION_PERIOD_NOT_COMPLETED("Испытательный срок не пройден"),
    DAILY_REPORT("Форма ежедневного отчета");

    private final String code;

    Button(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}