package com.ramibahrouni.discoveryserver;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Map;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;

/**
 * DiscoveryServerApplicationTests.java
 *
 * @author Rami Bahrouni
 */
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = DiscoveryServerApplication.class)
class DiscoveryServerApplicationTests {

  private static final Logger logger = LoggerFactory.getLogger(DiscoveryServerApplicationTests.class);
  @Value("${local.server.port}")
  private int port = 8761;


  /**
   * This test is made to check if Eureka is UP.
   */
  @Test
  public void catalogLoads() {
    @SuppressWarnings("rawtypes")
    ResponseEntity<Map> entity = new TestRestTemplate().getForEntity(
        "http://localhost:" + port + "/eureka/apps", Map.class);
    logger.info("Returned response {} !", entity.getBody());
    assertEquals(HttpStatus.OK, entity.getStatusCode());
  }

}
