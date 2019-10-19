package pl.coderslab.balanceyourdiet.productPortion;

import pl.coderslab.balanceyourdiet.product.ProductEntity;

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
    private Long portion;

    @ManyToMany
    private List<ProductEntity> productEntities;

    public ProductPortionEntity() {
    }

    public Long getId() {
        return id;
    }

    public Long getPortion() {
        return portion;
    }

    public void setPortion(Long portion) {
        this.portion = portion;
    }

    public List<ProductEntity> getProductEntities() {
        return productEntities;
    }

    public void setProductEntities(List<ProductEntity> productEntities) {
        this.productEntities = productEntities;
    }
}
