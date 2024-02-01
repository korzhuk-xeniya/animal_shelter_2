package pro.sky.telegrambot.service;

import pro.sky.telegrambot.model.ReportAboutAnimal;
import pro.sky.telegrambot.repository.ReportAboutAnimalRepository;

import java.util.Optional;

public class ReportAboutAnimalServiceImpl implements ReportAboutAnimalService {
    private final ReportAboutAnimalRepository reportAboutAnimalRepository;

    public ReportAboutAnimalServiceImpl(ReportAboutAnimalRepository reportAboutAnimalRepository) {
        this.reportAboutAnimalRepository = reportAboutAnimalRepository;
    }

    public ReportAboutAnimal sendReportAboutAnimal(ReportAboutAnimal reportAboutAnimal) {
        return reportAboutAnimalRepository.save(reportAboutAnimal);
    }

    public ReportAboutAnimal updateReportAboutAnimal(ReportAboutAnimal reportAboutAnimal) {
        return reportAboutAnimalRepository.save(reportAboutAnimal);
    }



    public Optional<ReportAboutAnimal> readReportAboutAnimal(ReportAboutAnimal reportAboutAnimal) {
        return reportAboutAnimalRepository.findById(reportAboutAnimal.getId());
    }
}
