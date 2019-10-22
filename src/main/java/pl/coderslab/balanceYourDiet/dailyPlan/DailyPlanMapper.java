package pl.coderslab.balanceYourDiet.dailyPlan;

import org.springframework.stereotype.Component;
import pl.coderslab.balanceYourDiet.meal.MealMapper;
import pl.coderslab.balanceYourDiet.user.UserMapper;

import java.util.List;
import java.util.stream.Collectors;

@Component
public final class DailyPlanMapper {

    private MealMapper mealMapper;
    private UserMapper userMapper;

    public void setMealMapper(MealMapper mealMapper) {
        this.mealMapper = mealMapper;
    }

    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public DailyPlanEntity mapDailyPlanDtoToEntity(DailyPlanDto dailyPlanDto) {
        DailyPlanEntity dailyPlanEntity = new DailyPlanEntity();
        dailyPlanEntity.setName(dailyPlanDto.getName());
        dailyPlanEntity.setDescription(dailyPlanDto.getDescription());
        dailyPlanEntity.setUserEntity(userMapper.mapUserDtoToEntity(dailyPlanDto.getUserDto()));
        dailyPlanEntity.setMealEntities(mealMapper.mapMealListDtoToEntity(dailyPlanDto.getMealDtos()));

        return dailyPlanEntity;
    }

    public DailyPlanDto mapDailyPlanEntityToDto(DailyPlanEntity dailyPlanEntity) {
        DailyPlanDto dailyPlanDto = new DailyPlanDto();
        dailyPlanDto.setId(dailyPlanEntity.getId());
        dailyPlanDto.setName(dailyPlanEntity.getName());
        dailyPlanDto.setDescription(dailyPlanEntity.getDescription());
        dailyPlanDto.setUserDto(userMapper.mapUserEntityToDto(dailyPlanEntity.getUserEntity()));
        dailyPlanDto.setMealDtos(mealMapper.mapMealListEntityToDto(dailyPlanEntity.getMealEntities()));
        return dailyPlanDto;
    }

    public List<DailyPlanEntity> mapDailyPlanListDtoToEntity(List<DailyPlanDto> dailyPlanDtos) {
        return dailyPlanDtos.stream().map(this::mapDailyPlanDtoToEntity).collect(Collectors.toList());
    }

    public List<DailyPlanDto> mapDailyPlanListEntityToDto(List<DailyPlanEntity> dailyPlanEntities) {
        return dailyPlanEntities.stream().map(this::mapDailyPlanEntityToDto).collect(Collectors.toList());
    }
}
