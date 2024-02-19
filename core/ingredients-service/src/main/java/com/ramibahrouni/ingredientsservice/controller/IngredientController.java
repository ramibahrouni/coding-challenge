package com.ramibahrouni.ingredientsservice.controller;

import com.ramibahrouni.ingredientsservice.model.Ingredient;
import com.ramibahrouni.ingredientsservice.service.ingredient.IngredientServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * IngredientController.java
 *
 *
 *
 * @author Rami Bahrouni
 */
@RestController
@RequestMapping("/api/v1/ingredients")
@RequiredArgsConstructor
@Tag(name = "Controller for ingredients requests")
@CrossOrigin
@Slf4j
public class IngredientController {

  private final IngredientServiceImpl ingredientService;


  @GetMapping()
  @Operation(summary = "Get All ingredients")
  @ApiResponse(responseCode = "200", description = "Ingredients were found in the database")
  @ApiResponse(responseCode = "404", description = "No ingredients were found")
  @PostMapping(consumes = "application/json")
//  @PreAuthorize("hasAnyRole('ROLE_USER')")
  public ResponseEntity<Set<Ingredient>> getAll() {
    Set<Ingredient> ingredients =  ingredientService.getAvailableIngredients();
    if (ingredients.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity<>(ingredients, HttpStatus.OK);
  }





}
