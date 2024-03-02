package pro.sky.telegrambot.controller;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pro.sky.telegrambot.model.User;
import pro.sky.telegrambot.service.UserService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("users")
@Tag(name = "Пользователь", description = "CRUD-методы для работы с пользователями")
@ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Всё хорошо, запрос выполнился."),
        @ApiResponse(responseCode = "400", description = "Есть ошибка в параметрах запроса."),
        @ApiResponse(responseCode = "404", description = "URL неверный или такого действия нет в веб-приложении."),
        @ApiResponse(responseCode = "500", description = "Во время выполнения запроса произошла ошибка на сервере.")
})
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/save")
    @Operation(summary = "Создать пользователя")
    public User create(@RequestBody User user) {
        return userService.createUser(user);
    }

    @GetMapping()
    @Operation(summary = "Получение списка всех пользователей")
    public List<User> getAll() {
        return userService.getAll();
    }

    @GetMapping("id")
    @Operation(summary = "Получение пользователя по id")
    public User getById(@RequestParam @Parameter(description = "Id пользователя") long userId) {
        return userService.getById(userId);
    }


    @Operation(summary = "Обновление иформации о пользователе")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Пользователь обновлен",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            array = @ArraySchema(
                                    schema = @Schema(implementation = User.class)))),
            @ApiResponse(responseCode = "400", description = "Имеют некорруктный формат"),
            @ApiResponse(responseCode = "500", description = "Ошибка сервера")
    })
    @PutMapping("/update/{id}")
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        User user1 = userService.updateUser(user);
        return ResponseEntity.ok(user1);
    }
    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Удаление пользователя по id")
    public String deleteById(@RequestParam @Parameter(description = "Id пользователя") long userId) {
        userService.deleteById(userId);
        return "Пользователь успешно удалён";
    }
    @Operation(summary = "Получить пользователя по chatId")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Пользователь найден",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = User.class))),
            @ApiResponse(responseCode = "400", description = "Посетитель не найден"),
            @ApiResponse(responseCode = "500", description = "Ошибка сервера")
    })
    @GetMapping("/chatId/{chatId}")
    public ResponseEntity<User> getUserByChatId(@PathVariable  long chatId) {
        return ResponseEntity.of(userService.getUserByChatId(chatId));
    }
}

