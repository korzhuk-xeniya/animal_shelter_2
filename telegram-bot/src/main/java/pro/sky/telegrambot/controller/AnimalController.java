package pro.sky.telegrambot.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pro.sky.telegrambot.model.Animal;
import pro.sky.telegrambot.service.AnimalService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/animals")
public class AnimalController {
    private final AnimalService animalService;

    public AnimalController(AnimalService animalService) {
        this.animalService = animalService;
    }
    //добавление животного
    @PostMapping
    public Animal add( @RequestBody Animal animal) {
        return animalService.add(animal);
    }

    //поиск животного по id
    @GetMapping("/{id}")
    public ResponseEntity<Animal> get(@PathVariable UUID id){
        Animal animal = animalService.get(id);
        if (animal == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(animal);
    }
   //обновление данных по животному
    @PutMapping("/update/{id}")
    public ResponseEntity<Animal> update( @PathVariable Long id,
                                       @RequestBody Animal animal) {
        Animal savedAnimal = animalService.update(id, animal);
        if (savedAnimal == null) {
            return ResponseEntity.badRequest().build();
        } else {
            return ResponseEntity.ok(savedAnimal);
        }
    }

    //удаление животного
        @DeleteMapping("/{id}")
        public ResponseEntity<Animal> delete(@PathVariable UUID id) {
            animalService.delete(id);
            return ResponseEntity.ok().build();
        }

        //вывод списка всех животных
        @GetMapping
        public List<Animal> allAnimals() {
            return animalService.allAnimals();
}}
