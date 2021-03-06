package com.revature.spaceecobackend.model;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Data
public class CustomUserDetails implements UserDetails {

    private Integer id;
    private String userName;
    private String password;
    private boolean active;
    private List<GrantedAuthority> authorities;
    private String secret;

    public CustomUserDetails(User user) {
        this.id = user.getId();
        this.userName = user.getUsername();
        this.password = user.getPassword();
        this.active = user.isActive();
        this.authorities = Arrays.asList(new SimpleGrantedAuthority(user.getUserRole().getRole()));
        this.secret = user.getSecret();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return active;
    }
}