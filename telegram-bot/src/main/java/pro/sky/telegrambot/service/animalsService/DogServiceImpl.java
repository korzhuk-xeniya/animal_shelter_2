package pro.sky.telegrambot.service.animalsService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pro.sky.telegrambot.exceptions.NotFoundException;
import pro.sky.telegrambot.model.animals.Dog;
import pro.sky.telegrambot.repository.animals.DogRepository;
import pro.sky.telegrambot.service.UserServiceImpl;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DogServiceImpl implements DogService {
    private final DogRepository dogRepository;

    @Override
    public Dog create(Dog dog) {
        return dogRepository.save(dog);
    }

    @Override
    public Dog getById(Long id) {
        Optional<Dog> optionalDog = dogRepository.findById(id);
        if (optionalDog.isEmpty()) {
            throw new NotFoundException("Собака не найдена!");
        }
        return optionalDog.get();

    }

    @Override
    public List<Dog> getAllByUserId(Long id) {
        List<Dog> dogList = dogRepository.findAllByOwnerId(id);
        if (dogList.isEmpty()) {
            throw new NotFoundException("У этого человека нет собак!");
        }
        return dogList;
    }

    @Override
    public Dog update(Dog dog) {
        Optional<Dog> dogId = dogRepository.findById(dog.getId());
        if (dogId.isEmpty()) {
            throw new NotFoundException("Пса нет");
        }
        Dog currentDog = dogId.get();
        UserServiceImpl.EntityUtils.copyNonNullFields(dog, currentDog);
        return dogRepository.save(currentDog);
    }

    @Override
    public List<Dog> getAll() {
        return dogRepository.findAll();
    }

    @Override
    public void remove(Long id) {
        dogRepository.deleteById(getById(id).getId());
    }
}
