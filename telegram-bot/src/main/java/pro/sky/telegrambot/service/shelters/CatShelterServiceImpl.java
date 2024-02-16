package pro.sky.telegrambot.service.shelters;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pro.sky.telegrambot.exceptions.NotFoundException;
import pro.sky.telegrambot.model.animals.Cat;
import pro.sky.telegrambot.model.shelters.CatShelter;
import pro.sky.telegrambot.repository.shelters.CatShelterRepository;
import pro.sky.telegrambot.service.UserServiceImpl;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CatShelterServiceImpl implements CatShelterService {


    private final CatShelterRepository catShelterRepository;

    @Override
    public CatShelter addShelter(CatShelter shelter) {
        return catShelterRepository.save(shelter);
    }

    @Override
    public CatShelter updateShelter(CatShelter catShelter) {
        CatShelter currentShelter = getSheltersId(catShelter.getId());
        UserServiceImpl.EntityUtils.copyNonNullFields(catShelter, currentShelter);
        return catShelterRepository.save(currentShelter);
    }

    @Override
    public CatShelter getSheltersId(long id) {
        Optional<CatShelter> shelterId = catShelterRepository.findById(id);
        if (shelterId.isEmpty()) {
            throw new NotFoundException("Приют не найден. Котики остались без дома");
        }
        return shelterId.get();
    }

    @Override
    public CatShelter getShelterByName(String name) {
        Optional<CatShelter> shelterId = catShelterRepository.findByName(name);
        if (shelterId.isEmpty()) {
            throw new NotFoundException("Приют не найден. Котики остались без дома");
        }
        return shelterId.get();
    }

    @Override
    public List<CatShelter> getShelter() {
        return catShelterRepository.findAll();
    }

    @Override
    public List<Cat> getAnimal(long index) {
        return getSheltersId(index).getList();
    }

    @Override
    public String deleteShelter(long index) {
        String result;
        Optional<CatShelter> catShelter = catShelterRepository.findById(index);
        if (catShelter.isPresent()) {
            catShelterRepository.deleteById(index);
            result = "Запись удалена";
        } else {
            throw new NotFoundException("Котики остались без приюта / Не нашли приют");
        }
        return result;
    }
}
