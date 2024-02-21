package pro.sky.telegrambot.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pro.sky.telegrambot.exceptions.VolunteerNotFoundException;
import pro.sky.telegrambot.model.Volunteer;
import pro.sky.telegrambot.repository.VolunteerRepository;

import java.util.*;

import static java.util.Optional.empty;
import static java.util.OptionalInt.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class VolunteerServiceImplTest {
    @Mock
    VolunteerRepository repository;
    @InjectMocks
    VolunteerServiceImpl service;
    private Volunteer volunteer1 = new Volunteer( "Nastya", "Nastina", 123456L);
    private Volunteer volunteer2 = new Volunteer( "Nastya2", "Nastina2", 1234567L);
    private Volunteer volunteer3 = new Volunteer( "Nastya3", "Nastina3", 12345678L);
    @Test
    void saveVolunteerInBd_shouldReturnAddedVolunteer() {
        when(repository.save(volunteer1)).thenReturn(volunteer1);
        Volunteer result = service.saveVolunteerInBd(volunteer1);

        assertEquals(volunteer1, result);
    }

    @Test
    void readVolunteer_shouldThrowExceptionWhenVolunteerWithIdNotFound() {

        when(repository.findById(volunteer1.getId())).thenReturn(empty());

        assertThrows(VolunteerNotFoundException.class, () -> service.readVolunteer(volunteer1.getId()));
    }

    @Test
    void updateVolunteerById_ShouldUpdateAndReturnUpdateVolunteer() {
        when(repository.findById(volunteer1.getId())).thenReturn(Optional.of(volunteer1));
        when(repository.save(volunteer1)).thenReturn(volunteer1);
        Volunteer result = service.updateVolunteerById(volunteer1);


        assertEquals(volunteer1, result);
    }

    @Test
    void deleteById() {
        doNothing().when(repository).deleteById(anyLong());

        service.deleteById(volunteer1.getId());

        verify(repository, only()).deleteById(anyLong());
    }


    @Test
    void getVolunteerByChatId() {
        when(repository.findByChatId(volunteer1.getChatId())).thenReturn(Optional.of(volunteer1));
        Volunteer result =  service.getVolunteerByChatId(volunteer1.getChatId()).get();


        assertEquals(volunteer1, result);
    }


}