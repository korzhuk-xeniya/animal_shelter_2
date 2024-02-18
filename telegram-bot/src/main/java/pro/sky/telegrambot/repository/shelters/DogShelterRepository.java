package pro.sky.telegrambot.repository.shelters;

import org.springframework.data.jpa.repository.JpaRepository;
import pro.sky.telegrambot.model.shelters.DogShelter;

import java.util.Optional;

public interface DogShelterRepository extends JpaRepository<DogShelter,Long> {
    Optional<DogShelter> findByName(String name);
}
