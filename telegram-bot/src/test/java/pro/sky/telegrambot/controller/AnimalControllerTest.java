package pro.sky.telegrambot.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import pro.sky.telegrambot.model.Animal;
import pro.sky.telegrambot.service.AnimalService;

import javax.persistence.Column;

import static org.hamcrest.Matchers.any;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AnimalController.class)
class AnimalControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private AnimalService animalService;
    @InjectMocks
    private AnimalController animalController;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void shouldAddAnimal() throws Exception {
        long chatId = 1L;
        int age = 26;
        String name = "catty";
        String userName = "aleksander";
        Gender gender = Gender.MALE;
        Animal animal;
        animal=new Animal(1,2,"кот","к","");
        animal.setChatId(chatId);
        animal.setPetType(petType);
        animal.setName(name);
        animal.setAgeMonth(age);
        animal.setGender(gender);
        when(AnimalService.add((Animal) any(Animal.class))).thenReturn(animal);
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/animals")
                        .content(objectMapper.writeValueAsBytes(animal))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())

                .andExpect((ResultMatcher) jsonPath("$.age").value(age))
                .andExpect((ResultMatcher) jsonPath("$.name").value(name))
                .andExpect((ResultMatcher) jsonPath("$.userName").value(userName))
                .andExpect(jsonPath("$.gender").value(gender.toString()));
    }
    @Test
    void get() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }

    @Test
    void allAnimals() {
    }
}



