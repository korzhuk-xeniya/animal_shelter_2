package pro.sky.telegrambot.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pro.sky.telegrambot.model.ReportAboutAnimal;


import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface ReportAboutAnimalRepository extends JpaRepository<ReportAboutAnimal, Long> {
    Optional<ReportAboutAnimal> findByReceiveDateAndTrialPeriodId(LocalDate date, Long id);

    List<ReportAboutAnimal> findAllByTrialPeriodId(Long trialPeriodId);
}

