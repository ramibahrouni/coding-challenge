package com.ramibahrouni.orderservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class IngredientItem {

  private Long ingredientId;
  private String name;
  private int qty;
  private double price;
  private double ingredientPrice;

  public double getIngredientPrice() {
    return price * qty;
  }
}
