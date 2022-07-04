package com.onix.msoauth.controllers;

import com.onix.msoauth.entities.LoginDto;
import com.onix.msoauth.entities.SecurityUser;
import com.onix.msoauth.services.SecurityUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpServerErrorException;

import java.util.List;

@RestController
//@RequestMapping(path = "/users")
public class SecurityUserController {
    @Autowired
    private SecurityUserService securityUserService;

    @PostMapping("/users/signin")
    public String signin(@RequestBody LoginDto loginDto){
        return securityUserService.signin(loginDto.getUsername(), loginDto.getPassword())
                .orElseThrow(() -> new HttpServerErrorException(HttpStatus.FORBIDDEN, "Login Failed"));
    }

    @PostMapping("/users/signup")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    public SecurityUser signup(@RequestBody LoginDto loginDto){
        return securityUserService.signup(loginDto.getUsername(), loginDto.getPassword(), loginDto.getFirstName(),
                                  loginDto.getLastName()).orElseThrow(() -> new HttpServerErrorException(HttpStatus.BAD_REQUEST,"User already exists"));
    }

    @GetMapping("/users")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<SecurityUser> getAllUsers() {
        return securityUserService.getAll();
    }


}
