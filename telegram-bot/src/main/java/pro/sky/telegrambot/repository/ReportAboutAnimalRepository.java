package pro.sky.telegrambot.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pro.sky.telegrambot.model.ReportAboutAnimal;


import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ReportAboutAnimalRepository extends JpaRepository<ReportAboutAnimal, UUID> {
    Optional<ReportAboutAnimal> findByReceiveDateAndTrialPeriodId(LocalDate date, UUID id);

    List<ReportAboutAnimal> findAllByTrialPeriodId(UUID trialPeriodId);
}

