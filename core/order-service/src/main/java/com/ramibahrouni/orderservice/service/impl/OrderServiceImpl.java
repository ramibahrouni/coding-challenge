package com.ramibahrouni.orderservice.service.impl;

import com.ramibahrouni.orderservice.kafka.KafkaProducer;
import com.ramibahrouni.orderservice.model.IngredientItem;
import com.ramibahrouni.orderservice.model.Order;
import com.ramibahrouni.orderservice.repository.OrderRepository;
import com.ramibahrouni.orderservice.service.OrderService;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

@Service
public class OrderServiceImpl implements OrderService {

  private final OrderRepository orderRepository;
  private RestTemplate restTemplate;

  private final KafkaProducer kafkaProducer;

  @Autowired
  public OrderServiceImpl(OrderRepository orderRepository, RestTemplate restTemplate,
      KafkaProducer kafkaProducer) {
    this.orderRepository = orderRepository;
    this.restTemplate = restTemplate;
    this.kafkaProducer = kafkaProducer;
  }

  @Override
  public void addIngredientToOrder(Order order) {
    IngredientItem ingredient = restTemplate.getForObject("http://INGREDIENTS-SERVICE/api/v1/ingredients/{ingredientId}",
        IngredientItem.class, order.getIngredientId());
    Optional<Order> optionalOrder = orderRepository.findOrderByIngredientIdAndUsername(
        order.getIngredientId(), order.getUsername());

    if(optionalOrder.isPresent()) {
      Order orderEntity = optionalOrder.get();
      kafkaProducer.sendMessage(String.valueOf(orderEntity));
    }
  }

  @Override
  public void removeIngredientFromOrder(Long ingredientId, String username) {
    Order order = orderRepository.findOrderByIngredientIdAndUsername(ingredientId, username)
        .orElseThrow(
            () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Order does not exist")
        );
    orderRepository.delete(order);
  }
}
