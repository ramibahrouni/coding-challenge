package com.ramibahrouni.orderservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;
  private Long ingredientId;
  private String username;
  private int qty;
  private double price;

  public Order(String username, int qty, double price) {
    this.username = username;
    this.qty = qty;
    this.price = price;
  }
}
