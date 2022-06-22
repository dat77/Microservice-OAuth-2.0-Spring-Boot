package com.onix.msoauth.services;

import com.onix.msoauth.entities.Role;
import com.onix.msoauth.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    private RoleRepository roleRepository;

    @Autowired
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Role create(String roleName, String description){
        return roleRepository.save(new Role(roleName, description));
    }

    public Long count(){
        return roleRepository.count();
    }

}
