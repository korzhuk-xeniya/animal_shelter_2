package pro.sky.telegrambot.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "trial_periods")
public class TrialPeriod {
    public enum Result {
        IN_PROGRESS,
        SUCCESSFUL,
        NOT_SUCCESSFUL,
        EXTENDED
    }


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Column(name = "last_report_date")
    private LocalDate lastReportDate;

    @OneToMany(mappedBy = "trialPeriodId", cascade = CascadeType.ALL)
    private List<ReportAboutAnimal> reports;

    /**
     * Стадия испытательного срока
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "result")
    private Result result;

    @Column(name = "owner_id")
    private UUID ownerId;

    @Column(name = "animal_id")
    private UUID animalId;

    public TrialPeriod(LocalDate startDate, LocalDate endDate, LocalDate lastReportDate,
                       List<ReportAboutAnimal> reports, Result result, UUID ownerId, UUID animalId) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.lastReportDate = lastReportDate;
        this.reports = reports;
        this.result = result;
        this.ownerId = ownerId;
        this.animalId = animalId;
    }
}

