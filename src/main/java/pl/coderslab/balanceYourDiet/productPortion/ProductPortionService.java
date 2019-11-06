package pl.coderslab.balanceYourDiet.productPortion;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProductPortionService {

    private final ProductPortionMapper productPortionMapper;
    private final ProductPortionRepository productPortionRepository;

    public ProductPortionService(ProductPortionMapper productPortionMapper, ProductPortionRepository productPortionRepository) {
        this.productPortionMapper = productPortionMapper;
        this.productPortionRepository = productPortionRepository;
    }

    public ProductPortionEntity mapDtoToEntity(ProductPortionDto productPortionDto) {
        return productPortionMapper.mapProductPortionDtoToEntity(productPortionDto);
    }

    public ProductPortionEntity mapDtoToEntityWithProduct(ProductPortionDto productPortionDto) {
        return productPortionMapper.mapProductPortionDtoToEntityWithProduct(productPortionDto);
    }

    public ProductPortionDto mapEntityToDto(ProductPortionEntity productPortionEntity) {
        return productPortionMapper.mapProductPortionEntityToDto(productPortionEntity);
    }

    public List<ProductPortionEntity> mapListDtoToEntity(List<ProductPortionDto> productDtos) {
        return productPortionMapper.mapProductPortionListDtoToEntity(productDtos);
    }

    public List<ProductPortionDto> mapListEntityToDto(List<ProductPortionEntity> productPortionEntities) {
        return productPortionMapper.mapProductPortionListEntityToDto(productPortionEntities);
    }

    public List<ProductPortionEntity> findAll() {
        return productPortionRepository.findAll();
    }

    public Optional<ProductPortionEntity> findById(Long categoryEntityId) {
        return productPortionRepository.findById(categoryEntityId);
    }

    public ProductPortionEntity save(ProductPortionEntity productPortionEntity) {
        return productPortionRepository.save(productPortionEntity);
    }

    public List<Long> findAllProductPortionsIdsByMealId(Long mealId) {
        return productPortionRepository.findAllProductPortionsIdsByMealId(mealId);
    }
}


