package pl.coderslab.balanceyourdiet.productPortion;

import pl.coderslab.balanceyourdiet.productEntity.ProductEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
@Table(name = "productPortions")
public class ProductPortionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(scale = 2, precision = 8, nullable = false)
    private BigDecimal portion;

    @Column(scale = 2, precision = 8)
    private BigDecimal portionCalories;

    @Column(scale = 2, precision = 8)
    private BigDecimal portionCarbs;

    @Column(scale = 2, precision = 8)
    private BigDecimal portionFats;

    @Column(scale = 2, precision = 8)
    private BigDecimal portionProtein;

    @ManyToOne
    private ProductEntity product;

    public ProductPortionEntity() {
    }
}
