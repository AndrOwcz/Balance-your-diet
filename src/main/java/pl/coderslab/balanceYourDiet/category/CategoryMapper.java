package pl.coderslab.balanceYourDiet.category;

import org.springframework.stereotype.Component;
import pl.coderslab.balanceYourDiet.comment.CommentDto;
import pl.coderslab.balanceYourDiet.comment.CommentEntity;

import java.util.List;
import java.util.stream.Collectors;

@Component
public final class CategoryMapper {

    public CategoryEntity mapCategoryDtoToEntity(CategoryDto categoryDto) {
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setName(categoryDto.getName());
        return categoryEntity;
    }

    public CategoryDto mapCategoryEntityToDto(CategoryEntity categoryEntity) {
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setId(categoryEntity.getId());
        categoryDto.setName(categoryEntity.getName());
        return categoryDto;
    }

    public List<CategoryEntity> mapCategoryListDtoToEntity(List<CategoryDto> categoryDtos) {
        return categoryDtos.stream().map(this::mapCategoryDtoToEntity).collect(Collectors.toList());
    }

    public List<CategoryDto> mapCategoryListEntityToDto(List<CategoryEntity> categoryEntities) {
        return categoryEntities.stream().map(this::mapCategoryEntityToDto).collect(Collectors.toList());
    }
}
