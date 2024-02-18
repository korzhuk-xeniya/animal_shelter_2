package pro.sky.telegrambot.repository.shelters;

import org.springframework.data.jpa.repository.JpaRepository;
import pro.sky.telegrambot.model.shelters.CatShelter;

import java.util.Optional;

public interface CatShelterRepository extends JpaRepository<CatShelter,Long> {
    Optional<CatShelter> findByName(String name);
}
