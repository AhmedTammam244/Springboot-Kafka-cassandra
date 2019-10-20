package com.springboot_kafka.dao;
import org.springframework.data.repository.CrudRepository;

import com.springboot_kafka.model.User;

public interface UserDao extends CrudRepository<User, String> {




}
