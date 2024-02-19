package com.ramibahrouni.orderservice.repository;


import com.ramibahrouni.orderservice.model.Order;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order, String> {
  Optional<Order> findOrderByIngredientIdAndUsername(Long ingredient, String username);
}
