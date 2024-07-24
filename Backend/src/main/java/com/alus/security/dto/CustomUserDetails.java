package com.alus.security.dto;

import com.alus.security.entity.UserEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CustomUserDetails implements UserDetails {

    private UserEntity userEntity;

    public CustomUserDetails(UserEntity user) {
        this.userEntity = userEntity;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        Collection<GrantedAuthority> collection = new ArrayList<>();
        collection.add(new GrantedAuthority() {

            @Override
            public String getAuthority() {
                return userEntity.getRole();
            }
        });

        return List.of();
    }

    @Override
    public String getPassword() {
        return userEntity.getPassword();
    }

    @Override
    public String getUsername() {
        return userEntity.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }
}
