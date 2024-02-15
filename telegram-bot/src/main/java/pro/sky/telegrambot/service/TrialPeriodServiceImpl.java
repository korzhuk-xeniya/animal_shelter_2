package pro.sky.telegrambot.service;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pro.sky.telegrambot.exceptions.NotFoundException;
import pro.sky.telegrambot.model.TrialPeriod;
import pro.sky.telegrambot.repository.TrialPeriodRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TrialPeriodServiceImpl implements TrialPeriodService {

    private final TrialPeriodRepository trialPeriodRepository;
    private final CatService catService;
    private final DogService dogService;

    @Override
    public TrialPeriod create(TrialPeriod trialPeriod) {
        return trialPeriodRepository.save(trialPeriod);
    }

    @Override
    public TrialPeriod create(TrialPeriod trialPeriod, TrialPeriod.AnimalType animalType) {
        if (animalType.equals(TrialPeriod.AnimalType.CAT)) {
            catService.getById(trialPeriod.getAnimalId()).setOwnerId(trialPeriod.getOwnerId());
        } else if (animalType.equals(TrialPeriod.AnimalType.DOG)) {
            dogService.getById(trialPeriod.getAnimalId()).setOwnerId(trialPeriod.getOwnerId());
        }
        return trialPeriodRepository.save(trialPeriod);
    }

    @Override
    public TrialPeriod getById(Long id) {
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
    public List<TrialPeriod> getAllByOwnerId(Long ownerId) {
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
    public void deleteById(Long id) {
        trialPeriodRepository.deleteById(getById(id).getId());
    }
}
