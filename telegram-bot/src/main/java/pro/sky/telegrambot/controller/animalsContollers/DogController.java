package pro.sky.telegrambot.controller.animalsContollers;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pro.sky.telegrambot.model.animals.Dog;
import pro.sky.telegrambot.service.animalsService.DogServiceImpl;

import java.util.List;

@RestController
@RequestMapping("cats")
@Tag(name = "Песики", description = "CRUD-методы для работы с собаками")
@RequiredArgsConstructor
@ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Всё хорошо, запрос выполнился."),
        @ApiResponse(responseCode = "400", description = "Есть ошибка в параметрах запроса."),
        @ApiResponse(responseCode = "404", description = "URL неверный или такого действия нет в веб-приложении."),
        @ApiResponse(responseCode = "500", description = "Во время выполнения запроса произошла ошибка на сервере.")
})
public class DogController {

    private final DogServiceImpl dogService;

    @GetMapping("/id")
    @Operation(summary = "Получение собаки по ID")
    public Dog getById(@RequestParam @Parameter(description = "ID собачки") Long id) {
        return dogService.getById(id);
    }

//    @PostMapping
//    @Operation(summary = "Добавить собаку в приют")
//    public Dog create(
//            @RequestParam @Parameter(description = "Имя песика") String name,
//            @RequestParam @Parameter(description = "Возраст") int age,
//            @RequestParam @Parameter(description = "Пол") Boolean gender,
//            @RequestParam @Parameter(description = "ID приюта") Long shelterId) {
//        return dogService.create(new Dog(name, age, gender, shelterId));
//    }

//    @GetMapping()
//    @Operation(summary = "Получение всех собак")
//    public List<Dog> getAll() {
//        return dogService.getAll();
//    }

    @GetMapping("/ownerID")
    @Operation(summary = "Получение списка песиков по ID хозяина")
    public List<Dog> getOwnerById(@RequestParam @Parameter(description = "ID хозяина") Long id) {
        return dogService.getAllByUserId(id);
    }


//    @PutMapping
//    @Operation(summary = "Изменить информацию о собаке")
//    public Dog update(
//            @RequestParam @Parameter(description = "ID песика") Long id,
//            @RequestParam(required = false) @Parameter(description = "Имя песика") String name,
//            @RequestParam(required = false) @Parameter(description = "Возраст песика") Integer age,
//            @RequestParam(required = false) @Parameter(description = "Пол") Boolean gender,
//            @RequestParam(required = false) @Parameter(description = "ID собачьего приюта") Long shelterId,
//            @RequestParam(required = false) @Parameter(description = "ID хозяина") Long ownerId) {
//        return dogService.update(new Dog(id, age, name,  gender, shelterId, ownerId));
//    }

    @DeleteMapping("/id")
    @Operation(summary = "Удаление собаки")
    public String deleteById(@RequestParam Long id) {
        dogService.remove(id);
        return "Теперь он дикий койот! Улица - его дом...";
    }

}
