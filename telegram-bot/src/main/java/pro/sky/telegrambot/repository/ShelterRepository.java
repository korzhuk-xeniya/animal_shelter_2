package pro.sky.telegrambot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pro.sky.telegrambot.model.Shelter;

import java.util.UUID;

public interface ShelterRepository extends JpaRepository<Shelter, UUID> {
}
