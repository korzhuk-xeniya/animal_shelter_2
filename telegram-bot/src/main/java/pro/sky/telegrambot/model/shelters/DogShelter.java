package pro.sky.telegrambot.model.shelters;

import lombok.Data;
import lombok.NoArgsConstructor;
import pro.sky.telegrambot.model.animals.Dog;

import javax.persistence.*;
import java.util.List;


@Data
@NoArgsConstructor
@Entity
@Table(name = "dog_shelter")
public class DogShelter {
    /**
     * Id для приюта
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * Название приюта
     */
    @Column
    private String name;

    /**
     * Телефонный номер приюта
     */
    @Column
    private String shelterPhone;
    /**
     * Адрес и схема проезда
     */
    @Column
    private String location;
    /**
     * Расписание
     */
    @Column
    private String timetable;
    /**
     * О приюте
     */
    @Column(name = "about_me")
    private String aboutMe;
    /**
     * Список животных в приюте
     */
    @OneToMany(mappedBy = "shelterId", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Dog> list;


    /**
     * Конструктор для POST в БД (Без id, без List)
     */
    public DogShelter(Long idShelter, String name,String shelterPhone, String location, String timetable, String aboutMe) {
        this.id = idShelter;
        this.name = name;
        this.location = location;
        this.timetable = timetable;
        this.aboutMe = aboutMe;
    }

    /**
     * Конструктор для PUT в БД (c id, без List)
     */
    public DogShelter(String name,String shelterPhone, String location, String timetable, String aboutMe) {
        this.name = name;
        this.shelterPhone=shelterPhone;
        this.location = location;
        this.timetable = timetable;
        this.aboutMe = aboutMe;

    }
}
