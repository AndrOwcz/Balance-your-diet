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
    @UniqueElements
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

    @OneToMany
    private List<MealEntity> mealEntities;

    @OneToMany
    private List<DailyPlanEntity> dailyPlanEntities;

    public UserEntity() {
    }

}
