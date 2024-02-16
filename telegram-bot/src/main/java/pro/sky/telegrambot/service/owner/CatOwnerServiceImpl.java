package pro.sky.telegrambot.service.owner;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pro.sky.telegrambot.exceptions.AlreadyExistsException;
import pro.sky.telegrambot.exceptions.NotFoundException;
import pro.sky.telegrambot.model.TrialPeriod;
import pro.sky.telegrambot.model.owner.CatOwner;
import pro.sky.telegrambot.repository.owner.CatOwnerRepository;
import pro.sky.telegrambot.service.TrialPeriodService;
import pro.sky.telegrambot.service.UserService;
import pro.sky.telegrambot.service.UserServiceImpl;
import pro.sky.telegrambot.service.animalsService.CatService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
@RequiredArgsConstructor
public class CatOwnerServiceImpl implements CatOwnerService{
    private final CatOwnerRepository catOwnerRepository;
    private final UserService userService;
    private final CatService catService;
    private final TrialPeriodService trialPeriodService;

    @Override
    public CatOwner create(CatOwner catOwner, TrialPeriod.AnimalType animalType, Long animalId) {
        if (catService.getById(animalId).getOwnerId() != null) {
            throw new AlreadyExistsException("У этого кота уже есть хозяин!");
        }
        trialPeriodService.create(new TrialPeriod(LocalDate.now(), LocalDate.now().plusDays(30),
                LocalDate.now().minusDays(1), new ArrayList<>(),
                TrialPeriod.Result.IN_PROGRESS, catOwner.getTelegramId(), animalType, animalId), animalType);
        catService.getById(animalId).setOwnerId(catOwner.getTelegramId());
        return catOwnerRepository.save(catOwner);
    }

    @Override
    public CatOwner create(Long id, TrialPeriod.AnimalType animalType, Long animalId) {
        if (catService.getById(animalId).getOwnerId() != null) {
            throw new AlreadyExistsException("У этого кота уже есть хозяин!");
        }
        CatOwner catOwner = new CatOwner(userService.getById(id));
        trialPeriodService.create(new TrialPeriod(LocalDate.now(), LocalDate.now().plusDays(30),
                LocalDate.now().minusDays(1), new ArrayList<>(),
                TrialPeriod.Result.IN_PROGRESS, id, animalType, animalId), animalType);
        catService.getById(animalId).setOwnerId(id);
        return catOwnerRepository.save(catOwner);
    }

    @Override
    public CatOwner getById(Long id) {
        Optional<CatOwner> optionalCatOwner = catOwnerRepository.findByTelegramId(id);
        if (optionalCatOwner.isEmpty()) {
            throw new NotFoundException("Хозяин кота не найден!");
        }
        return optionalCatOwner.get();
    }

    @Override
    public List<CatOwner> getAll() {
        List<CatOwner> all = catOwnerRepository.findAll();
        if (all.isEmpty()) {
            throw new NotFoundException("Владельцев котов нет!");
        }
        return all;
    }

    @Override
    public CatOwner update(CatOwner catOwner) {
        CatOwner currentCatOwner = getById(catOwner.getTelegramId());
        UserServiceImpl.EntityUtils.copyNonNullFields(catOwner, currentCatOwner);
        return catOwnerRepository.save(currentCatOwner);
    }

    @Override
    public void delete(CatOwner catOwner) {
        catService.getAllByUserId(catOwner.getTelegramId()).forEach(cat -> {
            cat.setOwnerId(null);
            catService.update(cat);
        });
        catOwnerRepository.delete(getById(catOwner.getTelegramId()));
    }

    @Override
    public void deleteById(Long id) {
        delete(getById(id));
    }
}
