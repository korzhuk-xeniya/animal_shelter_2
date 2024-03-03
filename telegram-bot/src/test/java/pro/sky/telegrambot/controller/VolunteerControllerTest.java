package pro.sky.telegrambot.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import pro.sky.telegrambot.model.Volunteer;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


    @SpringBootTest
    @AutoConfigureMockMvc
    @Transactional
    @DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
    public class VolunteerControllerTest {
        @Autowired
        private MockMvc mockMvc;
        @Autowired
        private ObjectMapper objectMapper;


        @Test
        void deleteVolunteerById() throws Exception {
            Volunteer volunteer = new Volunteer(10L,"Slava1", "Safronov", 2L);
            long id = volunteer.getId();
            mockMvc.perform(delete("/volunteer/delete/{id}",id))
                    .andExpect(status().isOk());
        }
        @Test
        void save() throws Exception {
            Volunteer volunteer = new Volunteer( "Slava2", "Safronov2", 3L);
            mockMvc.perform(post("/volunteer/save")
                            .content(objectMapper.writeValueAsString(volunteer))
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andDo(print())
                    .andExpect(content().contentType("application/json"))
                    .andExpect(jsonPath("$").value("Slava2"))
                    .andExpect(jsonPath("$").value("Safronov2"))
                    .andExpect(jsonPath("$").value(3L));

        }

        @Test
        void allVolunteer() throws Exception {
            mockMvc.perform(get("/volunteer/list"))

                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$").isArray())
                    .andExpect(jsonPath("$.*", Matchers.hasSize(7)))
                    .andExpect(jsonPath("$[5].name").value("Slava1"))
                    .andExpect(jsonPath("$[5].lastName").value("Safronov1"))
                    .andExpect(jsonPath("$[5].chatId").value(2L))
                    .andExpect(jsonPath("$[6].name").value("Slava2"))
                    .andExpect(jsonPath("$[6].lastName").value("Safronov2"))
                    .andExpect(jsonPath("$[6].chatId").value(3L));
        }




}