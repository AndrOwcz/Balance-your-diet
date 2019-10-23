package pl.coderslab.balanceYourDiet.meal;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
@Transactional
public class MealService {

    private final MealRepository mealRepository;
    private final MealMapper mealMapper;

    public MealService(MealRepository mealRepository, MealMapper mealMapper) {
        this.mealRepository = mealRepository;
        this.mealMapper = mealMapper;
    }

    public MealEntity mapDtoToEntity(MealDto mealDto) {
        return mealMapper.mapMealDtoToEntity(mealDto);
    }

    public MealDto mapEntityToDto(MealEntity mealEntity) {
        return mealMapper.mapMealEntityToDto(mealEntity);
    }

    public Set<MealEntity> findAllById(Long id) {
        return mealRepository.findAllById(id);
    }

    public Set<MealEntity> findAll() {
        return mealRepository.findAllMeals();
    }

}
