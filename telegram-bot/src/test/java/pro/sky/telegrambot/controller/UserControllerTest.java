package pro.sky.telegrambot.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import pro.sky.telegrambot.model.User;
import pro.sky.telegrambot.repository.UserRepository;
import pro.sky.telegrambot.service.UserService;
import pro.sky.telegrambot.service.UserServiceImpl;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.http.RequestEntity.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@AutoConfigureMockMvc
@WebMvcTest(UserController.class)

 class UserControllerTest {

    @MockBean
    UserRepository userRepository;
    @SpyBean
    UserService userService;
    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;

    User user = new User( "Slava", true,15, LocalDateTime.now());
        @Test
        void deleteUser() throws Exception {
//            doNothing().when(userService).deleteById(anyLong());
//            when(userService.deleteById(user.getId())).thenReturn(Optional.empty());
            when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));
            long id = user.getId();
            mockMvc.perform((RequestBuilder) delete("/users/delete/{id}", id))
                    .andExpect(status().isOk());
        }
        @Test
        void create_shouldReturnStatus200() throws Exception {
            when(userRepository.save(user)).thenReturn(user);
            mockMvc.perform(post("/users/save")
                            .content(objectMapper.writeValueAsString(user))
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andDo(print())
                    .andExpect(content().contentType("application/json"))
                    .andExpect(jsonPath("$.id").value(1))
                    .andExpect(jsonPath("$.firstName").value("Slava"))
                    .andExpect(jsonPath("$.chatId").value(15));

        }
        @Test
        void getUserByChatId() throws Exception {
            mockMvc.perform(get("/users/chatId/15"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.id").value(1))
                    .andExpect(jsonPath("$.firstName").value("Slava"))
                    .andExpect(jsonPath("$.chatId").value(15));
        }
        @Test
        void updateUser() throws Exception {
            when(userRepository.save(user)).thenReturn(user);

            long id = user.getId();
            mockMvc.perform(put("/users/update/{id}",id)
                            .content(objectMapper.writeValueAsString(user))
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.firstName").value("Slava"))
                    .andExpect(jsonPath("$.chatId").value(15));
        }

    }