package pl.coderslab.balanceYourDiet.meal;

import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.coderslab.balanceYourDiet.comment.CommentEntity;
import pl.coderslab.balanceYourDiet.exception.MealNotFoundException;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class MealService {

    private final MealRepository mealRepository;
    private final MealMapper mealMapper;

    public MealService(MealRepository mealRepository, MealMapper mealMapper) {
        this.mealRepository = mealRepository;
        this.mealMapper = mealMapper;
    }

    public MealEntity mapDtoToEntity(MealDto mealDto) {
        return mealMapper.mapMealDtoToEntity(mealDto);
    }

    public MealDto mapEntityToDto(MealEntity mealEntity) {
        return mealMapper.mapMealEntityToDto(mealEntity);
    }

    public List<MealEntity> mapListDtoToEntity(List<MealDto> mealDtos) {
        return mealMapper.mapMealListDtoToEntity(mealDtos);
    }

    public List<MealDto> mapMealListEntityToDto(List<MealEntity> mealEntities) {
        return mealMapper.mapMealListEntityToDto(mealEntities);
    }

    public List<MealDto> mapMealListEntityToDtoWithUsers(List<MealEntity> mealEntities) {
        return mealMapper.mapMealListEntityToDtoWithUsers(mealEntities);
    }

    public List<MealEntity> findAllById(Long id) {
        return mealRepository.findAllById(id);
    }

    public List<MealEntity> findAll() {
        return mealRepository.findAllMeals();
    }

    public List<MealEntity> findAllByUserId(Long id) {
        return mealRepository.findAllByUserEntityId(id);
    }

    public Optional<MealEntity> findById(Long id) {
        return mealRepository.findById(id);
    }

    public List<Long> findAllCommentEntitiesIdByMealId(Long mealId) {
        return mealRepository.findAllCommentEntitiesIdByMealId(mealId);
    }

    public void deleteById(Long id) {
        mealRepository.deleteById(id);
    }

    public List<Long> findAllDailyPlanIdsByMealId(Long id) {
        return mealRepository.findAllDailyPlanIdsByMealId(id);
    }

    public MealEntity save(MealEntity mealEntity) {
        return mealRepository.save(mealEntity);
    }

    public MealEntity findByIdWithComments(Long id) {
        MealEntity mealEntity = mealRepository.findById(id).orElseThrow(MealNotFoundException::new);
        Hibernate.initialize(mealEntity.getComments());
        return mealEntity;
    }
}
