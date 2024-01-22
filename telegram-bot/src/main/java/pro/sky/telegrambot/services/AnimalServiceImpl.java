package pro.sky.telegrambot.services;

import org.springframework.stereotype.Service;
import pro.sky.telegrambot.repository.AnimalRepository;

@Service

public class AnimalServiceImpl implements AnimalService {
    private final AnimalRepository animalRepository;
    private final ParentRepository parentRepository;
    public AnimalServiceImpl(AnimalRepository animalRepository, ParentRepository parentRepository) {
        this.animalRepository = animalRepository;
        this.parentRepository = parentRepository;
    }}