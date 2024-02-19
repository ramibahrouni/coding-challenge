package com.ramibahrouni.userservice.controller;

import com.ramibahrouni.userservice.model.User;
import com.ramibahrouni.userservice.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/v1/users")
@RestController
@CrossOrigin
public class UserController {
  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @ResponseStatus(HttpStatus.CREATED)
  @PostMapping(consumes = "application/json")
  public void addUser(@RequestBody User user) {
    this.userService.addUser(user);
  }

  @GetMapping(produces = "application/json", value = "/{username}")
  public User getUser(@PathVariable String username){
    return userService.findUser(username);
  }
}
