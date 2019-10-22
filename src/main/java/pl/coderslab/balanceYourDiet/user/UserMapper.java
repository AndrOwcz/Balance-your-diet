package pl.coderslab.balanceYourDiet.user;

import org.springframework.stereotype.Component;
import pl.coderslab.balanceYourDiet.dailyPlan.DailyPlanMapper;
import pl.coderslab.balanceYourDiet.meal.MealMapper;

@Component
public final class UserMapper {

    private DailyPlanMapper dailyPlanMapper;
    private MealMapper mealMapper;

    public UserMapper(DailyPlanMapper dailyPlanMapper, MealMapper mealMapper) {
        this.dailyPlanMapper = dailyPlanMapper;
        this.mealMapper = mealMapper;
    }

    public UserEntity mapUserDtoToEntity(UserDto userDto) {
        UserEntity userEntity = new UserEntity();
        userEntity.setFirstName(userDto.getFirstName());
        userEntity.setLastName(userDto.getLastName());
        userEntity.setEmail(userDto.getEmail());
        userEntity.setRequiredCalories(userDto.getRequiredCalories());
        userEntity.setRequiredCarbs(userDto.getRequiredCarbs());
        userEntity.setRequiredFats(userDto.getRequiredFats());
        userEntity.setRequiredProtein(userDto.getRequiredProtein());
        userEntity.setDailyPlanEntities(dailyPlanMapper.mapDailyPlanListDtoToEntity(userDto.getDailyPlanDtos()));
        userEntity.setMealEntities(mealMapper.mapMealListDtoToEntity(userDto.getMealDto()));
        return userEntity;
    }

    public UserDto mapUserEntityToDto(UserEntity userEntity) {
        UserDto userDto = new UserDto();
        userDto.setId(userEntity.getId());
        userDto.setFirstName(userEntity.getFirstName());
        userDto.setLastName(userEntity.getLastName());
        userDto.setEmail(userEntity.getEmail());
        userDto.setRequiredCalories(userEntity.getRequiredCalories());
        userDto.setRequiredCarbs(userEntity.getRequiredCarbs());
        userDto.setRequiredFats(userEntity.getRequiredFats());
        userDto.setRequiredProtein(userEntity.getRequiredProtein());
        userDto.setDailyPlanDtos(dailyPlanMapper.mapDailyPlanListEntityToDto(userEntity.getDailyPlanEntities()));
        userDto.setMealDto(mealMapper.mapMealListEntityToDto(userEntity.getMealEntities()));
        return userDto;

    }
}
