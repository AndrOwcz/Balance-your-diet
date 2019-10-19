package pl.coderslab.balanceyourdiet.recipe;

import pl.coderslab.balanceyourdiet.comment.CommentEntity;
import pl.coderslab.balanceyourdiet.productPortion.ProductPortionEntity;
import pl.coderslab.balanceyourdiet.user.UserEntity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@EntityListeners(RecipeEntityEventListener.class)
@Table(name = "recipes")
public class RecipeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @NotNull
    @Column(nullable = false)
    private String name;

    private String description;

    private LocalDate created;

    private LocalDate updated;

    private Long preparationTime;

    @Column(scale = 2, precision = 8)
    private BigDecimal recipeCalories;

    @Column(scale = 2, precision = 8)
    private BigDecimal recipeCarbs;

    @Column(scale = 2, precision = 8)
    private BigDecimal recipeFats;

    @Column(scale = 2, precision = 8)
    private BigDecimal recipeProtein;

    @OneToMany
    private List<CommentEntity> comments;

    @ManyToMany
    private List<ProductPortionEntity> productPortions;

    @ManyToOne
    private UserEntity userEntity;

    public RecipeEntity() {
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
}
