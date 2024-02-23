package pro.sky.telegrambot.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user_tg")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "took_a_pet")
    private Boolean tookAPet;

    @Column(name = "date_time_to_took")
    private LocalDateTime dateTimeToTook;

    @Column(name = "chat_id")
    private long chatId;

    @Column(name = "number")
    private String number;


//    @Column(name = "reports")
////    @OneToMany(mappedBy = "user_id", cascade = CascadeType.ALL)
//    private List<Report> reports = new ArrayList<>();

    public User(String firstName, Boolean tookAPet, long chatId, LocalDateTime dateTimeToTook) {
        this.firstName = firstName;
        this.tookAPet = tookAPet;
        this.chatId = chatId;
        this.dateTimeToTook = dateTimeToTook;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Boolean getTookAPet() {
        return tookAPet;
    }

    public void setTookAPet(Boolean tookAPet) {
        this.tookAPet = tookAPet;
    }

    public long getChatId() {
        return chatId;
    }

    public void setChatId(long chatId) {
        this.chatId = chatId;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public LocalDateTime getDateTimeToTook() {
        return dateTimeToTook;
    }

    public void setDateTimeToTook(LocalDateTime dateTimeToTook) {
        this.dateTimeToTook = dateTimeToTook;
    }

//    public List<Report> getReports() {
//        return reports;
//    }

//    public void setReports(List<Report> reports) {
//        this.reports = reports;
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id && chatId == user.chatId && Objects.equals(firstName, user.firstName)
                && Objects.equals(tookAPet, user.tookAPet) && Objects.equals(number, user.number) &&
                Objects.equals(dateTimeToTook, user.dateTimeToTook);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, tookAPet, chatId, number, dateTimeToTook);
    }

//    public List<Report> getReportAboutAnimals() {
//        return reportAboutAnimals;
//    }
//
//    public void setReportAboutAnimals(List<Report> reportAboutAnimals) {
//        this.reportAboutAnimals = reportAboutAnimals;
//    }

//    public Animal getAnimal() {
//        return animal;
//    }
//
//    public void setAnimal(Animal animal) {
//        this.animal = animal;
//    }


}
