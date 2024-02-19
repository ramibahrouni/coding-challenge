package com.ramibahrouni.orderservice.controller;

import com.ramibahrouni.orderservice.model.Order;
import com.ramibahrouni.orderservice.service.OrderService;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {

  private final OrderService orderService;

  public OrderController(OrderService orderService) {
    this.orderService = orderService;
  }

  private String getPrincipal(String authorizationToken){
    String token = authorizationToken.replaceAll("Basic ", "");
    String loginCredentials = new String(Base64.getDecoder().decode(token), StandardCharsets.UTF_8);
    return loginCredentials.split(":")[0];
  }

  @ResponseStatus(HttpStatus.CREATED)
  @PostMapping(consumes = "application/json")
  public void addNewIngredientToOrder(@RequestBody Order order, @RequestHeader String authorization){
    order.setUsername(getPrincipal(authorization));
    this.orderService.addIngredientToOrder(order);
  }

  @ResponseStatus(HttpStatus.NO_CONTENT)
  @DeleteMapping("/{ingredientId:\\d+}")
  public void removeIngredientFromOrder(@PathVariable Long ingredientId, @RequestHeader String authorization){
    this.orderService.removeIngredientFromOrder(ingredientId, getPrincipal(authorization));
  }
}
