package pro.sky.telegrambot.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ReportAboutAnimal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private UUID id;
    @Column(name = "photo_link")
    private String photoLink;
    @Column(name = "diet")
    private String diet;
    @Column(name = "well_being_and_addiction")
    private String wellBeingAndAddiction;
    @Column(name = "changes_in_behaviour")
    private String changesInBehaviour;
    @Column(name = "receive_date")
    private LocalDate receiveDate;
    @Column(name = "trial_period_Id")
    private UUID trialPeriodId;

    public ReportAboutAnimal(String photoLink, String diet, String wellBeingAndAddiction,
                  String changesInBehaviour, LocalDate receiveDate, UUID trialPeriodId) {
        this.photoLink = photoLink;
        this.diet = diet;
        this.wellBeingAndAddiction = wellBeingAndAddiction;
        this.changesInBehaviour = changesInBehaviour;
        this.receiveDate = receiveDate;
        this.trialPeriodId = trialPeriodId;
    }
}
