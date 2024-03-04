package pro.sky.telegrambot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pro.sky.telegrambot.model.Animal;

import java.util.List;
import java.util.Optional;

public interface AnimalRepository extends JpaRepository <Animal, Long> {
    Optional<Object> findById(long id);

    Animal save(Animal animal);

    void deleteById(long id);

    List<Animal> findAll();
}
