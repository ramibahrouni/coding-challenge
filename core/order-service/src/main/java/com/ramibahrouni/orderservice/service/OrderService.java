package com.ramibahrouni.orderservice.service;

import com.ramibahrouni.orderservice.model.Order;

public interface OrderService {
  public void addIngredientToOrder(Order order);
  public void removeIngredientFromOrder(Long ingredientId, String username);
}
