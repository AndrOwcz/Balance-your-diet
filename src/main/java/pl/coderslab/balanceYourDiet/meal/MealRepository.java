package pl.coderslab.balanceYourDiet.meal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface MealRepository extends JpaRepository<MealEntity, Long> {

    List<MealEntity> findAllById(Long id);

    @Query("SELECT m from MealEntity m")
    List<MealEntity> findAllMeals();

    @Query("SELECT m from MealEntity m WHERE m.userEntity.id = ?1")
    List<MealEntity> findAllByUserEntityId(Long id);

    Optional<MealEntity> findById(Long id);

    @Query(value = "SELECT comments_id from balanceYourDietDB.meals AS m JOIN " +
            "balanceYourDietDB.meals_comments AS c ON m.id = c.MealEntity_id WHERE m.id = ?1", nativeQuery = true)
    List<Long> findAllCommentEntitiesIdByMealId(Long mealId);

    @Query(value = "SELECT DailyPlanEntity_id from balanceYourDietDB.meals AS m JOIN " +
            "balanceYourDietDB.dailyPlans_meals AS d ON m.id = d.mealEntities_id WHERE m.id = ?1", nativeQuery = true)
    List<Long> findAllDailyPlanIdsByMealId(Long mealId);

}
