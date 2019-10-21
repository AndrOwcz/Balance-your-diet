package pl.coderslab.balanceYourDiet.meal;

import pl.coderslab.balanceYourDiet.comment.CommentMapper;

import java.util.List;
import java.util.stream.Collectors;

public final class MealMapper {

    private final ProductPortionMapper productPortionMapper;
    private final CommentMapper commentMapper;

    public MealMapper(ProductPortionMapper productPortionMapper, CommentMapper commentMapper) {
        this.productPortionMapper = productPortionMapper;
        this.commentMapper = commentMapper;
    }

    public MealEntity mapMealDtoToEntity(MealDto mealDto) {
        MealEntity mealEntity = new MealEntity();
        mealEntity.setName(mealDto.getName());
        mealEntity.setDescription(mealDto.getDescription());
        mealEntity.setRecipeCalories(mealDto.getRecipeCalories());
        mealEntity.setRecipeCarbs(mealDto.getRecipeCarbs());
        mealEntity.setRecipeFats(mealDto.getRecipeFats());
        mealEntity.setRecipeProtein(mealDto.getRecipeProtein());
        mealEntity.setProductPortions(productPortionMapper.mapProductPortionDtoToEntity(mealDto.getProductPortionDtos()));
        mealEntity.setComments(commentMapper.mapCommentListDtoToEntity(mealDto.getComments()));

        return mealEntity;
    }

    public MealDto mapMealEntityToDto(MealEntity mealEntity) {
        MealDto mealDto = new MealDto();
        mealDto.setId(mealEntity.getId());
        mealDto.setName(mealEntity.getName());
        mealDto.setDescription(mealEntity.getDescription());
        mealDto.setRecipeCalories(mealEntity.getRecipeCalories());
        mealDto.setRecipeCarbs(mealEntity.getRecipeCarbs());
        mealDto.setRecipeFats(mealEntity.getRecipeFats());
        mealDto.setRecipeProtein(mealEntity.getRecipeProtein());
        mealDto.setProductPortionDtos(productPortionMapper.mapProductPortionEntityToDto(mealEntity.getProductPortions()));
        mealDto.setComments(commentMapper.mapCommentListEntityToDto(mealEntity.getComments()));

        return mealDto;
    }

    public List<MealEntity> mapMealListDtoToEntity(List<MealDto> mealDtos) {
        return mealDtos.stream().map(this::mapMealDtoToEntity).collect(Collectors.toList());
    }

    public List<MealDto> mapMealListEntityToDto(List<MealEntity> mealEntities) {
        return mealEntities.stream().map(this::mapMealEntityToDto).collect(Collectors.toList());
    }
}
