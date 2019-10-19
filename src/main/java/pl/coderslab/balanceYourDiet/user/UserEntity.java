package pl.coderslab.balanceYourDiet.user;

import org.hibernate.validator.constraints.UniqueElements;
import pl.coderslab.balanceYourDiet.dailyPlan.DailyPlanEntity;
import pl.coderslab.balanceYourDiet.meal.MealEntity;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String firstName;

    @NotBlank
    @Column(nullable = false)
    private String lastName;

    @Email
    @NotBlank
    @Column(nullable = false, unique = true)
    private String email;

    @NotBlank
    @Column(nullable = false)
    private String password;

    @Column(scale = 2, precision = 8)
    private Long requiredCalories;

    @Column(scale = 2, precision = 8)
    private Long requiredCarbs;

    @Column(scale = 2, precision = 8)
    private Long requiredFats;

    @Column(scale = 2, precision = 8)
    private Long requiredProtein;

    @OneToMany(mappedBy = "userEntity", fetch = FetchType.LAZY)
    private List<MealEntity> mealEntities;

    @OneToMany(mappedBy = "userEntity", fetch = FetchType.LAZY)
    private List<DailyPlanEntity> dailyPlanEntities;

    public UserEntity() {
    }

    public Long getId() {
        return id;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public List<MealEntity> getMealEntities() {
        return mealEntities;
    }

    public void setMealEntities(List<MealEntity> mealEntities) {
        this.mealEntities = mealEntities;
    }

    public List<DailyPlanEntity> getDailyPlanEntities() {
        return dailyPlanEntities;
    }

    public void setDailyPlanEntities(List<DailyPlanEntity> dailyPlanEntities) {
        this.dailyPlanEntities = dailyPlanEntities;
    }
}
