package pro.sky.telegrambot.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pro.sky.telegrambot.exceptions.NotFoundException;
import pro.sky.telegrambot.model.Animal;
import pro.sky.telegrambot.model.TrialPeriod;
import pro.sky.telegrambot.repository.TrialPeriodRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TrialPeriodServiceImpl implements TrialPeriodService {

    private final TrialPeriodRepository trialPeriodRepository;
    private final AnimalService animalService;

    @Override
    public TrialPeriod create(TrialPeriod trialPeriod) {
        return trialPeriodRepository.save(trialPeriod);
    }
/* to do : получать от животного тип
    @Override
    public TrialPeriod create(TrialPeriod trialPeriod, {
        if (.Animal.PetType.CAT)) {
            animalService.getById(trialPeriod.getAnimalId()).setOwnerId(trialPeriod.getOwnerId());
        } else if (.Animal.PetType.DOG)) {
            animalService.getById(trialPeriod.getAnimalId()).setOwnerId(trialPeriod.getOwnerId());
        }
        return trialPeriodRepository.save(trialPeriod);
    }*/

    @Override
    public TrialPeriod getById(UUID id) {
        Optional<TrialPeriod> optionalTrialPeriod = trialPeriodRepository.findById(id);
        if (optionalTrialPeriod.isEmpty()) {
            throw new NotFoundException("Испытательный срок не найден!");
        }
        return optionalTrialPeriod.get();
    }

    @Override
    public List<TrialPeriod> getAll() {
        List<TrialPeriod> all = trialPeriodRepository.findAll();
        if (all.isEmpty()) {
            throw new NotFoundException("Испытательные сроки не найдены!");
        }
        return all;
    }

    @Override
    public List<TrialPeriod> getAllByOwnerId(UUID ownerId) {
        List<TrialPeriod> allByOwnerId = trialPeriodRepository.findAllByOwnerId(ownerId);
        if (allByOwnerId.isEmpty()) {
            throw new NotFoundException("Испытательные сроки не найдены!");
        }
        return allByOwnerId;
    }

    @Override
    public TrialPeriod update(TrialPeriod trialPeriod) {
        TrialPeriod currentTrialPeriod = getById(trialPeriod.getId());
        UserServiceImpl.EntityUtils.copyNonNullFields(trialPeriod, currentTrialPeriod);
        return trialPeriodRepository.save(currentTrialPeriod);
    }

    @Override
    public void delete(TrialPeriod trialPeriod) {
        trialPeriodRepository.delete(getById(trialPeriod.getId()));
    }

    @Override
    public void deleteById(UUID id) {
        trialPeriodRepository.deleteById(getById(id).getId());
    }
}
