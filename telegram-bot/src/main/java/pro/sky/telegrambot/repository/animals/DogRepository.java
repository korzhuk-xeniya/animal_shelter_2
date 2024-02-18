package pro.sky.telegrambot.repository.animals;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pro.sky.telegrambot.model.animals.Dog;

import java.util.List;

@Repository
public interface DogRepository extends JpaRepository<Dog, Long> {
    /**
     * Поиск собак по telegramId пользователя
     */
    List<Dog> findAllByOwnerId(Long id);

}