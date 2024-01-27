package pro.sky.telegrambot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pro.sky.telegrambot.model.AnimalAdoptiveParents;

import java.util.UUID;

public interface AnimalAdoptiveParentsRepository extends JpaRepository<AnimalAdoptiveParents, UUID> {
}
