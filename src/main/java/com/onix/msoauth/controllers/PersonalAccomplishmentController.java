package com.onix.msoauth.controllers;

import com.onix.msoauth.entities.AccomplishmentDto;
import com.onix.msoauth.entities.PersonalAccomplishment;
import com.onix.msoauth.services.PersonalAccomplishmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
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
    @PreAuthorize("hasRole('ROLE_USER')")
    public void createByCode(@PathVariable(value = "code") String code,
                             @RequestBody AccomplishmentDto dto){

        personalAccomplishmentService.create(dto.getName(), code,
                                             dto.getTimeCosts(), dto.getDescription());
    }

    @PostMapping("/employees/{id}/accomplishments")
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    //@PostAuthorize("#username == authentication.principal.username")
    public void createByPerson(@PathVariable(value = "id") Integer id,
                               @RequestBody AccomplishmentDto dto){

        personalAccomplishmentService.create(id, dto.getCode(),
                                             dto.getTimeCosts(), dto.getDescription());
    }

    @PutMapping("/employees/{id}/accomplishments")
    @PreAuthorize("hasRole('ROLE_USER')")
    public AccomplishmentDto putByPerson(@PathVariable(value = "id") Integer id,
                                         @RequestBody AccomplishmentDto dto){
       return new AccomplishmentDto(personalAccomplishmentService.update(id, dto.getCode(),
                                                                  dto.getTimeCosts(), dto.getDescription()));
    }

    @PatchMapping("/employees/{id}/accomplishments")
    @PreAuthorize("hasRole('ROLE_USER')")
    public AccomplishmentDto patchByPerson(@PathVariable(value = "id") Integer id,
                                         @RequestBody AccomplishmentDto dto){
       return new AccomplishmentDto(personalAccomplishmentService.patch(id, dto.getCode(),
                                                                  dto.getTimeCosts(), dto.getDescription()));
    }

    @DeleteMapping(value = {"/employees/{id}/accomplishments/{code}",
                            "/teams/{code}/accomplishments/{id}"})
    @PreAuthorize("hasRole('ROLE_USER')")
    public void deleteByPersonIdAndProjectCode(@PathVariable(value = "id") Integer id,
                                                            @PathVariable(value = "code") String code){
        personalAccomplishmentService.delete(id,code);
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

    @GetMapping(value = {"/employees/{id}/accomplishments/{code}",
                         "/teams/{code}/accomplishments/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public AccomplishmentDto getByPersonIdAndProjectCode(@PathVariable(value = "id") Integer id,
                                                         @PathVariable(value = "code") String code){
        return new AccomplishmentDto(personalAccomplishmentService.getPersonalInfoByProject(id,code));
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

    @GetMapping("/employees/{id}/accomplishments/productive")
    @ResponseStatus(HttpStatus.OK)
    public Map<String, Long> getProductiveCostByPersonId(@PathVariable(value = "id") Integer id){
        return (personalAccomplishmentService.getProductiveCosts(id));
    }


    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoSuchElementException.class)
    public String error404(NoSuchElementException ex){
        return ex.getMessage();
    }


}
