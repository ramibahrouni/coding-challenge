package com.ramibahrouni.ingredientsservice.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

@Entity
@Table(name = "ingredients")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Ingredient {

  @Id
  @Column(name = "ingredient_id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Long ingredientId;
  @Column(name = "name")
  String name;
  @Column(name = "price")
  double price;
  @Column(name = "available_quantity")
  int availableQuantity;
  @Column(name = "type")
  String ingredientType;
  @CreatedDate
  @Column(name = "created_at")
  private LocalDateTime createdDate;



  public Ingredient(String name, double price, int availableQuantity, String ingredientType) {
    this.name = name;
    this.price = price;
    this.availableQuantity = availableQuantity;
    this.ingredientType = ingredientType;
  }
}
