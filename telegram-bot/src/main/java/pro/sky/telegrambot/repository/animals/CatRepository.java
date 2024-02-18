package pro.sky.telegrambot.repository.animals;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pro.sky.telegrambot.model.animals.Cat;

import java.util.List;

@Repository
public interface CatRepository extends JpaRepository<Cat, Long> {
    /**
     * Поиск котов по telegramId пользователя
     */
    List<Cat> findAllByOwnerId(Long id);

}
