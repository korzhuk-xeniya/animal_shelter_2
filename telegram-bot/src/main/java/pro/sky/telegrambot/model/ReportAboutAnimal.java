package pro.sky.telegrambot.model;

import javax.persistence.*;
import java.util.UUID;

public class ReportAboutAnimal {

    //ID репорта
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "user_Id")
    private User user;


    //Ссылка на фото
    private String photoLink;

    //Рацион
    private String diet;

    //Общее самочувствие и привыкание к новому месту
    private String wellBeingAndAddiction;

    //Изменения в поведении: отказ от старых привычек, приобретение новых
    private String changesInBehaviour;

    public ReportAboutAnimal(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getPhotoLink() {
        return photoLink;
    }

    public void setPhotoLink(String photoLink) {
        this.photoLink = photoLink;
    }

    public String getDiet() {
        return diet;
    }

    public void setDiet(String diet) {
        this.diet = diet;
    }

    public String getWellBeingAndAddiction() {
        return wellBeingAndAddiction;
    }

    public void setWellBeingAndAddiction(String wellBeingAndAddiction) {
        this.wellBeingAndAddiction = wellBeingAndAddiction;
    }

    public String getChangesInBehaviour() {
        return changesInBehaviour;
    }

    public void setChangesInBehaviour(String changesInBehaviour) {
        this.changesInBehaviour = changesInBehaviour;
    }

    //Загрузка фотографии животного
    public void loadPhotoOfAnimal() {
        //здесь надо как-то прописать загрузку и изменение фотографии животного
    }

    //Отправка отчета (!НАДО ДОБАВИТЬ ФОТОГРАФИЮ И ТОЖЕ ОТПРАВЛЯТЬ!)
    public void sendReport(String diet, String wellBeingAndAddiction, String changesInBehaviour) {
        //Действия для отправки отчета, возможно отсылка к методу из Listener
    }
}
