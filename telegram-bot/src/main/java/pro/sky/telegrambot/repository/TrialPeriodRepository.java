package pro.sky.telegrambot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pro.sky.telegrambot.model.TrialPeriod;

import java.util.List;

public interface TrialPeriodRepository extends JpaRepository<TrialPeriod, Long> {
    List<TrialPeriod> findAllByOwnerId(Long ownerId);
}
