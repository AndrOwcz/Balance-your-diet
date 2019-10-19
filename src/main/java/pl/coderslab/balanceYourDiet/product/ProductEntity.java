package pl.coderslab.balanceYourDiet.product;

import pl.coderslab.balanceYourDiet.category.CategoryEntity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "products")
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    @Column(scale = 2, precision = 8)
    private Long calories;

    @Column(scale = 2, precision = 8)
    private Long carbs;

    @Column(scale = 2, precision = 8)
    private Long fats;

    @Column(scale = 2, precision = 8)
    private Long protein;

    @ManyToOne
    private CategoryEntity categoryEntity;

    public ProductEntity() {
    }

    public Long getId() {
        return id;
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

    public CategoryEntity getCategoryEntity() {
        return categoryEntity;
    }

    public void setCategoryEntity(CategoryEntity categoryEntity) {
        this.categoryEntity = categoryEntity;
    }
}
