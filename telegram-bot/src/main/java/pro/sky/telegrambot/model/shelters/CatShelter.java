package pro.sky.telegrambot.model.shelters;

import lombok.Data;
import lombok.NoArgsConstructor;
import pro.sky.telegrambot.model.animals.Cat;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "cat_shelter")
public class CatShelter {
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
     * Расписание работы приюта
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
    private List<Cat> list;

    /**
     * Конструктор для POST в БД (Без id, без List)
     */

    public CatShelter(Long id, String name, String shelterPhone, String location, String timetable, String aboutMe) {
        this.id = id;
        this.name = name;
        this.shelterPhone = shelterPhone;
        this.location = location;
        this.timetable = timetable;
        this.aboutMe = aboutMe;
    }

    /**
     * Конструктор для PUT в БД (c id, без List)
     */
    public CatShelter(String name, String shelterPhone, String location, String timetable, String aboutMe) {
        this.name = name;
        this.location = location;
        this.shelterPhone = shelterPhone;
        this.timetable = timetable;
        this.aboutMe = aboutMe;
    }
}
