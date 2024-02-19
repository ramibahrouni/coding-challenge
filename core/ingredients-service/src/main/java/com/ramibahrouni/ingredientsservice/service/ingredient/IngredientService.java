package com.ramibahrouni.ingredientsservice.service.ingredient;

import com.ramibahrouni.ingredientsservice.model.Ingredient;
import java.util.List;
import java.util.Set;

public interface IngredientService {
  Ingredient getIngredientById(long ingredientId);
  void reduceQuantity(long ingredientId, int quantity);
  Set<Ingredient> getAvailableIngredients();

}
