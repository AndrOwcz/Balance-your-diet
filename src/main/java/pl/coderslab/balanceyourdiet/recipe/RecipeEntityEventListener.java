package pl.coderslab.balanceyourdiet.recipe;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.LocalDate;

public class RecipeEntityEventListener {
    @PrePersist
    public void prePersist(Object object) {
        RecipeEntity recipeEntity = (RecipeEntity) object;
        recipeEntity.setCreated(LocalDate.now());
    }

    @PreUpdate
    public void preUpdate(Object object) {
        RecipeEntity recipeEntity = (RecipeEntity) object;
        recipeEntity.setUpdated(LocalDate.now());
    }
}
