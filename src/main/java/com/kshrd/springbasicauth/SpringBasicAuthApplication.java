package com.kshrd.springbasicauth;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SecurityScheme(
        name = "basicAuth",
        type = SecuritySchemeType.HTTP,
        scheme = "basic"
)
@SpringBootApplication
public class SpringBasicAuthApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringBasicAuthApplication.class, args);
    }

}
