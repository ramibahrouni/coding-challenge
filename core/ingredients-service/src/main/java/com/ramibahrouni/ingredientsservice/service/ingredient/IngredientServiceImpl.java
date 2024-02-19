package com.ramibahrouni.ingredientsservice.service.ingredient;

import com.ramibahrouni.ingredientsservice.exception.IngredientException;
import com.ramibahrouni.ingredientsservice.model.Ingredient;
import com.ramibahrouni.ingredientsservice.repository.IngredientsRepository;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class IngredientServiceImpl implements IngredientService {

  private final IngredientsRepository ingredientsRepository;

  @Override
  public Ingredient getIngredientById(long ingredientId) {

    log.info("IngredientServiceImpl | getIngredientById is called");
    log.info("IngredientServiceImpl | getIngredientById | Get the ingredient for ingredientId: {}",
        ingredientId);

    Ingredient ingredient = ingredientsRepository.findById(ingredientId)
        .orElseThrow(
            () -> new IngredientException("Ingredient with given Id not found",
                "INGREDIENT_NOT_FOUND"));

    log.info("IngredientResponse | getIngredientById | ingredientResponse : {}"
        , ingredient);

    return ingredient;
  }

  /**
   * When the order is valid and accepted, there will be reducing from the available quantity
   *
   * @param ingredientId the ingredient to reduce its quantity
   * @param quantity     the quantity chosen by the customer which will be reduced from the
   *                     ingredients
   */
  @Override
  public void reduceQuantity(long ingredientId, int quantity) {
    log.info("Reduce Quantity {} for Id: {}", quantity, ingredientId);

    Ingredient ingredient
        = ingredientsRepository.findById(ingredientId)
        .orElseThrow(() -> new IngredientException(
            "ingredient with given Id not found",
            "INGREDIENT_NOT_FOUND"
        ));

    if (ingredient.getAvailableQuantity() < quantity) {
      throw new IngredientException(
          "Ingredient does not have sufficient Quantity",
          "INSUFFICIENT_QUANTITY"
      );
    }

    ingredient.setAvailableQuantity(ingredient.getAvailableQuantity() - quantity);
    ingredientsRepository.save(ingredient);
    log.info("Ingredient Quantity updated Successfully");
  }

  /**
   * To get available ingredients only
   *
   * @return set of available ingredients
   */
  @Override
  public Set<Ingredient> getAvailableIngredients() {
    log.info("IngredientServiceImpl | getAvailableIngredients is called");

    if (ingredientsRepository.getAllByAvailableQuantityGreaterThan(0).isEmpty()) {
      log.info("All Ingredients are unavailable");
      throw new IngredientException(
          "There is no available ingredient",
          "INGREDIENTS_UNAVAILABLE");
    }
    log.info("Getting available ingredients");
    return ingredientsRepository.getAllByAvailableQuantityGreaterThan(0);
  }
}
