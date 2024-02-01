package pro.sky.telegrambot.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.UUID;

public class AnimalAdoptiveParents {

    //ID усыновителя
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private UUID id;


    //Имя усыновителя
    @Column(name = "animalAdoptiveParent")
    private String animalAdoptiveParent;

    //Усыновленное животное
    private Animal parentedAnimal;

    //Рацион
    private String diet;

    //Общее самочувствие и привыкание к новому месту
    private String wellBeingAndAddiction;

    //Изменения в поведении: отказ от старых привычек, приобретение новых
    private String changesInBehaviour;

    public AnimalAdoptiveParents(UUID id, String animalAdoptiveParent, Animal parentedAnimal) {
        this.id = id;
        this.animalAdoptiveParent = animalAdoptiveParent;
        this.parentedAnimal = parentedAnimal;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getAnimalAdoptiveParent() {
        return animalAdoptiveParent;
    }

    public void setAnimalAdoptiveParent(String animalAdoptiveParent) {
        this.animalAdoptiveParent = animalAdoptiveParent;
    }

    public Animal getParentedAnimal() {
        return parentedAnimal;
    }

    public void setParentedAnimal(Animal parentedAnimal) {
        this.parentedAnimal = parentedAnimal;
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
