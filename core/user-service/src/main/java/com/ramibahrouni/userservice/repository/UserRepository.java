package com.ramibahrouni.userservice.repository;

import com.ramibahrouni.userservice.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, String> {

}
