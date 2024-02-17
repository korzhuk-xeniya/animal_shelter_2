package pro.sky.telegrambot.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;
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
//    //ID репорта
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "id")
//    private long id;
//
//    @ManyToOne
//    @JoinColumn(name = "user_Id")
//    private User user;
//
//
//    //Ссылка на фото
//    private String photoLink;
//
//    //Рацион
//    private String diet;
//
//    //Общее самочувствие и привыкание к новому месту
//    private String wellBeingAndAddiction;
//
//    //Изменения в поведении: отказ от старых привычек, приобретение новых
//    private String changesInBehaviour;

    public ReportAboutAnimal(long id) {
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

    //    public String getPhotoLink() {
//        return photoLink;
//    }
//
//    public void setPhotoLink(String photoLink) {
//        this.photoLink = photoLink;
//    }
//
//    public String getDiet() {
//        return diet;
//    }
//
//    public void setDiet(String diet) {
//        this.diet = diet;
//    }
//
//    public String getWellBeingAndAddiction() {
//        return wellBeingAndAddiction;
//    }
//
//    public void setWellBeingAndAddiction(String wellBeingAndAddiction) {
//        this.wellBeingAndAddiction = wellBeingAndAddiction;
//    }
//
//    public String getChangesInBehaviour() {
//        return changesInBehaviour;
//    }
//
//    public void setChangesInBehaviour(String changesInBehaviour) {
//        this.changesInBehaviour = changesInBehaviour;
//    }

    //Загрузка фотографии животного
    public void loadPhotoOfAnimal() {
        //здесь надо как-то прописать загрузку и изменение фотографии животного
    }

    //Отправка отчета (!НАДО ДОБАВИТЬ ФОТОГРАФИЮ И ТОЖЕ ОТПРАВЛЯТЬ!)
    public void sendReport(String diet, String wellBeingAndAddiction, String changesInBehaviour) {
        //Действия для отправки отчета, возможно отсылка к методу из Listener
    }
}
