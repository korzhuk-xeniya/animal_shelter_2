package pro.sky.telegrambot.model.owner;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pro.sky.telegrambot.model.TrialPeriod;
import pro.sky.telegrambot.model.User;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "owners")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "animal_type",
        discriminatorType = DiscriminatorType.STRING)
public class Owner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Long telegramId;

    @Column
    private String firstName;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ownerId")
    private List<TrialPeriod> trialPeriodList;

    public Owner(Long telegramId, String firstName,  List<TrialPeriod> trialPeriodList) {
        this.telegramId = telegramId;
        this.firstName = firstName;
        this.trialPeriodList = trialPeriodList;
    }

    public Owner(User user) {
        this.setTelegramId(user.getChatId());
        this.setFirstName(user.getFirstName());
    }
}