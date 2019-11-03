package pl.coderslab.balanceYourDiet.meal;

import pl.coderslab.balanceYourDiet.comment.CommentDto;
import pl.coderslab.balanceYourDiet.productPortion.ProductPortionDto;
import pl.coderslab.balanceYourDiet.user.UserDto;

import java.time.LocalDate;
import java.util.List;

public class MealDto {

    private Long id;

    private String name;

    private String description;

    private LocalDate created;

    private LocalDate updated;

    private Long mealCalories;

    private Long mealCarbs;

    private Long mealFats;

    private Long mealProtein;

    private List<CommentDto> comments;

    private List<ProductPortionDto> productPortionDtos;

    private UserDto userDto;

    public MealDto() {
    }

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

    public LocalDate getCreated() {
        return created;
    }

    public void setCreated(LocalDate created) {
        this.created = created;
    }

    public LocalDate getUpdated() {
        return updated;
    }

    public void setUpdated(LocalDate updated) {
        this.updated = updated;
    }

    public Long getMealCalories() {
        return mealCalories;
    }

    public void setMealCalories(Long mealCalories) {
        this.mealCalories = mealCalories;
    }

    public Long getMealCarbs() {
        return mealCarbs;
    }

    public void setMealCarbs(Long mealCarbs) {
        this.mealCarbs = mealCarbs;
    }

    public Long getMealFats() {
        return mealFats;
    }

    public void setMealFats(Long mealFats) {
        this.mealFats = mealFats;
    }

    public Long getMealProtein() {
        return mealProtein;
    }

    public void setMealProtein(Long mealProtein) {
        this.mealProtein = mealProtein;
    }

    public List<CommentDto> getComments() {
        return comments;
    }

    public void setComments(List<CommentDto> comments) {
        this.comments = comments;
    }

    public List<ProductPortionDto> getProductPortionDtos() {
        return productPortionDtos;
    }

    public void setProductPortionDtos(List<ProductPortionDto> productPortionDtos) {
        this.productPortionDtos = productPortionDtos;
    }

    public UserDto getUserDto() {
        return userDto;
    }

    public void setUserDto(UserDto userDto) {
        this.userDto = userDto;
    }
}
