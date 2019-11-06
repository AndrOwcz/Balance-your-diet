package pl.coderslab.balanceYourDiet.product;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
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
    private Double calories;

    @Column(scale = 2, precision = 8)
    private Double carbs;

    @Column(scale = 2, precision = 8)
    private Double fats;

    @Column(scale = 2, precision = 8)
    private Double protein;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
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

    public Double getCalories() {
        return calories;
    }

    public void setCalories(Double calories) {
        this.calories = calories;
    }

    public Double getCarbs() {
        return carbs;
    }

    public void setCarbs(Double carbs) {
        this.carbs = carbs;
    }

    public Double getFats() {
        return fats;
    }

    public void setFats(Double fats) {
        this.fats = fats;
    }

    public Double getProtein() {
        return protein;
    }

    public void setProtein(Double protein) {
        this.protein = protein;
    }

    public CategoryEntity getCategoryEntity() {
        return categoryEntity;
    }

    public void setCategoryEntity(CategoryEntity categoryEntity) {
        this.categoryEntity = categoryEntity;
    }
}
