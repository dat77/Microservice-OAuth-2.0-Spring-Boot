package com.onix.msoauth.services;

import com.onix.msoauth.entities.SecurityUser;
import com.onix.msoauth.repository.SecurityUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import static org.springframework.security.core.userdetails.User.withUsername;

@Component
public class SecurityUserDetailsService implements UserDetailsService {

    @Autowired
    private SecurityUserRepository securityUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        SecurityUser securityUser = securityUserRepository.findByUsername(username)
                .orElseThrow(() ->
                       new UsernameNotFoundException(String.format("There is no user %s registered", username)) );



        return withUsername(securityUser.getUsername())
                .password(securityUser.getPassword())
                .authorities(securityUser.getRoles())
                .accountExpired(false)
                .accountLocked(false)
                .credentialsExpired(false)
                .disabled(false)
                .build();
    }
}
