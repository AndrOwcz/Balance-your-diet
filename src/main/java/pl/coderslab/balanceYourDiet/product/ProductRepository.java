package pl.coderslab.balanceYourDiet.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

    @Query("SELECT p from ProductEntity p WHERE p.categoryEntity.id = ?1")
    List<ProductEntity> findAllByCategoryId(Long categoryId);

    @Query(value = "SELECT * from balanceYourDietDB.products AS p JOIN balanceYourDietDB.categories AS c ON p.categoryEntity_id=c.id", nativeQuery = true)
    List<ProductEntity> findAllWithCategories();
}
