package pro.sky.telegrambot.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
public class User  {

    @Id
    @Column
    private Long chatId;

    @OneToMany
    private List<ReportAboutAnimal> reportAboutAnimals;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column
    private String phone;

    @OneToMany
    @Column
    private Animal animal;

    public User(Long chatId, String firstName, String lastName, String phone) {
        this.chatId = chatId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
    }

    public User() {
    }

    public Long getChatId() {
        return chatId;
    }

    public void setChatId(Long chatId) {
        this.chatId = chatId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<ReportAboutAnimal> getReportAboutAnimals() {
        return reportAboutAnimals;
    }

    public void setReportAboutAnimals(List<ReportAboutAnimal> reportAboutAnimals) {
        this.reportAboutAnimals = reportAboutAnimals;
    }

    public Animal getAnimal() {
        return animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }


}
