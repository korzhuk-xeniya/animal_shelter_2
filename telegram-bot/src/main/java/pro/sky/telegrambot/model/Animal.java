package pro.sky.telegrambot.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.Parent;

import javax.persistence.*;
import java.util.UUID;

/**
 * Класс для животных в приюте.
 * На данный момент это кошки и собаки.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "animals")
public class Animal {

    //ID животного
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_animal")
    private long id;
    @Column(name = "age_in_month")
    //Возраст животного
    private final Integer ageMonth;
    @Column(name = "name_of_animal")
    //Имя
    private final String nameOfAnimal;
    @Column(name = "photo_link")
    //Ссылка на фото
    private final String photoLink;
    @Column(name = "gender")
    //Пол животного
    private final String gender;
    @Column(name = "pet_type")
    //Тип животного
    private final String petType;

    @Column(name = "user_id")
    private User user;

    public Animal( long id, Integer ageMonth, String name, String photoLink, String gender, String petType) {

        this.ageMonth = ageMonth;
        this.nameOfAnimal = name;
        this.photoLink = photoLink;
        this.gender = gender;
        this.petType = petType;
    }
    public Animal( Integer ageMonth, String name, String photoLink, String gender, String petType) {

        this.ageMonth = 0;
        this.nameOfAnimal = null;
        this.photoLink = null;
        this.gender = null;
        this.petType = null;
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