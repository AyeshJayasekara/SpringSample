package com.saegis.springsample.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@Slf4j
public class SampleController {

    private final Environment environment;

    @Autowired
    public SampleController(Environment environment) {
        this.environment = environment;
    }

    @GetMapping("/test")
    public ResponseEntity<String> test(){
        return ResponseEntity.ok("Hi");
    }

    @GetMapping("/user")
    public Principal user(Principal user) {

        log.info("HANDLING REQUEST RUNNING AT PORT : {} FOR USER {}", environment.getProperty("server.port"),
                user.getName());

        return user;
    }
}
