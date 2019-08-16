package guru.springframework.spring5recipeapp.domain;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Builder
@Entity
public class Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private BigDecimal amount;

    @ManyToOne
    private UnitOfMeasure unitOfMeasure;

    @ManyToOne
    private Recipe recipe;

}
