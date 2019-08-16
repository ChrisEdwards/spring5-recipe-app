package guru.springframework.spring5recipeapp.domain;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Builder
@EqualsAndHashCode(exclude = {"recipe"})
@Entity
@NoArgsConstructor
@AllArgsConstructor
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
