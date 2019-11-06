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

    private Double mealCalories;

    private Double mealCarbs;

    private Double mealFats;

    private Double mealProtein;

    private List<CommentDto> comments;

    private List<ProductPortionDto> productPortionDtos;

    private UserDto userDto;

    private Long newProductId;

    private ProductPortionDto newProductPortionDto;

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

    public Double getMealCalories() {
        return mealCalories;
    }

    public void setMealCalories(Double mealCalories) {
        this.mealCalories = mealCalories;
    }

    public Double getMealCarbs() {
        return mealCarbs;
    }

    public void setMealCarbs(Double mealCarbs) {
        this.mealCarbs = mealCarbs;
    }

    public Double getMealFats() {
        return mealFats;
    }

    public void setMealFats(Double mealFats) {
        this.mealFats = mealFats;
    }

    public Double getMealProtein() {
        return mealProtein;
    }

    public void setMealProtein(Double mealProtein) {
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

    public Long getNewProductId() {
        return newProductId;
    }

    public void setNewProductId(Long newProductId) {
        this.newProductId = newProductId;
    }

    public ProductPortionDto getNewProductPortionDto() {
        return newProductPortionDto;
    }

    public void setNewProductPortionDto(ProductPortionDto newProductPortionDto) {
        this.newProductPortionDto = newProductPortionDto;
    }
}
