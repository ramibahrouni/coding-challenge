package com.ramibahrouni.userservice.service;

import com.ramibahrouni.userservice.model.User;

public interface UserService {
  void addUser(User user);
  User findUser(String username);
}
