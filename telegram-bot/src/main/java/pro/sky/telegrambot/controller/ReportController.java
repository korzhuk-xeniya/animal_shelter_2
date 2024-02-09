package pro.sky.telegrambot.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import pro.sky.telegrambot.listener.TelegramBotUpdatesListener;
import pro.sky.telegrambot.model.ReportAboutAnimal;
import pro.sky.telegrambot.service.ReportAboutAnimalServiceImpl;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("reports")
@Tag(
        name = "Отчёт", description = "CRUD-методы для работы с отчётами"
)
@ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Всё хорошо, запрос выполнился."),
        @ApiResponse(responseCode = "400", description = "Есть ошибка в параметрах запроса."),
        @ApiResponse(responseCode = "404", description = "URL неверный или такого действия нет в веб-приложении."),
        @ApiResponse(responseCode = "500", description = "Во время выполнения запроса произошла ошибка на сервере.")
})
public class ReportController {

    private final ReportAboutAnimalServiceImpl reportService;
    private final TelegramBotUpdatesListener telegramBotUpdatesListener;

    public ReportController(ReportAboutAnimalServiceImpl reportService, TelegramBotUpdatesListener telegramBotUpdatesListener) {
        this.reportService = reportService;
        this.telegramBotUpdatesListener = telegramBotUpdatesListener;
    }

    @PostMapping
    @Operation(
            summary = "Создать отчёт"
    )
    public ReportAboutAnimal create(@RequestParam @Parameter(description = "Фотографии животного") String photoLink,
                                    @RequestParam @Parameter(description = "Рацион животного") String diet,
                                    @RequestParam @Parameter(description = "Общее самочувствие и привыкание к новому месту") String wellBeingAndAddiction,
                                    @RequestParam @Parameter(description = "Изменение в поведении") String changesInBehaviour,
                                    @RequestParam @Parameter(description = "Id испытательного срока") UUID trialPeriodId) {
        return reportService.create(new ReportAboutAnimal(photoLink, diet, wellBeingAndAddiction, changesInBehaviour, LocalDate.now(), trialPeriodId));
    }


    @GetMapping()
    @Operation(
            summary = "Получение всех отчётов"
    )
    public List<ReportAboutAnimal> getAll() {
        return reportService.getAll();
    }

    @GetMapping("trial-id")
    @Operation(
            summary = "Получение всех отчётов по id испытательного срока"
    )
    public List<ReportAboutAnimal> getAllByTrialPeriodId(@RequestParam @Parameter(description = "id испытательного срока") UUID id) {
        return reportService.gelAllByTrialPeriodId(id);
    }

    @GetMapping("date-and-trial")
    @Operation(
            summary = "Получение отчёта по дате и id испытательного срока"
    )
    @Parameters(value = {
            @Parameter(
                    name = "date",
                    description = "Дата создания отчёта",
                    example = "2023-12-31"
            ),
            @Parameter(
                    name = "id",
                    description = "Id испытательного срока",
                    example = "00000000-0000-0000-0000-000000000000"
            )
    }
    )
    public ReportAboutAnimal getByDateAndTrialId(@RequestParam @Parameter(description = "Дата получения отчёта") LocalDate date,
                                                 @RequestParam @Parameter(description = "id испытательного срока") UUID id) {
        return reportService.getByDateAndTrialId(date, id);
    }

    @GetMapping("id")
    @Operation(summary = "Получение отчёта по id")
    public ReportAboutAnimal getById(@RequestParam @Parameter(description = "Id испытательного срока") UUID reportId) {
        return reportService.getById(reportId);
    }

    @PutMapping
    @Operation(
            summary = "Изменить отчёт"
    )
    public ReportAboutAnimal update(@RequestParam @Parameter(description = "Id отчёта") UUID id,
                                    @RequestParam(required = false) @Parameter(description = "Фотографии животного") String photoLink,
                                    @RequestParam(required = false) @Parameter(description = "Рацион животного") String diet,
                                    @RequestParam(required = false) @Parameter(description = "Общее самочувствие и привыкание к новому месту") String wellBeingAndAddiction,
                                    @RequestParam(required = false) @Parameter(description = "Изменение в поведении") String changesInBehaviour,
                                    @RequestParam(required = false) @Parameter(description = "Дата получения") LocalDate receiveDate,
                                    @RequestParam(required = false) @Parameter(description = "Id испытательного срока") UUID trialPeriodId) {
        return reportService.update(new ReportAboutAnimal(id, photoLink, diet, wellBeingAndAddiction, changesInBehaviour, receiveDate, trialPeriodId));
    }

    @DeleteMapping("id")
    @Operation(
            summary = "Удаление отчёта по id"
    )
    @Parameter(
            name = "id",
            description = "Id отчёта",
            example = "1"
    )
    public String deleteById(@RequestParam UUID id) {
        reportService.deleteById(id);
        return "Отчёт успешно удалён";
    }

    @GetMapping("report-photo")
    @Operation(summary = "Отправить фото из отчёта волонтёру")
    public String getReportPhoto(@RequestParam @Parameter(description = "Id отчёта") UUID reportId,
                                 @RequestParam @Parameter(description = "Id волонтёра") UUID volunteerId) {
        // telegramBotUpdatesListener.sendReportPhotoToVolunteer(reportId, volunteerId);
        return "Фотография успешно отправлена";
    }
}