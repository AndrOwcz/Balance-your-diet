package pl.coderslab.balanceYourDiet.meal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MealRepository extends JpaRepository<MealEntity, Long> {

    List<MealEntity> findAllById(Long id);

    @Query("SELECT m from MealEntity m")
    List<MealEntity> findAllMeals();

    @Query("SELECT m from MealEntity m WHERE m.userEntity.id = ?1")
    List<MealEntity> findAllByUserEntityId(Long id);
}
