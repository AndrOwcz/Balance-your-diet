package pl.coderslab.balanceYourDiet.productPortion;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductPortionRepository extends JpaRepository<ProductPortionEntity, Long> {

    @Query(value = "SELECT productPortions_id from balanceYourDietDB.meals_productPortions AS p WHERE p.MealEntity_id = ?1", nativeQuery = true)
    List<Long> findAllProductPortionsIdsByMealId(Long mealId);

    @Query(value = "SELECT id, portion, productEntity_id from balanceYourDietDB.meals_productPortions AS p JOIN balanceYourDietDB.productPortions AS m ON p.productPortions_id = m.id WHERE p.MealEntity_id = ?1", nativeQuery = true)
    List<ProductPortionEntity> findAllProductPortionsByMealId(Long mealId);

    @Query(value = "SELECT productEntity_id from balanceYourDietDB.productPortions AS p WHERE p.id = ?1", nativeQuery = true)
    Long findProductEntityIdByProductPortionId(Long productPortionId);
}
