package pro.sky.telegrambot.model;


import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import java.util.UUID;

/**
 * Класс для животных в приюте.
 * На данный момент это кошки и собаки.
 */

public class Animal {

    //ID животного
    @Id
    @GeneratedValue
    private UUID id;

    @OneToOne
    private User user;

    //Возраст животного
    private final Integer ageMonth;

    //Имя
    private final String name;

    //Ссылка на фото
    private final String photoLink;

    //Пол животного
    private final String gender;

    //Тип животного
    private final String petType;

    public Animal(Integer ageMonth, String name, String photoLink, String gender, String petType) {
        this.ageMonth = ageMonth;
        this.name = name;
        this.photoLink = photoLink;
        this.gender = gender;
        this.petType = petType;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getAgeMonth() {
        return ageMonth;
    }

    public String getName() {
        return name;
    }

    public String getPhotoLink() {
        return photoLink;
    }

    public String getGender() {
        return gender;
    }

    public String getPetType() {
        return petType;
    }

    @Override
    public String toString() {
        return "Animal{" +
                "user=" + user +
                ", ageMonth=" + ageMonth +
                ", name='" + name + '\'' +
                ", photoLink='" + photoLink + '\'' +
                ", gender='" + gender + '\'' +
                ", petType='" + petType + '\'' +
                '}';
    }


}