package pl.coderslab.balanceYourDiet.productPortion;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductPortionRepository extends JpaRepository<ProductPortionEntity, Long> {

    @Query(value = "SELECT productPortions_id from balanceYourDietDB.meals_productPortions AS p WHERE p.MealEntity_id = ?1", nativeQuery = true)
    List<Long> findAllProductPortionsIdsByMealId(Long mealId);
}
