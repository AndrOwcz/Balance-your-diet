package pl.coderslab.balanceyourdiet.PlanEntity;

import pl.coderslab.balanceyourdiet.recipe.RecipeEntity;
import pl.coderslab.balanceyourdiet.user.UserEntity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "plans")
public class PlanEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @NotNull
    private String name;

    private String description;

    @ManyToMany
    private List<UserEntity> userEntityList;

    @ManyToMany
    private List<RecipeEntity> recipeEntities;

    public PlanEntity() {
    }
}
