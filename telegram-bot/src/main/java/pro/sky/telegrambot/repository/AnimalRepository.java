package pro.sky.telegrambot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import pro.sky.telegrambot.model.Animal;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, UUID> {
//    public Animal save(Animal animal) {
//        return animal;
//    }
//
//    public void deleteById(Long id) {
//    }
//
//    public Optional<Object> findById(Long id) {
//
//
//    }
}
