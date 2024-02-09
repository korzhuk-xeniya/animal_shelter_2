package pro.sky.telegrambot.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pro.sky.telegrambot.model.User;


import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    static void save(Optional<User> user) {
    }

    static User findByChatId(long animalsUserChatId) {
        return animalsUserChatId;
    }


    Optional<User> getUserByChatId(long chatId);

    User findUserByChatId(int chatId);

//    @Query("SELECT CASE WHEN COUNT(u) > 0 THEN true ELSE false END FROM User u WHERE u.chatId = :chatId")
//    boolean existsByChatId(int chatId);





//    Optional<User> findByDateTimeToTookBefore(LocalDateTime dateTimeToTook);

//    @Transactional
//    @Modifying
//    @Query("UPDATE User u set u.number = ?2 where u.chatId = ?1")
//    int updateNumber(int chatId, String num);
}