package pro.sky.telegrambot.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pro.sky.telegrambot.model.User;


import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    static void save(Optional<Object> o) {
    }

    Optional<User> findById(UUID telegramId);

    Optional<User> getUserByChatId(long chatId);

    @Query("SELECT CASE WHEN COUNT(u) > 0 THEN true ELSE false END FROM User u WHERE u.chatId = :chatId")
    User findUserByChatId(long chatId);


    Optional<User> findByDateTimeToTookBefore(LocalDateTime dateTimeToTook);

    Optional<User> findByChatId(long chatId);

    boolean existsByChatId(long chatId);


    @Transactional
    @Modifying
    @Query("UPDATE User u set u.number = ?2 where u.chatId = ?1")
    int updateNumber(long chatId, String num);
}