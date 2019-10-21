package pl.coderslab.balanceYourDiet.dailyPlan;

import pl.coderslab.balanceYourDiet.meal.MealDto;
import pl.coderslab.balanceYourDiet.user.UserDto;

import java.util.List;

public class DailyPlanDto {

    private Long id;

    private String name;

    private String description;

    private UserDto userDto;

    private List<MealDto> mealDtos;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public UserDto getUserDto() {
        return userDto;
    }

    public void setUserDto(UserDto userDto) {
        this.userDto = userDto;
    }

    public List<MealDto> getMealDtos() {
        return mealDtos;
    }

    public void setMealDtos(List<MealDto> mealDtos) {
        this.mealDtos = mealDtos;
    }
}
