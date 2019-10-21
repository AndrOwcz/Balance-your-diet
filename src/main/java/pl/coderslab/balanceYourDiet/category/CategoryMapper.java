package pl.coderslab.balanceYourDiet.category;

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
}
