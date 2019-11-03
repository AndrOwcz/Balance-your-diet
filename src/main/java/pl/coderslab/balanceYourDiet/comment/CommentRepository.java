package pl.coderslab.balanceYourDiet.comment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.coderslab.balanceYourDiet.meal.MealEntity;

import java.util.List;

public interface CommentRepository extends JpaRepository<CommentEntity, Long> {

    List<CommentEntity> findAllById(Long id);

    @Query("SELECT c from CommentEntity c WHERE c.userEntity.id = ?1")
    List<CommentEntity> findAllByUserEntityId(Long id);
}
