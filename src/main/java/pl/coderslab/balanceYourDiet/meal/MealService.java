package pl.coderslab.balanceYourDiet.meal;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    public List<MealEntity> mapListDtoToEntity(List<MealDto> mealDtos) {
        return mealMapper.mapMealListDtoToEntity(mealDtos);
    }

    public List<MealDto> mapMealListEntityToDto(List<MealEntity> mealEntities) {
        return mealMapper.mapMealListEntityToDto(mealEntities);
    }

    public List<MealEntity> findAllById(Long id) {
        return mealRepository.findAllById(id);
    }

    public List<MealEntity> findAll() {
        return mealRepository.findAllMeals();
    }

    public List<MealEntity> findAllByUserId(Long id) {
        return mealRepository.findAllByUserEntityId(id);
    }

}
