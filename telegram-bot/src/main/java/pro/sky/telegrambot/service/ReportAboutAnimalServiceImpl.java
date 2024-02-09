package pro.sky.telegrambot.service;

import org.springframework.stereotype.Service;
import pro.sky.telegrambot.exceptions.AlreadyExistsException;
import pro.sky.telegrambot.exceptions.NotFoundException;
import pro.sky.telegrambot.model.ReportAboutAnimal;
import pro.sky.telegrambot.model.TrialPeriod;
import pro.sky.telegrambot.repository.ReportAboutAnimalRepository;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class ReportAboutAnimalServiceImpl implements ReportAboutAnimalService {
    private final ReportAboutAnimalRepository reportAboutAnimalRepository;
    private final TrialPeriodService trialPeriodService;

    @Override
    public ReportAboutAnimal create(ReportAboutAnimal report) {
        return reportAboutAnimalRepository.save(report);
    }

    @Override
    public ReportAboutAnimal getById(UUID id) {
        Optional<ReportAboutAnimal> optionalReport = reportAboutAnimalRepository.findById(id);
        if (optionalReport.isEmpty()) {
            throw new NotFoundException("Отчёт не найден!");
        }
        return optionalReport.get();
    }

    @Override
    public ReportAboutAnimal getByDateAndTrialId(LocalDate date, UUID id) {
        Optional<ReportAboutAnimal> optionalReport = reportAboutAnimalRepository.findByReceiveDateAndTrialPeriodId(date, id);
        if (optionalReport.isEmpty()) {
            throw new NotFoundException("Отчёт не найден!");
        }
        return optionalReport.get();
    }

    @Override
    public List<ReportAboutAnimal> getAll() {
        List<ReportAboutAnimal> all = reportAboutAnimalRepository.findAll();
        if (all.isEmpty()) {
            throw new NotFoundException("Отчёты не найдены!");
        }
        return all;
    }

    @Override
    public List<ReportAboutAnimal> gelAllByTrialPeriodId(UUID id) {
        List<ReportAboutAnimal> allByTrialPeriodId = reportAboutAnimalRepository.findAllByTrialPeriodId(id);
        if (allByTrialPeriodId.isEmpty()) {
            throw new NotFoundException("Отчёты не найдены!");
        }
        return allByTrialPeriodId;
    }

    @Override
    public ReportAboutAnimal update(ReportAboutAnimal report) {
        ReportAboutAnimal currentReport = getById(report.getId());
        UserServiceImpl.EntityUtils.copyNonNullFields(report, currentReport);
        return reportAboutAnimalRepository.save(currentReport);
    }

    @Override
    public void delete(ReportAboutAnimal report) {
        reportAboutAnimalRepository.delete(getById(report.getId()));
    }

    @Override
    public void deleteById(UUID id) {
        reportAboutAnimalRepository.deleteById(getById(id).getId());
    }

    @Override
    public ReportAboutAnimal createFromTelegram(String photoLink, String caption, UUID id) {
        TrialPeriod trialPeriod = trialPeriodService.getAllByOwnerId(id).stream()
                .filter(trialPeriod1 -> trialPeriod1.getResult().equals(TrialPeriod.Result.IN_PROGRESS))
                .findFirst().get();
        if (trialPeriod.getLastReportDate().isEqual(LocalDate.now())) {
            throw new AlreadyExistsException("Вы уже отправляли отчёт сегодня.");
        }
        List<String> captionParts = splitCaption(caption);
        ReportAboutAnimal report = create(new ReportAboutAnimal(photoLink, captionParts.get(0), captionParts.get(1),
                captionParts.get(2), LocalDate.now(), trialPeriod.getId()));
        trialPeriod.setLastReportDate(LocalDate.now());
        trialPeriodService.update(trialPeriod);
        return report;
    }

    /**
     * Метод разбивающий описание фотографии на части для создания отчёта
     *
     * @param caption Описание под фотографией
     * @return Список с частями для создания отчёта
     */
    private List<String> splitCaption(String caption) {
        Pattern pattern = Pattern.compile("(Рацион:)\\s(\\W+);\\n(Самочувствие:)\\s(\\W+);\\n(Поведение:)\\s(\\W+);");
        if (caption == null || caption.isBlank()) {
            throw new IllegalArgumentException("Описание под фотографией не должно быть пустым. Отправьте отчёт заново");
        }
        Matcher matcher = pattern.matcher(caption);
        if (matcher.find()) {
            return new ArrayList<>(List.of(matcher.group(2), matcher.group(4), matcher.group(6)));
        } else {
            throw new IllegalArgumentException("Проверьте правильность введённых данных и отправьте отчёт ещё раз.");
        }
    }
}

