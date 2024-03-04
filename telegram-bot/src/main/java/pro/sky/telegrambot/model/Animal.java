package pro.sky.telegrambot.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(force = true)
@Entity
@Component
@Table(name = "animals")
public class Animal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_animal")
    private long id;
    @Column(name = "age_in_month")
    //Возраст животного
    private final long ageMonth;
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
    @OneToOne
//    @JoinColumn(name = "user_id")
    private User user;

    public Animal(long ageMonth, String nameOfAnimal, String photoLink, String gender, String petType) {
        this.ageMonth = ageMonth;
        this.nameOfAnimal = nameOfAnimal;
        this.photoLink = photoLink;
        this.gender = gender;
        this.petType = petType;
    }

    public Animal(long id, long ageMonth, String nameOfAnimal, String photoLink, String gender, String petType) {
        this.id = id;
        this.ageMonth = ageMonth;
        this.nameOfAnimal = nameOfAnimal;
        this.photoLink = photoLink;
        this.gender = gender;
        this.petType = petType;
    }


    public void setId(long id) {
        this.id = id;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Animal{" +
                "id=" + id +
                ", ageMonth=" + ageMonth +
                ", nameOfAnimal='" + nameOfAnimal + '\'' +
                ", photoLink='" + photoLink + '\'' +
                ", gender='" + gender + '\'' +
                ", petType='" + petType + '\'' +
                ", user=" + user +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Animal animal = (Animal) o;
        return id == animal.id && ageMonth == animal.ageMonth && user == animal.user &&
                Objects.equals(nameOfAnimal, animal.nameOfAnimal) &&
                Objects.equals(photoLink, animal.photoLink) &&
                Objects.equals(gender, animal.gender) &&
                Objects.equals(petType, animal.petType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, ageMonth, nameOfAnimal,
                photoLink, gender, petType, user);
    }
}