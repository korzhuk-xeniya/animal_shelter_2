package pro.sky.telegrambot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pro.sky.telegrambot.model.Volunteer;

import java.util.Optional;
import java.util.UUID;

public interface VolunteerRepository  extends JpaRepository<Volunteer, UUID> {
        Optional<Object> findAById(long id);

        void deleteById(long id);

        Optional<Object> findById(long id);

        Optional<Volunteer> findByChatId(long chatId);
    }

