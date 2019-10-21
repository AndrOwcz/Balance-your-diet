package pl.coderslab.balanceYourDiet.dailyPlan;

import pl.coderslab.balanceYourDiet.meal.MealMapper;
import pl.coderslab.balanceYourDiet.user.UserMapper;

import java.util.List;
import java.util.stream.Collectors;

public final class DailyPlanMapper {

    private final MealMapper mealMapper;
    private final UserMapper userMapper;

    public DailyPlanMapper(MealMapper mealMapper, UserMapper userMapper) {
        this.userMapper = userMapper;
        this.mealMapper = mealMapper;
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
