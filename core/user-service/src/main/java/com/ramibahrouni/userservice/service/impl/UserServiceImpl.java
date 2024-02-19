package com.ramibahrouni.userservice.service.impl;

import com.ramibahrouni.userservice.model.User;
import com.ramibahrouni.userservice.repository.UserRepository;
import com.ramibahrouni.userservice.service.UserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;

  public UserServiceImpl(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public void addUser(User user) {
    this.userRepository.save(new User(user.getUsername(), DigestUtils.sha256Hex(user.getPassword()), user.getRole()));
  }

  @Override
  public User findUser(String username) {
    return this.userRepository.findById(username)
        .map(u -> new User(u.getUsername(), u.getPassword(), u.getRole()))
        .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Could not find submitted username"));
  }



}
