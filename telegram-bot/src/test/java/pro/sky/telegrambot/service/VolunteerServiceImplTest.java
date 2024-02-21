package pro.sky.telegrambot.service;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import pro.sky.telegrambot.exceptions.VolunteerNotFoundException;
import pro.sky.telegrambot.model.Volunteer;
import pro.sky.telegrambot.repository.VolunteerRepository;

import java.util.*;

import static java.util.OptionalInt.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

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
        when(repository.findAllById(Collections.singleton(volunteer1.getId()))).thenReturn(empty());

        assertThrows(VolunteerNotFoundException.class, () -> service.readVolunteer(volunteer1.getId()));
    }

    @Test
    void updateVolunteerById_ShouldUpdateAndReturnUpdateVolunteer() {
        when(repository.findAllById(Collections.singleton(volunteer1.getId()))).thenReturn(Optional.of(volunteer1));
        when(repository.save(volunteer1)).thenReturn(volunteer1);
        Volunteer result = service.updateVolunteerById(volunteer1);

        assertEquals(volunteer1, result);
    }

    @Test
    void deleteById() {
        long id = 3;
        when(repository.deleteById()(Collections.singleton(volunteer1.getId()))).thenReturn((List<Volunteer>) volunteer1);
        service.saveVolunteerInBd(volunteer1);
        service.saveVolunteerInBd(volunteer2);
        Collection<Volunteer> volunteerCollection = new ArrayList<>(Arrays.asList(volunteer1, volunteer2));
        Collection<Volunteer> volunteerCollection = new ArrayList<>(Arrays.asList(volunteer1, volunteer2));

        Volunteer result = service.deleteById(volunteer3.getId());

        assertEquals(volunteer1, result);
    }

    @Test
    void findAllVolunteers() {
    }

    @Test
    void getVolunteerByChatId() {
    }

    @Test
    void saveVolunteer() {
    }
}