package com.ramibahrouni.apigw.config;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.web.reactive.function.client.WebClient;

public class SecurityConfig {

  @Bean
  public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
    http.csrf().disable();
    http.authorizeExchange().pathMatchers("/eureka/**").permitAll()
        .pathMatchers(HttpMethod.POST, "/api/v1/users").hasAnyRole("ADMIN")
        .pathMatchers(HttpMethod.GET, "/api/v1/ingredients").hasAnyRole("USER")
        .pathMatchers(HttpMethod.POST, "/api/v1/order").hasAnyRole("USER")
        .pathMatchers(HttpMethod.GET, "/api/v1/analytics").hasAnyRole("ADMIN")
        .anyExchange().authenticated()
        .and()
        .httpBasic();
    return http.build();
  }

  @Bean
  @LoadBalanced
  public WebClient.Builder webClientBuilder() {
    return WebClient.builder();
  }


  @Bean
  public PasswordEncoder passwordEncoder() {
    return new PasswordEncoder() {
      @Override
      public String encode(CharSequence rawPassword) {
        return DigestUtils.sha256Hex(rawPassword.toString());
      }

      @Override
      public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return encode(rawPassword).equals(encodedPassword);
      }

    };
  }

}
