package com.onix.msoauth.controllers;

import com.onix.msoauth.entities.AccomplishmentDto;
import com.onix.msoauth.services.PersonalAccomplishmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

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

    @PostMapping("/teams/{code}/accomplishments")
    @ResponseStatus(HttpStatus.CREATED)
    public void createByCode(@PathVariable(value = "code") String code,
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

    @GetMapping("/teams/{code}/accomplishments")
    @ResponseStatus(HttpStatus.OK)
    public List<AccomplishmentDto> getByProjectCode(@PathVariable(value = "code") String code){
        return personalAccomplishmentService.getProjectInfo(code).stream()
                .map(AccomplishmentDto::new).collect(Collectors.toList());
    }

    @GetMapping("/employees/{id}/accomplishments")
    @ResponseStatus(HttpStatus.OK)
    public List<AccomplishmentDto> getByPersonId(@PathVariable(value = "id") Integer id){
        return personalAccomplishmentService.getPersonalInfo(id).stream()
                .map(AccomplishmentDto::new).collect(Collectors.toList());
    }

    @GetMapping("/teams/{code}/accomplishments/sum")
    @ResponseStatus(HttpStatus.OK)
    public Long getCostByCode(@PathVariable(value = "code") String code){
        return personalAccomplishmentService.getProjectTimeCost(code);
    }

    @GetMapping("/employees/{id}/accomplishments/sum")
    @ResponseStatus(HttpStatus.OK)
    public Long getCostByPersonId(@PathVariable(value = "id") Integer id){
//        Long l1 = personalAccomplishmentService.getPersonalInfo(id).stream()
//                .mapToLong(pa -> pa.getTimeCosts()).sum();
        return (personalAccomplishmentService.getProjectTimeCost(id));
    }


    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoSuchElementException.class)
    public String error404(NoSuchElementException ex){
        return ex.getMessage();
    }


}
