package pro.sky.telegrambot.model;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "users")
public class User  {

    @Id
    @Column
    @GeneratedValue
    private UUID id;
    @Column
    private Long chatId;
    @Column
    @OneToMany
//            (mappedBy = "user_tg")

    private List<ReportAboutAnimal> reportAboutAnimals;

    @Column
    private String firstName;

    @Column
    private String lastName;

    public UUID getId() {
        return id;
    }

    @Column
    private String phone;

   @OneToOne
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

    public void setAnimal(Object o) {
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
