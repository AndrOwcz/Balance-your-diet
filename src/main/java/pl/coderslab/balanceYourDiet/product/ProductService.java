package pl.coderslab.balanceYourDiet.product;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProductService {

    private final ProductMapper productMapper;
    private final ProductRepository productRepository;

    public ProductService(ProductMapper productMapper, ProductRepository productRepository) {
        this.productMapper = productMapper;
        this.productRepository = productRepository;
    }

    public ProductEntity mapDtoToEntity(ProductDto productDto) {
        return productMapper.mapProductDtoToEntity(productDto);
    }

    public ProductDto mapEntityToDto(ProductEntity productEntity) {
        return productMapper.mapProductEntityToDto(productEntity);
    }

    public List<ProductEntity> mapListDtoToEntity(List<ProductDto> productDtos) {
        return productMapper.mapProductListDtoToEntity(productDtos);
    }

    public List<ProductDto> mapListEntityToDto(List<ProductEntity> productEntities) {
        return productMapper.mapProductListEntityToDto(productEntities);
    }

    public List<ProductDto> mapListEntityToDtoWithCategories(List<ProductEntity> productEntities) {
        return productMapper.mapProductListEntityToDtoWithCategories(productEntities);
    }

    public List<ProductEntity> findAll() {
        return productRepository.findAll();
    }

    public List<ProductEntity> findAllByCategoryId(Long categoryId) {
        return productRepository.findAllByCategoryId(categoryId);
    }

    public List<ProductEntity> findAllWithCategories() {
        return productRepository.findAllWithCategories();
    }


    public Optional<ProductEntity> findById(Long id) {
        return productRepository.findById(id);
    }

    public ProductEntity save(ProductEntity productEntity) {
        return productRepository.save(productEntity);
    }
}
