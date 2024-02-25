package pro.sky.telegrambot.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pro.sky.telegrambot.model.ReportAboutAnimal;
import pro.sky.telegrambot.model.Volunteer;
import pro.sky.telegrambot.service.VolunteerService;

import javax.validation.constraints.Positive;
import java.util.List;

@RestController
@RequestMapping("/volunteer")
public class VolunteerController {


    private final VolunteerService volunteerService;

    public VolunteerController(VolunteerService volunteerService) {
        this.volunteerService = volunteerService;
    }

    @Operation(summary = "Внести волонтера",
            responses = {
                    @ApiResponse(responseCode = "200",
                            description = "Волонтер внесен в таблицу",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Volunteer.class)
                            )),
                    @ApiResponse(responseCode = "500",
                            description = "Ошибка сервера",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Volunteer.class)

                            )),
                    @ApiResponse(responseCode = "404",
                            description = "Некорректный формат",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Volunteer.class)

                            ))})

    @PostMapping("/save")
    public Volunteer saveVolunteerInBd(@RequestBody Volunteer volunteer) {
        return volunteerService.saveVolunteerInBd(volunteer);
    }



    @Operation(summary = "Удаления волонтера по id",
            responses = {
                    @ApiResponse(responseCode = "200",
                            description = "Волонтер удален",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Volunteer.class)
                            )),
                    @ApiResponse(responseCode = "500",
                            description = "Ошибка сервера",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Volunteer.class)

                            )),
                    @ApiResponse(responseCode = "404",
                            description = "Нет волонтера с таким id",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Volunteer.class)

                            ))
            })
    @DeleteMapping("/delete/{id}")
    public void deleteVolunteerById(@PathVariable long id) {
        volunteerService.deleteById(id);
    }





    @Operation(summary = "Поиск отчетов по ID.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Отчет по идентификатору получен!",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ReportAboutAnimal.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Неверный запрос!"
            )
    }
    )
    @GetMapping("/{id}")
    public ResponseEntity<ReportAboutAnimal> downloadReport(@Parameter(description = "report id") @PathVariable Integer id) {
        return ResponseEntity.ok(this.volunteerService.findById(id));
    }

    @Operation(summary = "Удаление отчета по идентификатору.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Отчет удален!",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ReportAboutAnimal.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Неверный запрос!"
            )
    }
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remove(@Parameter(description = "report id") @PathVariable Integer id) {
        if (id != null) {
            volunteerService.remove(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Получение коллекции отчетов.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Отчет получен.",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ReportAboutAnimal.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Неверный запрос!"
            )
    }
    )
    @GetMapping("/")
    public ResponseEntity<List<ReportAboutAnimal>> getAll() {
        return ResponseEntity.ok(this.volunteerService.getAll());
    }


}


