package com.ramibahrouni.apigw.service;

import com.ramibahrouni.apigw.model.UserPayload;
import java.util.List;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import org.springframework.http.HttpStatusCode;

@Service
public class AuthService implements ReactiveUserDetailsService {
  private final WebClient.Builder webClientBuilder;

  public AuthService(WebClient.Builder webClientBuilder) {
    this.webClientBuilder = webClientBuilder;
  }

  @Override
  public Mono<UserDetails> findByUsername(String username) {
    return Mono.create(userDetailsMonoSink -> {
      Mono<UserPayload> userPayloadMono = webClientBuilder.build().get().uri("http://localhost:8082/api/v1/users/{username}", username).retrieve().onStatus(HttpStatusCode::isError, clientResponse -> Mono.empty()).bodyToMono(
          UserPayload.class);
      userPayloadMono.subscribe(userPayload -> {
        userDetailsMonoSink.success(
            userPayload.username() == null ? null : new User(userPayload.username(), userPayload.password(),
            List.of(new SimpleGrantedAuthority("ROLE_" + userPayload.role()))));
      });

    });

  }
}
