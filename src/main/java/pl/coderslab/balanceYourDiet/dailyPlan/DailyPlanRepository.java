package pl.coderslab.balanceYourDiet.dailyPlan;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DailyPlanRepository extends JpaRepository<DailyPlanEntity, Long> {
}
