package pl.coderslab.balanceYourDiet.productPortion;

import pl.coderslab.balanceYourDiet.product.ProductEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "productPortions")
public class ProductPortionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(scale = 2, precision = 8, nullable = false)
    private Double portion;

    @ManyToOne (fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.REFRESH})
    private ProductEntity productEntity;

    public ProductPortionEntity() {
    }

    public Long getId() {
        return id;
    }

    public Double getPortion() {
        return portion;
    }

    public void setPortion(Double portion) {
        this.portion = portion;
    }


    public ProductEntity getProductEntity() {
        return productEntity;
    }

    public void setProductEntity(ProductEntity productEntity) {
        this.productEntity = productEntity;
    }
}
