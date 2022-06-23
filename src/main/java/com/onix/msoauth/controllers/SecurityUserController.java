package com.onix.msoauth.controllers;

import com.onix.msoauth.entities.LoginDto;
import com.onix.msoauth.services.SecurityUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@RequestMapping(path = "/users")
public class SecurityUserController {
    @Autowired
    private SecurityUserService securityUserService;

    @PostMapping("/users/signin")
    public Authentication signin(@RequestBody LoginDto loginDto){
        return securityUserService.signin(loginDto.getUsername(), loginDto.getPassword()) ;
    }
}
