package pro.sky.telegrambot.repository.owner;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pro.sky.telegrambot.model.owner.DogOwner;

import java.util.Optional;

@Repository
public interface DogOwnerRepository extends JpaRepository<DogOwner, Long> {
    Optional<DogOwner> findByTelegramId(Long telegramId);
}
