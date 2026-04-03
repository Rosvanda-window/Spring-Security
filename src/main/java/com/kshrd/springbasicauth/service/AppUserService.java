package com.kshrd.springbasicauth.service;

import com.kshrd.springbasicauth.repository.AppUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AppUserService implements UserDetailsService { //we need to implement it from  UserDetailsService for working

    private final AppUserRepository appUserRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException { // we changed to email because we want to work with email
        return appUserRepository.findUserByEmail(email); //normal flow like CRUF
    }
}
