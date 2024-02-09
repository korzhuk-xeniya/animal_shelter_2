package pro.sky.telegrambot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pro.sky.telegrambot.model.ReportAboutAnimal;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ReportAboutAnimalRepository extends JpaRepository<ReportAboutAnimal, UUID> {

    Optional<ReportAboutAnimal> findById(long id);
}
