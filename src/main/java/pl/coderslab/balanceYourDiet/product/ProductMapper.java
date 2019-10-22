package pl.coderslab.balanceYourDiet.product;

import org.springframework.stereotype.Component;
import pl.coderslab.balanceYourDiet.category.CategoryMapper;

import java.util.List;
import java.util.stream.Collectors;

@Component
public final class ProductMapper {

    private CategoryMapper categoryMapper;

    public ProductMapper(CategoryMapper categoryMapper) {
        this.categoryMapper = categoryMapper;
    }

    public ProductEntity mapProductDtoToEntity(ProductDto productDto) {
        ProductEntity productEntity = new ProductEntity();
        productEntity.setName(productDto.getName());
        productEntity.setCalories(productDto.getCalories());
        productEntity.setCarbs(productDto.getCarbs());
        productEntity.setFats(productDto.getFats());
        productEntity.setProtein(productDto.getProtein());
        productEntity.setCategoryEntity(categoryMapper.mapCategoryDtoToEntity(productDto.getCategoryDto()));

        return productEntity;
    }

    public ProductDto mapProductEntityToDto(ProductEntity productEntity) {
        ProductDto productDto = new ProductDto();
        productDto.setId(productEntity.getId());
        productDto.setName(productEntity.getName());
        productDto.setCalories(productEntity.getCalories());
        productDto.setCarbs(productEntity.getCarbs());
        productDto.setFats(productEntity.getFats());
        productDto.setProtein(productEntity.getProtein());
        productDto.setCategoryDto(categoryMapper.mapCategoryEntityToDto(productEntity.getCategoryEntity()));

        return productDto;
    }

    public List<ProductEntity> mapProductListDtoToEntity(List<ProductDto> productDtos) {
        return productDtos.stream().map(this::mapProductDtoToEntity).collect(Collectors.toList());
    }

    public List<ProductDto> mapProductListEntityToDto(List<ProductEntity> productEntities) {
        return productEntities.stream().map(this::mapProductEntityToDto).collect(Collectors.toList());
    }
}
