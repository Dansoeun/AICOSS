package com.alus.security.service;

import com.alus.security.dto.CustomUserDetails;
import com.alus.security.entity.UserEntity;
import com.alus.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userData = userRepository.findByUsername(username);

        if(userData != null) {
            return new CustomUserDetails(userData);
        }
        return null;
    }
}
