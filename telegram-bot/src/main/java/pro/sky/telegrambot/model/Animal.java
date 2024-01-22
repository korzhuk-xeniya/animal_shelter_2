package pro.sky.telegrambot.model;


import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Класс для  животных в приюте.
 * На данный момент это кошки и собаки.
 */

public class Animal {
    @Id
    @GeneratedValue
    private long id;
    private Integer ageMonth;
    private String name;
    private Gender gender;
    private PetType petType;

}