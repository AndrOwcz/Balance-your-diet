package pl.coderslab.balanceYourDiet.meal;

import pl.coderslab.balanceYourDiet.comment.CommentEntity;
import pl.coderslab.balanceYourDiet.productPortion.ProductPortionEntity;
import pl.coderslab.balanceYourDiet.user.UserEntity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "meals")
public class MealEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String name;

    private String description;

    private LocalDate created;

    @PrePersist
    public void prePersist() {
        created = LocalDate.now();
    }

    private LocalDate updated;

    @PreUpdate
    public void preUpdate() {
        updated = LocalDate.now();
    }

    @Column(scale = 2, precision = 8)
    private Long mealCalories;

    @Column(scale = 2, precision = 8)
    private Long mealCarbs;

    @Column(scale = 2, precision = 8)
    private Long mealFats;

    @Column(scale = 2, precision = 8)
    private Long mealProtein;

    @OneToMany (fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<CommentEntity> comments;

    @OneToMany (fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<ProductPortionEntity> productPortions;

    @ManyToOne
    private UserEntity userEntity;

    public MealEntity() {
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

    public void setMealCalories(Long recipeCalories) {
        this.mealCalories = recipeCalories;
    }

    public Long getMealCarbs() {
        return mealCarbs;
    }

    public void setMealCarbs(Long recipeCarbs) {
        this.mealCarbs = recipeCarbs;
    }

    public Long getMealFats() {
        return mealFats;
    }

    public void setMealFats(Long recipeFats) {
        this.mealFats = recipeFats;
    }

    public Long getMealProtein() {
        return mealProtein;
    }

    public void setMealProtein(Long recipeProtein) {
        this.mealProtein = recipeProtein;
    }

    public List<CommentEntity> getComments() {
        return comments;
    }

    public void setComments(List<CommentEntity> comments) {
        this.comments = comments;
    }

    public List<ProductPortionEntity> getProductPortions() {
        return productPortions;
    }

    public void setProductPortions(List<ProductPortionEntity> productPortions) {
        this.productPortions = productPortions;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }
}
