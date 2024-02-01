package pro.sky.telegrambot.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.UUID;

public class AnimalAdoptiveParents {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private UUID id;


    @Column(name = "animalAdoptiveParent")
    private String animalAdoptiveParent;
}
