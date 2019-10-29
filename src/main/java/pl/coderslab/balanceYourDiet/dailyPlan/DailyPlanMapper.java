package pl.coderslab.balanceYourDiet.dailyPlan;

import org.springframework.stereotype.Component;
import pl.coderslab.balanceYourDiet.meal.MealMapper;
import pl.coderslab.balanceYourDiet.user.UserMapper;

import java.util.List;
import java.util.stream.Collectors;

@Component
public final class DailyPlanMapper {

    public DailyPlanEntity mapDailyPlanDtoToEntity(DailyPlanDto dailyPlanDto) {
        DailyPlanEntity dailyPlanEntity = new DailyPlanEntity();
        dailyPlanEntity.setName(dailyPlanDto.getName());
        dailyPlanEntity.setDescription(dailyPlanDto.getDescription());
        return dailyPlanEntity;
    }

    public DailyPlanDto mapDailyPlanEntityToDto(DailyPlanEntity dailyPlanEntity) {
        DailyPlanDto dailyPlanDto = new DailyPlanDto();
        dailyPlanDto.setId(dailyPlanEntity.getId());
        dailyPlanDto.setName(dailyPlanEntity.getName());
        dailyPlanDto.setDescription(dailyPlanEntity.getDescription());
        return dailyPlanDto;
    }

    public List<DailyPlanEntity> mapDailyPlanListDtoToEntity(List<DailyPlanDto> dailyPlanDtos) {
        return dailyPlanDtos.stream().map(this::mapDailyPlanDtoToEntity).collect(Collectors.toList());
    }

    public List<DailyPlanDto> mapDailyPlanListEntityToDto(List<DailyPlanEntity> dailyPlanEntities) {
        return dailyPlanEntities.stream().map(this::mapDailyPlanEntityToDto).collect(Collectors.toList());
    }
}
