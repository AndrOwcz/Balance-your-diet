package pl.coderslab.balanceYourDiet.meal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Set;

public interface MealRepository extends JpaRepository<MealEntity, Long> {

    Set<MealEntity> findAllById(Long id);

    @Query("SELECT m from MealEntity m")
    Set<MealEntity> findAllMeals();
}
