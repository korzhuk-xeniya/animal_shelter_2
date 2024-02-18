package pro.sky.telegrambot.service.animalsService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pro.sky.telegrambot.exceptions.NotFoundException;
import pro.sky.telegrambot.model.animals.Cat;
import pro.sky.telegrambot.repository.animals.CatRepository;
import pro.sky.telegrambot.service.UserServiceImpl;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CatServiceImpl implements CatService {
    private final CatRepository catRepository;


    @Override
    public Cat create(Cat cat) {
        return catRepository.save(cat);
    }

    @Override
    public Cat getById(Long id) {
        Optional<Cat> optionalCat = catRepository.findById(id);
        if (optionalCat.isEmpty()) {
            throw new NotFoundException("Кот не найден!");
        }
        return optionalCat.get();
    }


    @Override
    public List<Cat> getAllByUserId(Long id) {
        List<Cat> catList = catRepository.findAllByOwnerId(id);
        if (catList.isEmpty()) {
            throw new NotFoundException("У этого человека нет котов!");
        }
        return catList;
    }

    @Override
    public Cat update(Cat cat) {
        Optional<Cat> catId = catRepository.findById(cat.getId());
        if (catId.isEmpty()) {
            throw new NotFoundException("Нет кота!");
        }
        Cat currentCat = catId.get();
        UserServiceImpl.EntityUtils.copyNonNullFields(cat, currentCat);
        return catRepository.save(currentCat);
    }

    @Override
    public List<Cat> getAll() {
        return catRepository.findAll();
    }

    @Override
    public void remove(Long id) {
        catRepository.deleteById(getById(id).getId());
    }
}
