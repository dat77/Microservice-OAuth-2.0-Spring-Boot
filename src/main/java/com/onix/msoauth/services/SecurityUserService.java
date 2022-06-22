package com.onix.msoauth.services;

import com.onix.msoauth.entities.Role;
import com.onix.msoauth.entities.SecurityUser;
import com.onix.msoauth.repository.RoleRepository;
import com.onix.msoauth.repository.SecurityUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SecurityUserService {

    private RoleRepository roleRepository;

    private SecurityUserRepository securityUserRepository;

    @Autowired
    public SecurityUserService(RoleRepository roleRepository, SecurityUserRepository securityUserRepository) {
        this.roleRepository = roleRepository;
        this.securityUserRepository = securityUserRepository;
    }

    public SecurityUser create(String username, String password, String firstName, String lastName,
                                List<String> roles){
        return securityUserRepository.save(new SecurityUser(username, password, firstName, lastName,
                roles.stream().map(roleRepository::findByRoleName).map(Optional::get).collect(Collectors.toList())));
    }

    public Long count(){
        return securityUserRepository.count();
    }
}
