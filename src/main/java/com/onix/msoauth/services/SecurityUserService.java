package com.onix.msoauth.services;

import com.onix.msoauth.entities.Role;
import com.onix.msoauth.entities.SecurityUser;
import com.onix.msoauth.repository.RoleRepository;
import com.onix.msoauth.repository.SecurityUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SecurityUserService {

    private AuthenticationManager authenticationManager;
    private SecurityUserRepository securityUserRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public SecurityUserService(SecurityUserRepository securityUserRepository,
                               AuthenticationManager authenticationManager,
                               RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.securityUserRepository = securityUserRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public SecurityUser create(String username, String password, String firstName, String lastName,
                               List<String> roles){
        return securityUserRepository.save(new SecurityUser(username, password, firstName, lastName,
                roles.stream().map(roleRepository::findByRoleName).map(Optional::get).collect(Collectors.toList())));
    }



    public Long count(){
        return securityUserRepository.count();
    }

    public Authentication signin(String username, String password) {
        return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
    }

    public Optional<SecurityUser> signup(String username, String password, String firstName, String lastName){
        if (!securityUserRepository.findByUsername(username).isPresent()){
            return Optional.of(securityUserRepository.save(new SecurityUser(
                    username,passwordEncoder.encode(password),firstName,lastName,
                    Arrays.asList(roleRepository.findByRoleName("ROLE_USER").get())
            )));
        }
        return Optional.empty();
    }


}
