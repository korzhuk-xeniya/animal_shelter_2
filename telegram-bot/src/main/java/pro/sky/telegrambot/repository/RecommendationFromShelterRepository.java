package pro.sky.telegrambot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pro.sky.telegrambot.model.Animal;
import pro.sky.telegrambot.model.RecommendationFromShelter;

import java.util.UUID;

public interface RecommendationFromShelterRepository extends JpaRepository<RecommendationFromShelter, UUID> {
}
