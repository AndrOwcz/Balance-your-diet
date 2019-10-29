package pl.coderslab.balanceYourDiet.user;

import pl.coderslab.balanceYourDiet.dailyPlan.DailyPlanDto;
import pl.coderslab.balanceYourDiet.meal.MealDto;

import java.util.List;

public class UserDto {


    private Long id;

    private String firstName;

    private String lastName;

    private String username;

    private String password;

    private Long requiredCalories;

    private Long requiredCarbs;

    private Long requiredFats;

    private Long requiredProtein;

    private List<MealDto> mealDto;

    private List<DailyPlanDto> dailyPlanDtos;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getRequiredCalories() {
        return requiredCalories;
    }

    public void setRequiredCalories(Long requiredCalories) {
        this.requiredCalories = requiredCalories;
    }

    public Long getRequiredCarbs() {
        return requiredCarbs;
    }

    public void setRequiredCarbs(Long requiredCarbs) {
        this.requiredCarbs = requiredCarbs;
    }

    public Long getRequiredFats() {
        return requiredFats;
    }

    public void setRequiredFats(Long requiredFats) {
        this.requiredFats = requiredFats;
    }

    public Long getRequiredProtein() {
        return requiredProtein;
    }

    public void setRequiredProtein(Long requiredProtein) {
        this.requiredProtein = requiredProtein;
    }

    public List<MealDto> getMealDto() {
        return mealDto;
    }

    public void setMealDto(List<MealDto> mealDto) {
        this.mealDto = mealDto;
    }

    public List<DailyPlanDto> getDailyPlanDtos() {
        return dailyPlanDtos;
    }

    public void setDailyPlanDtos(List<DailyPlanDto> dailyPlanDtos) {
        this.dailyPlanDtos = dailyPlanDtos;
    }
}
