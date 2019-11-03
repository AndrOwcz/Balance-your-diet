package pl.coderslab.balanceYourDiet.category;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    public CategoryService(CategoryRepository categoryRepository, CategoryMapper categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }

    public CategoryEntity mapDtoToEntity(CategoryDto categoryDto) {
        return categoryMapper.mapCategoryDtoToEntity(categoryDto);
    }

    public CategoryDto mapEntityToDto(CategoryEntity categoryEntity) {
        return categoryMapper.mapCategoryEntityToDto(categoryEntity);
    }

    public List<CategoryEntity> mapCategoryListDtoToEntity(List<CategoryDto> categoryDtos) {
        return categoryMapper.mapCategoryListDtoToEntity(categoryDtos);
    }

    public List<CategoryDto> mapCategoryListEntityToDto(List<CategoryEntity> categoryEntities) {
        return categoryMapper.mapCategoryListEntityToDto(categoryEntities);
    }

    public List<CategoryEntity> findAll() {
        return categoryRepository.findAll();
    }

    public Optional<CategoryEntity> findById(Long categoryId) {
        return categoryRepository.findById(categoryId);
    }
}
