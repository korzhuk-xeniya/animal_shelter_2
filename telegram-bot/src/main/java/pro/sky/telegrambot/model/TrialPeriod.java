package pro.sky.telegrambot.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
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
    private Long id;

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
    private Long ownerId;

    @Column(name = "animal_id")
    private Long animalId;

    public TrialPeriod(LocalDate startDate, LocalDate endDate, LocalDate lastReportDate,
                       List<ReportAboutAnimal> reports, Result result, Long ownerId, Long animalId) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.lastReportDate = lastReportDate;
        this.reports = reports;
        this.result = result;
        this.ownerId = ownerId;
        this.animalId = animalId;
    }

    public Long getId() {
        return id;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public LocalDate getLastReportDate() {
        return lastReportDate;
    }

    public List<ReportAboutAnimal> getReports() {
        return reports;
    }

    public Result getResult() {
        return result;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public Long getAnimalId() {
        return animalId;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public void setLastReportDate(LocalDate lastReportDate) {
        this.lastReportDate = lastReportDate;
    }

    public void setReports(List<ReportAboutAnimal> reports) {
        this.reports = reports;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    public void setAnimalId(Long animalId) {
        this.animalId = animalId;
    }
}