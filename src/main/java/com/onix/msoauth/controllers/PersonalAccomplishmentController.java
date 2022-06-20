package com.onix.msoauth.controllers;

import com.onix.msoauth.entities.AccomplishmentDto;
import com.onix.msoauth.services.PersonalAccomplishmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
//@RequestMapping(path ={"/teams/{projectCode}/accomplishments"} )
public class PersonalAccomplishmentController {
    private PersonalAccomplishmentService personalAccomplishmentService;

    @Autowired
    public PersonalAccomplishmentController(PersonalAccomplishmentService personalAccomplishmentService) {
        this.personalAccomplishmentService = personalAccomplishmentService;
    }

    public PersonalAccomplishmentController() {
    }

    @PostMapping("/teams/{projectCode}/accomplishments")
    @ResponseStatus(HttpStatus.CREATED)
    public void createByCode(@PathVariable(value = "projectCode") String code,
                             @RequestBody AccomplishmentDto dto){

        personalAccomplishmentService.create(dto.getName(), code,
                                             dto.getTimeCosts(), dto.getDescription());
    }

    @PostMapping("/employees/{id}/accomplishments")
    @ResponseStatus(HttpStatus.CREATED)
    public void createByPerson(@PathVariable(value = "id") Integer id,
                             @RequestBody AccomplishmentDto dto){

        personalAccomplishmentService.create(id, dto.getCode(),
                                             dto.getTimeCosts(), dto.getDescription());
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoSuchElementException.class)
    public String error404(NoSuchElementException ex){
        return ex.getMessage();
    }


}
