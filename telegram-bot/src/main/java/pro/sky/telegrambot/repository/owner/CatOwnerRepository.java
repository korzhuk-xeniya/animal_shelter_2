package pro.sky.telegrambot.repository.owner;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pro.sky.telegrambot.model.owner.CatOwner;

import java.util.Optional;

@Repository
public interface CatOwnerRepository extends JpaRepository<CatOwner, Long> {
    Optional<CatOwner> findByTelegramId(Long telegramId);
}
