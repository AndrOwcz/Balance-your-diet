package pl.coderslab.balanceyourdiet.productEntity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
@Table(name = "products")
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @NotNull
    private String name;

    @Column(scale = 2, precision = 8)
    private BigDecimal calories;

    @Column(scale = 2, precision = 8)
    private BigDecimal carbs;

    @Column(scale = 2, precision = 8)
    private BigDecimal fats;

    @Column(scale = 2, precision = 8)
    private BigDecimal protein;

    public ProductEntity() {
    }
}
