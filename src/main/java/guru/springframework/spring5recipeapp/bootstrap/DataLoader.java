package guru.springframework.spring5recipeapp.bootstrap;

import guru.springframework.spring5recipeapp.domain.Difficulty;
import guru.springframework.spring5recipeapp.domain.Ingredient;
import guru.springframework.spring5recipeapp.domain.Recipe;
import guru.springframework.spring5recipeapp.domain.UnitOfMeasure;
import guru.springframework.spring5recipeapp.repositories.RecipeRepository;
import guru.springframework.spring5recipeapp.repositories.UnitOfMeasureRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class DataLoader implements CommandLineRunner {

    private final RecipeRepository recipeRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;

    public DataLoader(RecipeRepository recipeRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
        this.recipeRepository = recipeRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        loadData();
    }

    private void loadData() {
        UnitOfMeasure count = unitOfMeasureRepository.findByDescription("Count").get();
        UnitOfMeasure teaspoon = unitOfMeasureRepository.findByDescription("Teaspoon").get();
        UnitOfMeasure tablespoon = unitOfMeasureRepository.findByDescription("Tablespoon").get();
        UnitOfMeasure dash = unitOfMeasureRepository.findByDescription("Dash").get();

        Recipe perfectGuacamole = new Recipe();
        Ingredient avocados = (new Ingredient())
                .setDescription("ripe avocados")
                .setAmount(BigDecimal.valueOf(2))
                .setUnitOfMeasure(count);
        perfectGuacamole.getIngredients().add(avocados);
        Ingredient salt = (new Ingredient())
                .setDescription("kosher salt")
                .setAmount(BigDecimal.valueOf(.5))
                .setUnitOfMeasure(teaspoon);
        perfectGuacamole.getIngredients().add(salt);
        Ingredient juice = (new Ingredient())
                .setDescription("fresh lime or lemon juice")
                .setAmount(BigDecimal.valueOf(1))
                .setUnitOfMeasure(tablespoon);
        perfectGuacamole.getIngredients().add(juice);
        Ingredient onion = (new Ingredient())
                .setDescription("minced red onion or thinly sliced green onion")
                .setAmount(BigDecimal.valueOf(2))
                .setUnitOfMeasure(tablespoon);
        perfectGuacamole.getIngredients().add(onion);
        Ingredient chiles = (new Ingredient())
                .setDescription("serrano chiles, stems and seeds removed, minced")
                .setAmount(BigDecimal.valueOf(2))
                .setUnitOfMeasure(count);
        perfectGuacamole.getIngredients().add(chiles);
        Ingredient cilantro = (new Ingredient())
                .setDescription("cilantro (leaves and tender stems), finley chopped")
                .setAmount(BigDecimal.valueOf(2))
                .setUnitOfMeasure(tablespoon);
        perfectGuacamole.getIngredients().add(cilantro);
        Ingredient pepper = (new Ingredient())
                .setDescription("freshly grated black pepper")
                .setAmount(BigDecimal.valueOf(1))
                .setUnitOfMeasure(dash);
        perfectGuacamole.getIngredients().add(pepper);
        Ingredient tomato = (new Ingredient())
                .setDescription("ripe tomato, seeds and pulp removed, chopped")
                .setAmount(BigDecimal.valueOf(.5))
                .setUnitOfMeasure(count);
        perfectGuacamole.getIngredients().add(tomato);
        perfectGuacamole
                .setDescription("Just do it.")
                .setDescription("Yummy!")
                .setDifficulty(Difficulty.EASY);
        recipeRepository.save(perfectGuacamole);

    }
}
