package pro.sky.telegrambot.model;


import javax.persistence.*;
import java.util.UUID;

/**
 * Класс для животных в приюте.
 * На данный момент это кошки и собаки.
 */
@Entity
@Table(name = "animals")
public class Animal {

    //ID животного
    @Id
    @GeneratedValue
    @Column
    private UUID id;
//    @Column
////    @OneToOne
//    private User user;
    @Column
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

    public Animal(Integer ageMonth, String name, String photoLink, String gender, String petType) {
        this.ageMonth = ageMonth;
        this.nameOfAnimal = name;
        this.photoLink = photoLink;
        this.gender = gender;
        this.petType = petType;
    }

//    public User getUser() {
//        return user;
//    }
//
//    public void setUser(User user) {
//        this.user = user;
//    }

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
//                "user=" + user +
                ", ageMonth=" + ageMonth +
                ", name='" + nameOfAnimal + '\'' +
                ", photoLink='" + photoLink + '\'' +
                ", gender='" + gender + '\'' +
                ", petType='" + petType + '\'' +
                '}';
    }


}