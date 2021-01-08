package ru.itmo.lab4.security;



import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.itmo.lab4.model.User;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class MyUserPrincipal implements UserDetails {

    private final User user;

    public MyUserPrincipal(User user) {
        this.user = user;
    }

    // cannot return null
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        grantedAuthorities.add(new SimpleGrantedAuthority("user"));
        return grantedAuthorities;
    }

    @Override
    public String getPassword() {
        return user.getHashPass();
    }

    @Override
    public String getUsername() {
        return user.getLogin();
    }

    // false = user's account is expired
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    // false = user is locked
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }


    // false = expired credentials
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    // false = used is disabled and cannot be authenticated
    @Override
    public boolean isEnabled() {
        return true;
    }
}
