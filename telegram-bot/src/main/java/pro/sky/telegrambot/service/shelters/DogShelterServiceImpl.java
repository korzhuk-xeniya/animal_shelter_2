package pro.sky.telegrambot.service.shelters;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pro.sky.telegrambot.exceptions.NotFoundException;
import pro.sky.telegrambot.model.animals.Dog;
import pro.sky.telegrambot.model.shelters.DogShelter;
import pro.sky.telegrambot.repository.shelters.DogShelterRepository;
import pro.sky.telegrambot.service.UserServiceImpl;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DogShelterServiceImpl implements DogShelterService {
    private final DogShelterRepository dogShelterRepository;


    @Override
    public DogShelter addShelter(DogShelter shelter) {
        return dogShelterRepository.save(shelter);
    }

    @Override
    public DogShelter updateShelter(DogShelter shelter) {
        DogShelter currentShelter = getSheltersId(shelter.getId());
        UserServiceImpl.EntityUtils.copyNonNullFields(shelter, currentShelter);
        return dogShelterRepository.save(currentShelter);
    }

    @Override
    public DogShelter getSheltersId(long id) {
        Optional<DogShelter> shelterId = dogShelterRepository.findById(id);
        if (shelterId.isEmpty()) {
            throw new NotFoundException("Приют не найден. Песики остались без приюта ;-(");
        }
        return shelterId.get();
    }

    @Override
    public DogShelter getShelterByName(String name) {
        Optional<DogShelter> shelterId = dogShelterRepository.findByName(name);
        if (shelterId.isEmpty()) {
            throw new NotFoundException("Приют не найден. Песики остались без приюта ;-(");
        }
        return shelterId.get();
    }

    @Override
    public List<DogShelter> getShelter() {
        return dogShelterRepository.findAll();
    }

    @Override
    public List<Dog> getAnimal(long index) {
        return getSheltersId(index).getList();
    }


    @Override
    public String deleteShelter(long index) {
        String result;
        Optional<DogShelter> dogShelter = dogShelterRepository.findById(index);
        if (dogShelter.isPresent()) {
            dogShelterRepository.deleteById(index);
            result = "Запись удалена";
        } else {
            throw new NotFoundException("Песики остались без приюта ;-(");
        }
        return result;
    }
}
