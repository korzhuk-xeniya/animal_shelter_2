package pro.sky.telegrambot.model;

import lombok.*;

import javax.persistence.*;
import javax.persistence.Column;
import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "reports")
public class ReportAboutAnimal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
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
    private Long trialPeriodId;

    public ReportAboutAnimal(String photoLink, String diet, String wellBeingAndAddiction,
                             String changesInBehaviour, LocalDate receiveDate, Long trialPeriodId) {
        this.photoLink = photoLink;
        this.diet = diet;
        this.wellBeingAndAddiction = wellBeingAndAddiction;
        this.changesInBehaviour = changesInBehaviour;
        this.receiveDate = receiveDate;
        this.trialPeriodId = trialPeriodId;
    }

    public ReportAboutAnimal(Long id, String photoLink, String diet, String wellBeingAndAddiction, String changesInBehaviour, LocalDate receiveDate, Long trialPeriodId) {
    }

    public Long getId() {
        return id;
    }
}
