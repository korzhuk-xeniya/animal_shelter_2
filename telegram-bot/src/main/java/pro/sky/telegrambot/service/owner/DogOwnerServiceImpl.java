package pro.sky.telegrambot.service.owner;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pro.sky.telegrambot.exceptions.AlreadyExistsException;
import pro.sky.telegrambot.exceptions.NotFoundException;
import pro.sky.telegrambot.model.TrialPeriod;
import pro.sky.telegrambot.model.owner.DogOwner;
import pro.sky.telegrambot.repository.owner.DogOwnerRepository;
import pro.sky.telegrambot.service.TrialPeriodService;
import pro.sky.telegrambot.service.UserService;
import pro.sky.telegrambot.service.UserServiceImpl;
import pro.sky.telegrambot.service.animalsService.DogService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DogOwnerServiceImpl implements DogOwnerService{
    private final DogOwnerRepository dogOwnerRepo;
    private final UserService userService;
    private final DogService dogService;
    private final TrialPeriodService trialPeriodService;


    @Override
    public DogOwner create(DogOwner dogOwner, TrialPeriod.AnimalType animalType, Long animalId) {
        if (dogService.getById(animalId).getOwnerId() != null) {
            throw new AlreadyExistsException("У этой собаки уже есть хозяин!");
        }
        trialPeriodService.create(new TrialPeriod(LocalDate.now(), LocalDate.now().plusDays(30),
                LocalDate.now().minusDays(1), new ArrayList<>(),
                TrialPeriod.Result.IN_PROGRESS, dogOwner.getTelegramId(), animalType, animalId), animalType);
        dogService.getById(animalId).setOwnerId(dogOwner.getTelegramId());
        return dogOwnerRepo.save(dogOwner);
    }

    @Override
    public DogOwner create(Long id, TrialPeriod.AnimalType animalType, Long animalId) {
        if (dogService.getById(animalId).getOwnerId() != null) {
            throw new AlreadyExistsException("У этой собаки уже есть хозяин!");
        }
        DogOwner dogOwner = new DogOwner(userService.getById(id));
        trialPeriodService.create(new TrialPeriod(LocalDate.now(), LocalDate.now().plusDays(30),
                LocalDate.now().minusDays(1), new ArrayList<>(),
                TrialPeriod.Result.IN_PROGRESS, id, animalType, animalId), animalType);
        dogService.getById(animalId).setOwnerId(id);
        return dogOwnerRepo.save(dogOwner);
    }

    @Override
    public DogOwner getById(Long id) {
        Optional<DogOwner> optionalDogOwner = dogOwnerRepo.findByTelegramId(id);
        if (optionalDogOwner.isEmpty()) {
            throw new NotFoundException("Хозяин собаки не найден!");
        }
        return optionalDogOwner.get();
    }

    @Override
    public List<DogOwner> getAll() {
        List<DogOwner> all = dogOwnerRepo.findAll();
        if (all.isEmpty()) {
            throw new NotFoundException("Владельцев собак нет!");
        }
        return all;
    }

    @Override
    public DogOwner update(DogOwner dogOwner) {
        DogOwner currentDogOwner = getById(dogOwner.getTelegramId());
        UserServiceImpl.EntityUtils.copyNonNullFields(dogOwner, currentDogOwner);
        return dogOwnerRepo.save(currentDogOwner);
    }

    @Override
    public void delete(DogOwner dogOwner) {
        dogService.getAllByUserId(dogOwner.getTelegramId()).forEach(dog -> {
            dog.setOwnerId(null);
            dogService.update(dog);
        });
        dogOwnerRepo.delete(getById(dogOwner.getTelegramId()));
    }

    @Override
    public void deleteById(Long id) {
        delete(getById(id));
    }
}
