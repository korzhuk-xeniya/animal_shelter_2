package pro.sky.telegrambot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pro.sky.telegrambot.model.Report;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface ReportRepository extends JpaRepository<Report, Long> {
    Optional<Report> findByReceiveDateAndTrialPeriodId(LocalDate date, Long id);

    List<Report> findAllByTrialPeriodId(Long trialPeriodId);
}
