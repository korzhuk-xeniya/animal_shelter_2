package pro.sky.telegrambot.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Getter
    @Id
    @Column
    private UUID chatId;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column
    private String phone;

    @Column
    private String shelterType;

    @Column
    private String shelterName;

    public User(UUID chatId, String firstName, String lastName, String phone) {
        this.chatId = chatId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
    }

    public void setChatId(UUID chatId) {
        this.chatId = chatId;
    }

    public UUID getId() {
        return chatId;
    }
}
