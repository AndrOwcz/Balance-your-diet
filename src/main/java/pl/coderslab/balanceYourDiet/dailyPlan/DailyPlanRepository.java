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
}
