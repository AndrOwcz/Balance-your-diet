package pl.coderslab.balanceYourDiet.productPortion;

import org.springframework.stereotype.Component;
import pl.coderslab.balanceYourDiet.product.ProductMapper;

import java.util.List;
import java.util.stream.Collectors;

@Component
public final class ProductPortionMapper {

    private final ProductMapper productMapper;

    public ProductPortionMapper(ProductMapper productMapper) {
        this.productMapper = productMapper;
    }


    public ProductPortionEntity mapProductPortionDtoToEntity(ProductPortionDto productPortionDto) {
        ProductPortionEntity productPortionEntity = new ProductPortionEntity();
        productPortionEntity.setPortion(productPortionDto.getPortion());
        return productPortionEntity;
    }

    public ProductPortionEntity mapProductPortionDtoToEntityWithProduct(ProductPortionDto productPortionDto) {
        ProductPortionEntity productPortionEntity = new ProductPortionEntity();
        productPortionEntity.setPortion(productPortionDto.getPortion());
        productPortionEntity.setProductEntity(productMapper.mapProductDtoToEntity(productPortionDto.getProductDto()));
        return productPortionEntity;
    }

    public ProductPortionDto mapProductPortionEntityToDto(ProductPortionEntity productPortionEntity) {
        ProductPortionDto productPortionDto = new ProductPortionDto();
        productPortionDto.setId(productPortionEntity.getId());
        productPortionDto.setPortion(productPortionEntity.getPortion());
        return productPortionDto;
    }

    public List<ProductPortionEntity> mapProductPortionListDtoToEntity(List<ProductPortionDto> productPortionDtos) {
        return productPortionDtos.stream().map(this::mapProductPortionDtoToEntity).collect(Collectors.toList());
    }

    public List<ProductPortionDto> mapProductPortionListEntityToDto(List<ProductPortionEntity> productPortionEntities) {
        return productPortionEntities.stream().map(this::mapProductPortionEntityToDto).collect(Collectors.toList());
    }
}
