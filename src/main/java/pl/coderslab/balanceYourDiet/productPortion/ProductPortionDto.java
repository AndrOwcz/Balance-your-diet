package pl.coderslab.balanceYourDiet.productPortion;

import pl.coderslab.balanceYourDiet.product.ProductDto;

public class ProductPortionDto {

    private Long id;

    private Double portion;

    private ProductDto productDto;

    public ProductPortionDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getPortion() {
        return portion;
    }

    public void setPortion(Double portion) {
        this.portion = portion;
    }

    public ProductDto getProductDto() {
        return productDto;
    }

    public void setProductDto(ProductDto productDto) {
        this.productDto = productDto;
    }
}
