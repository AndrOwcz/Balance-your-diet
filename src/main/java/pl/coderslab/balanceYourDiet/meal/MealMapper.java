package pl.coderslab.balanceYourDiet.meal;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public final class MealMapper {

    public MealEntity mapMealDtoToEntity(MealDto mealDto) {
        MealEntity mealEntity = new MealEntity();
        mealEntity.setName(mealDto.getName());
        mealEntity.setDescription(mealDto.getDescription());
        mealEntity.setMealCalories(mealDto.getMealCalories());
        mealEntity.setMealCarbs(mealDto.getMealCarbs());
        mealEntity.setMealFats(mealDto.getMealFats());
        mealEntity.setMealProtein(mealDto.getMealProtein());
        return mealEntity;
    }

    public MealDto mapMealEntityToDto(MealEntity mealEntity) {
        MealDto mealDto = new MealDto();
        mealDto.setId(mealEntity.getId());
        mealDto.setName(mealEntity.getName());
        mealDto.setDescription(mealEntity.getDescription());
        mealDto.setMealCalories(mealEntity.getMealCalories());
        mealDto.setMealCarbs(mealEntity.getMealCarbs());
        mealDto.setMealFats(mealEntity.getMealFats());
        mealDto.setMealProtein(mealEntity.getMealProtein());
        return mealDto;
    }

    public List<MealEntity> mapMealListDtoToEntity(List<MealDto> mealDtos) {
        return mealDtos.stream().map(this::mapMealDtoToEntity).collect(Collectors.toList());
    }

    public List<MealDto> mapMealListEntityToDto(List<MealEntity> mealEntities) {
        return mealEntities.stream().map(this::mapMealEntityToDto).collect(Collectors.toList());
    }
}
