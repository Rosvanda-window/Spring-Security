package com.kshrd.springbasicauth.Config;

import com.kshrd.springbasicauth.service.AppUserService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@RequiredArgsConstructor
public class InMemoryConfig {

    private final PasswordEncoder encoder;
    private final AppUserService appUserService;


    @Bean //use this for check if user have in db but compare as encode pwd
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider(appUserService);
        provider.setPasswordEncoder(encoder);
        return provider;
    }//this can apply in other project but need to change service and need to have encoder


    @Bean //config for which role can access which endpoint
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity){
        httpSecurity.csrf(csrf -> csrf.disable());
        httpSecurity.authorizeHttpRequests(
                auth -> auth
                        .requestMatchers("/admin").hasRole("ADMIN")
                        .requestMatchers("/user").hasAnyRole("USER", "ADMIN")
                        .requestMatchers("/test",
                                "/v3/api-docs/**", //work with swagger
                                "/swagger-ui/**",
                                "/swagger-ui.html").permitAll()
                        .anyRequest().authenticated()
        );
//        httpSecurity.formLogin(Customizer.withDefaults());
//        httpSecurity.httpBasic(Customizer.withDefaults());
        httpSecurity.exceptionHandling(ex -> ex.authenticationEntryPoint(
                (request, response, authException) -> {
                    response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
                }
        ));
        return httpSecurity.build();
    }

    //@Bean //config for make users how many user can have in our application
//    public InMemoryUserDetailsManager inMemoryUserDetailsManager(){
//
//        UserDetails admin = User.withUsername("admin")
//                .password(encoder.encode("v123"))
//                .roles("ADMIN")
//                .build();
//
//        UserDetails user = User.withUsername("user")
//                .password(encoder.encode("v123"))
//                .roles("USER")
//                .build();
//
//        System.out.println(encoder.encode("v123"));
//
//        return new InMemoryUserDetailsManager(admin, user);
//    }
}
