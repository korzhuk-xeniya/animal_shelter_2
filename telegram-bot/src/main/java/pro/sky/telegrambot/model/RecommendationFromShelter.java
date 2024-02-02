package pro.sky.telegrambot.model;

import javax.persistence.*;

@Entity
public class RecommendationFromShelter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;


    @Column(name = "recommendationFromShelter")
    private String recommendation;

}
