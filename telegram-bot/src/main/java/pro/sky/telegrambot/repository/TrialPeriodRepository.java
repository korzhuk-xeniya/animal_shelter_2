package pro.sky.telegrambot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pro.sky.telegrambot.model.TrialPeriod;

import java.util.List;
import java.util.UUID;

public interface TrialPeriodRepository extends JpaRepository<TrialPeriod, UUID> {
    List<TrialPeriod> findAllByOwnerId(UUID ownerId);
}
