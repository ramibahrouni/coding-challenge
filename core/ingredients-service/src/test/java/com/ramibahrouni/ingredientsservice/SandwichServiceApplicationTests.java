package com.ramibahrouni.ingredientsservice;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SandwichServiceApplicationTests {

  @Test
  void contextLoads() {
  }

  @Test
  public void migrationSuccessTest() {
    // migration starts automatically,
    // since Spring Boot runs the Flyway scripts on startup
  }

}
