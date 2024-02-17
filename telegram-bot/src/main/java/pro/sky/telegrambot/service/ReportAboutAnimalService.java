package pro.sky.telegrambot.service;

import pro.sky.telegrambot.model.ReportAboutAnimal;

import java.util.Optional;

public interface ReportAboutAnimalService {

    ReportAboutAnimal sendReportAboutAnimal(ReportAboutAnimal reportAboutAnimal);

    ReportAboutAnimal updateReportAboutAnimal(ReportAboutAnimal reportAboutAnimal);

    Optional<ReportAboutAnimal> readReportAboutAnimal(ReportAboutAnimal reportAboutAnimal);
}
