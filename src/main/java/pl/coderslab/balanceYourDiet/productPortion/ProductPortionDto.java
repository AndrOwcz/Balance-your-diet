package pl.coderslab.balanceYourDiet.productPortion;

import pl.coderslab.balanceYourDiet.product.ProductDto;

import java.util.List;

public class ProductPortionDto {

    private Long id;

    private Long portion;

    private List<ProductDto> productDtos;

    public ProductPortionDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPortion() {
        return portion;
    }

    public void setPortion(Long portion) {
        this.portion = portion;
    }

    public List<ProductDto> getProductDtos() {
        return productDtos;
    }

    public void setProductDtos(List<ProductDto> productDtos) {
        this.productDtos = productDtos;
    }
}
