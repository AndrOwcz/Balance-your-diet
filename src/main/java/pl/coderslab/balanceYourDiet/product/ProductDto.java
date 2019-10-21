package pl.coderslab.balanceYourDiet.product;

import pl.coderslab.balanceYourDiet.category.CategoryDto;

public class ProductDto {

    private Long id;

    private String name;

    private Long calories;

    private Long carbs;

    private Long fats;

    private Long protein;

    private CategoryDto categoryDto;

    public ProductDto() {
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

    public Long getCalories() {
        return calories;
    }

    public void setCalories(Long calories) {
        this.calories = calories;
    }

    public Long getCarbs() {
        return carbs;
    }

    public void setCarbs(Long carbs) {
        this.carbs = carbs;
    }

    public Long getFats() {
        return fats;
    }

    public void setFats(Long fats) {
        this.fats = fats;
    }

    public Long getProtein() {
        return protein;
    }

    public void setProtein(Long protein) {
        this.protein = protein;
    }

    public CategoryDto getCategoryDto() {
        return categoryDto;
    }

    public void setCategoryDto(CategoryDto categoryDto) {
        this.categoryDto = categoryDto;
    }
}
