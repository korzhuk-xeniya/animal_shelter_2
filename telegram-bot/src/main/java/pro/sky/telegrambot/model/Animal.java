package pro.sky.telegrambot.model;


import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.UUID;

/**
 * Класс для  животных в приюте.
 * На данный момент это кошки и собаки.
 */

public class Animal {
    @Id
    @GeneratedValue
    private long id;
    private long chatId;
    private String name;
    private int ageMonth;
    private Gender gender;
    private PetType petType;


}