package com.kshrd.springbasicauth.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SecurityRequirement(name = "basicAuth")
public class TestController {

    @GetMapping
    public String test(){
        return "Hello! you logged in.....";
    }

    @GetMapping("/test")
    public String permitAll(){
        return "Anyone can access me!";
    }

    @GetMapping("/admin")
    public String admin(){
        return "Hello admin";
    }
    @GetMapping("/user")
    public String user(){
        return "Hello user";
    }


}
