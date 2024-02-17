package pro.sky.telegrambot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pro.sky.telegrambot.model.Animal;
import pro.sky.telegrambot.model.RecommendationFromShelter;

import java.util.UUID;

@Repository
public interface RecommendationFromShelterRepository extends JpaRepository<RecommendationFromShelter, UUID> {
}
