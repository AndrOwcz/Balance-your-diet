package pl.coderslab.balanceyourdiet.dailyPlanEntity;

import pl.coderslab.balanceyourdiet.meal.MealEntity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Table(name = "dailyPlans")
public class DailyPlanEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    private String description;

    @ManyToMany
    private List<MealEntity> mealEntities;

    public DailyPlanEntity() {
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

    public List<MealEntity> getMealEntities() {
        return mealEntities;
    }

    public void setMealEntities(List<MealEntity> mealEntities) {
        this.mealEntities = mealEntities;
    }
}
