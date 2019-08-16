package guru.springframework.spring5recipeapp.bootstrap;

import guru.springframework.spring5recipeapp.domain.Difficulty;
import guru.springframework.spring5recipeapp.domain.Recipe;
import guru.springframework.spring5recipeapp.domain.UnitOfMeasure;
import guru.springframework.spring5recipeapp.repositories.RecipeRepository;
import guru.springframework.spring5recipeapp.repositories.UnitOfMeasureRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
public class RecipeBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private final RecipeRepository recipeRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;

    public RecipeBootstrap(RecipeRepository recipeRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
        this.recipeRepository = recipeRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @Transactional
    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        log.debug("Logging bootstrap data.");
        loadData();
    }

    private void loadData() {
        UnitOfMeasure count = unitOfMeasureRepository.findByDescription("Count").orElseThrow(NullPointerException::new);
        UnitOfMeasure teaspoon = unitOfMeasureRepository.findByDescription("Teaspoon").orElseThrow(NullPointerException::new);
        UnitOfMeasure tablespoon = unitOfMeasureRepository.findByDescription("Tablespoon").orElseThrow(NullPointerException::new);
        UnitOfMeasure dash = unitOfMeasureRepository.findByDescription("Dash").orElseThrow(NullPointerException::new);

        Recipe perfectGuacamole = Recipe.builder()
                .description("Just Do It")
                .difficulty(Difficulty.EASY)
                .build();
        perfectGuacamole
                .addIngredient("ripe avocados", 2, count)
                .addIngredient("kosher salt", .5, count)
                .addIngredient("fresh lime or lemon juice", 1, tablespoon)
                .addIngredient("minced red onion or thinly sliced green onion", 2, tablespoon)
                .addIngredient("serrano chiles, stems and seeds removed, minced", 2, count)
                .addIngredient("cilantro (leaves and tender stems), finley chopped", 2, tablespoon)
                .addIngredient("freshly grated black pepper", 1, dash)
                .addIngredient("ripe tomato, seeds and pulp removed, chopped", .5, count);
        recipeRepository.save(perfectGuacamole);

        log.debug("Saved perfect guacamole");
    }
}
