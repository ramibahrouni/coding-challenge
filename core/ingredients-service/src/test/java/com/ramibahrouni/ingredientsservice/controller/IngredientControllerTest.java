package com.ramibahrouni.ingredientsservice.controller;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasSize;

import com.ramibahrouni.ingredientsservice.model.Ingredient;
import com.ramibahrouni.ingredientsservice.repository.IngredientsRepository;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import java.util.List;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.TestPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = "classpath:application-test.properties")
public class IngredientControllerTest {

  @LocalServerPort
  private Integer port;

  static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>(
      "postgres:16-alpine"
  );

  @BeforeAll
  static void beforeAll() {
    postgres.start();
  }

  @AfterAll
  static void afterAll() {
    postgres.stop();
  }

  @Test
  public void migrationSuccessTest() {
    // migration starts automatically,
    // since Spring Boot runs the Flyway scripts on startup
  }

  @DynamicPropertySource
  static void configureProperties(DynamicPropertyRegistry registry) {
    registry.add("spring.datasource.url", postgres::getJdbcUrl);
    registry.add("spring.datasource.username", postgres::getUsername);
    registry.add("spring.datasource.password", postgres::getPassword);
  }

  @Autowired
  IngredientsRepository ingredientsRepository;

  @BeforeEach
  void setUp() {
    RestAssured.baseURI = "http://localhost:" + port;
    ingredientsRepository.deleteAll();
  }

  @Test
  void shouldGetAllIngredients() {
    List<Ingredient> ingredientList = List.of(
        new Ingredient("bread", 1.02, 4, "Vegetable"),
        new Ingredient("cheese", 2.10, 5, "Meat")
    );
    ingredientsRepository.saveAll(ingredientList);

    given()
        .contentType(ContentType.JSON)
        .when()
        .get("/api/v1/ingredients")
        .then()
        .statusCode(200)
        .body(".", hasSize(2));
  }

  @Test
  void shouldGetNoIngredients() {

    given()
        .contentType(ContentType.ANY)
        .when()
        .get("/api/v1/ingredients")
        .then()
        .statusCode(404);
  }


}
