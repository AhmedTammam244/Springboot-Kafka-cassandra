package com.springboot_kafka.resource;


import com.springboot_kafka.dao.UserDao;
import com.springboot_kafka.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class KafkaProducer {

    @Autowired
    private KafkaTemplate<String, User> kafkaTemplate;

    private static final String TOPIC = "Kafka_Example";
    @Autowired
    private UserDao repository;

    @GetMapping("/user")
    public List<User> getAll() {

        return (List<User>) this.repository.findAll();
    }
    @GetMapping("/user/{name}")
    public User getUser(@PathVariable("name") final String name) {

        return  this.repository.findOne(name);
    }
    @GetMapping("/{name}")
    public String post(@PathVariable("name") final String name) {

        kafkaTemplate.send(TOPIC, new User(name, "Technology"));

        return "Published successfully";
    }
    @DeleteMapping("/user/{name}")
    public boolean delete(@PathVariable String name) {
        this.repository.delete(name);
        return true;
    }
    @GetMapping("/user/{name}/{dept}")
    public User create(@PathVariable("name") final String name ,@PathVariable("dept") final String dept) {
        kafkaTemplate.send(TOPIC, new User(name, dept));
        return this.repository.save(new User(name, dept));

    }



}
