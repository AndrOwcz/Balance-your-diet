package pl.coderslab.balanceyourdiet.user;

import org.hibernate.validator.constraints.UniqueElements;
import pl.coderslab.balanceyourdiet.PlanEntity.PlanEntity;
import pl.coderslab.balanceyourdiet.comment.CommentEntity;
import pl.coderslab.balanceyourdiet.recipe.RecipeEntity;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @NotNull
    @Column(nullable = false)
    private String firstName;

    @NotBlank
    @NotNull
    @Column(nullable = false)
    private String lastName;

    @Email
    @UniqueElements
    @Column(nullable = false, unique = true)
    private String email;

    @NotNull
    @NotBlank
    @Column(nullable = false)
    private String password;

    @Column(scale = 2, precision = 8)
    private BigDecimal requiredCalories;

    @Column(scale = 2, precision = 8)
    private BigDecimal requiredCarbs;

    @Column(scale = 2, precision = 8)
    private BigDecimal requiredFats;

    @Column(scale = 2, precision = 8)
    private BigDecimal requiredProtein;

    @OneToMany
    private List<RecipeEntity> recipeEntities;

    @OneToMany
    private List<CommentEntity> commentEntities;

    @ManyToMany
    private List<PlanEntity> planEntities;

    public UserEntity() {
    }

}
