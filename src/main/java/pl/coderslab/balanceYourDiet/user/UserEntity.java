package pl.coderslab.balanceYourDiet.user;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import pl.coderslab.balanceYourDiet.dailyPlan.DailyPlanEntity;
import pl.coderslab.balanceYourDiet.meal.MealEntity;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
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
    private String username;

    @NotBlank
    @Column(nullable = false)
    private String password;

    @Min(0)
    @Column(scale = 2, precision = 8)
    private Long requiredCalories;

    @Min(0)
    @Column(scale = 2, precision = 8)
    private Long requiredCarbs;

    @Min(0)
    @Column(scale = 2, precision = 8)
    private Long requiredFats;

    @Min(0)
    @Column(scale = 2, precision = 8)
    private Long requiredProtein;

    @OneToMany(mappedBy = "userEntity", fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<MealEntity> mealEntities;

    @OneToMany(mappedBy = "userEntity", fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<DailyPlanEntity> dailyPlanEntities;

    private boolean enabled;

    public UserEntity() {
    }

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

    public void setUsername(String email) {
        this.username = email;
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

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
