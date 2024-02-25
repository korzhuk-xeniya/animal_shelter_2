package pro.sky.telegrambot.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "report_tg")
public class ReportAboutAnimal {


        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id", nullable = false, unique = true)
        private long id;

        @Column(name = "date_added")
        private LocalDateTime dateAdded;

        @Column(name = "general_well_being")
        private String generalWellBeing;

        @Column(name = "photo_name")
        private String photoNameId;

        @Column(name = "check_report")
        private boolean checkReport;

        @Getter
        @Column(name = "user_id")
        private long userId;


    public ReportAboutAnimal() {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }


    public void setPhoto(String photo) {
    }

    public void setWellBeing(String wellBeing) {
    }
    

    public void setAnimal(Animal animal) {
    }

    public void setDateAdded(LocalDateTime dateAdded) {
    }

    public void setUser(User user) {
    }
}
