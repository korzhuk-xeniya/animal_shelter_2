package pro.sky.telegrambot.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pro.sky.telegrambot.exceptions.NotFoundException;
import pro.sky.telegrambot.model.Volunteer;
import pro.sky.telegrambot.repository.VolunteerRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class VolunteerServiceImpl implements VolunteerService {

    private final VolunteerRepository volunteerRepository;

    @Override
    public Volunteer create(Volunteer volunteer) {
        return volunteerRepository.save(volunteer);
    }

    @Override
    public Volunteer getById(UUID id) {
        Optional<Volunteer> optionalVolunteer = volunteerRepository.findById(id);
        if (optionalVolunteer.isEmpty()) {
            throw new NotFoundException("По указанному id волонтёр не найден!");
        }
        return optionalVolunteer.get();
    }

    @Override
    public List<Volunteer> getAll() {
        List<Volunteer> all = volunteerRepository.findAll();
        if (all.isEmpty()) {
            throw new NotFoundException("Волонтёры не найдены!");
        }
        return all;
    }

    @Override
    public Volunteer update(Volunteer volunteer) {
        Volunteer currentVolunteer = getById(volunteer.getChatId());
        UserServiceImpl.EntityUtils.copyNonNullFields(volunteer, currentVolunteer);
        return volunteerRepository.save(currentVolunteer);
    }

    @Override
    public void delete(Volunteer volunteer) {
        volunteerRepository.delete(getById(volunteer.getChatId()));
    }

    @Override
    public void deleteById(UUID id) {
        volunteerRepository.deleteById(getById(id).getChatId());
    }
}
