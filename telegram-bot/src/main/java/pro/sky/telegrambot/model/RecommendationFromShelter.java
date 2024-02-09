package pro.sky.telegrambot.model;

import javax.persistence.*;

@Entity
public class RecommendationFromShelter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;


    @Column(name = "recommendation_from_shelter")
    private String recommendation;

}
