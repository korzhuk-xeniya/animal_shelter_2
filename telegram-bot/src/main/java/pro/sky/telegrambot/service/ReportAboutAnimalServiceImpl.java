package pro.sky.telegrambot.service;

import org.springframework.stereotype.Service;
import pro.sky.telegrambot.model.ReportAboutAnimal;
import pro.sky.telegrambot.repository.ReportAboutAnimalRepository;

import java.util.Optional;
@Service
public class ReportAboutAnimalServiceImpl implements ReportAboutAnimalService {
    private final ReportAboutAnimalRepository reportAboutAnimalRepository;

    public ReportAboutAnimalServiceImpl(ReportAboutAnimalRepository reportAboutAnimalRepository) {
        this.reportAboutAnimalRepository = reportAboutAnimalRepository;
    }
    @Override
    public ReportAboutAnimal sendReportAboutAnimal(ReportAboutAnimal reportAboutAnimal) {
        return reportAboutAnimalRepository.save(reportAboutAnimal);
    }
    @Override
    public ReportAboutAnimal updateReportAboutAnimal(ReportAboutAnimal reportAboutAnimal) {
        return reportAboutAnimalRepository.save(reportAboutAnimal);
    }


    @Override
    public Optional<ReportAboutAnimal> readReportAboutAnimal(ReportAboutAnimal reportAboutAnimal) {
        return reportAboutAnimalRepository.findById(reportAboutAnimal.getId());
    }
}
