package com.ramibahrouni.ingredientsservice.repository;

import com.ramibahrouni.ingredientsservice.model.Ingredient;
import java.util.Set;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredientsRepository extends JpaRepository<Ingredient, Long> {

  Set<Ingredient> getAllByAvailableQuantityGreaterThan(int availableQuantity);

}
