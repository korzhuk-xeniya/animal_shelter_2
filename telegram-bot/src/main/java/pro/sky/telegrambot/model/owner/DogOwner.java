package pro.sky.telegrambot.model.owner;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import pro.sky.telegrambot.model.TrialPeriod;
import pro.sky.telegrambot.model.User;
import pro.sky.telegrambot.model.animals.Dog;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@DiscriminatorValue("dog")
public class DogOwner extends Owner {

    @OneToMany(mappedBy = "ownerId", fetch = FetchType.EAGER)
    private List<Dog> dogList;

    public DogOwner(Long telegramId, String firstName,  String phone, List<Dog> dogList, List<TrialPeriod> trialPeriodList) {
        super(telegramId, firstName,  phone, trialPeriodList);
        this.dogList = dogList;
    }

    public DogOwner(Long id, Long telegramId, String firstName,  String phone, List<TrialPeriod> trialPeriodList, List<Dog> dogList) {
        super(id, telegramId, firstName, phone, trialPeriodList);
        this.dogList = dogList;
    }

    public DogOwner(User user) {
        super(user);
    }
}