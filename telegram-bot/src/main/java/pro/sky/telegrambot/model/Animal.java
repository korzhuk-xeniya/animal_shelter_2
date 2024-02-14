package pro.sky.telegrambot.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.Parent;

import javax.persistence.*;
import java.util.UUID;

/**
 * Класс для животных в приюте.
 * На данный момент это кошки и собаки.
 */

@Entity(name = "Animals")
@JsonIgnoreProperties(value = "user")
public class Animal {

    //ID животного
    @Id
    @GeneratedValue
    @Column
    private UUID id;

    @OneToOne
    private User user;

    //Возраст животного
    private final Integer ageMonth;
    @Column
    //Имя
    private final String nameOfAnimal;
    @Column
    //Ссылка на фото
    private final String photoLink;
    @Column
    //Пол животного
    private final String gender;
    @Column
    //Тип животного
    private final String petType;

    public Animal(UUID id, Integer ageMonth, String name, String photoLink, String gender, String petType) {
        this.id = id;
        this.ageMonth = ageMonth;
        this.nameOfAnimal = name;
        this.photoLink = photoLink;
        this.gender = gender;
        this.petType = petType;
    }

    public User getUser() {
        return user;
    }

    public Integer getAgeMonth() {
        return ageMonth;
    }

    public String getNameOfAnimal() {
        return nameOfAnimal;
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
                ", name='" + nameOfAnimal + '\'' +
                ", photoLink='" + photoLink + '\'' +
                ", gender='" + gender + '\'' +
                ", petType='" + petType + '\'' +
                '}';
    }
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "parent_chatId")
    @JsonManagedReference
    private Parent parent;


    public void setAgeMonth(Integer ageMonth) {

    }

    public void setName(String name) {
    }

    public void setGender(String gender) {
    }

    public void setPetType(String petType) {
    }


    public void setParent(Object o) {
    }


}