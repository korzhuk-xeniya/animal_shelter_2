package pro.sky.telegrambot.model.animals;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "cat")
public class Dog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private Integer age;

    @Column
    private String name;

    @Column
    private Boolean gender;


    @Column(name = "owner_id")
    private Long ownerId;

    @Column(name = "shelter_id")
    private Long shelterId;

    public Dog (String name, Integer age, Boolean gender, Long shelterId) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.shelterId = shelterId;
    }

    @Override
    public String toString() {
        return "Имя: " + name +
                ", Возраст: " + age +
                ", Состояние здоровья: " + (gender ? "это мальчик" : "это девочка") ;
    }
}
