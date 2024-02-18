package pro.sky.telegrambot.model.owner;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import pro.sky.telegrambot.model.TrialPeriod;
import pro.sky.telegrambot.model.User;
import pro.sky.telegrambot.model.animals.Cat;

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
@DiscriminatorValue("cat")
public class CatOwner extends Owner {

    @OneToMany(mappedBy = "ownerId", fetch = FetchType.EAGER)
    private List<Cat> catList;

    public CatOwner(Long telegramId, String firstName, List<Cat> catList, List<TrialPeriod> trialPeriodList) {
        super(telegramId, firstName, trialPeriodList);
        this.catList = catList;
    }

    public CatOwner(Long id, Long telegramId, String firstName, List<TrialPeriod> trialPeriodList, List<Cat> catList) {
        super(id, telegramId, firstName, trialPeriodList);
        this.catList = catList;
    }

    public CatOwner(User user) {
        super(user);
    }
}
