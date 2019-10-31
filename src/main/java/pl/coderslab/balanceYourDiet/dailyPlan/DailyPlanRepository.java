package pl.coderslab.balanceYourDiet.dailyPlan;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DailyPlanRepository extends JpaRepository<DailyPlanEntity, Long> {

    List<DailyPlanEntity> findAllById(Long id);

    @Query("SELECT d from DailyPlanEntity d")
    List<DailyPlanEntity> findAllDailyPlans();

    @Query("SELECT d from DailyPlanEntity d WHERE d.userEntity.id = ?1")
    List<DailyPlanEntity> findAllByUserId(Long id);

    @Query(value = "SELECT mealEntities_id from balanceYourDietDB.dailyPlans AS d JOIN " +
            "balanceYourDietDB.dailyPlans_meals AS m ON d.id = m.DailyPlanEntity_id WHERE d.id = ?1", nativeQuery = true)
    List<Long> findAllMealEntitiesIdByDailyPlanId(Long dailyPlanId);


}
