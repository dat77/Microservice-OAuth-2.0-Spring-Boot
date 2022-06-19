package com.onix.msoauth.controllers;

import com.onix.msoauth.entities.AccomplishmentDto;
import com.onix.msoauth.entities.Person;
import com.onix.msoauth.entities.ProjectTeam;
import com.onix.msoauth.repository.PersonRepository;
import com.onix.msoauth.repository.ProjectTeamRepository;
import com.onix.msoauth.services.PersonalAccomplishmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
@RequestMapping(path ={"/teams/{projectCode}/accomplishments"} )
public class PersonalAccomplishmentController {
    private PersonalAccomplishmentService personalAccomplishmentService;
    private ProjectTeamRepository projectTeamRepository;
    private PersonRepository personRepository;

    @Autowired
    public PersonalAccomplishmentController(PersonalAccomplishmentService personalAccomplishmentService,
                                            ProjectTeamRepository projectTeamRepository,
                                            PersonRepository personRepository) {
        this.personalAccomplishmentService = personalAccomplishmentService;
        this.projectTeamRepository = projectTeamRepository;
        this.personRepository = personRepository;
    }

    public PersonalAccomplishmentController() {
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createByCode(@PathVariable(value = "projectCode") String code,
                             @RequestBody AccomplishmentDto dto){
        ProjectTeam projectTeam = verifyProjectTeam(code);
        Person person = verifyPerson(dto.getName());

        personalAccomplishmentService.create(person.getName(), projectTeam.getCode(),
                                             dto.getTimeCosts(), dto.getDescription());
    }

    private ProjectTeam verifyProjectTeam(String code) throws NoSuchElementException {
        return projectTeamRepository.findById(code)
                .orElseThrow(()-> new NoSuchElementException("Project does not exist: " + code));
    }

    private Person verifyPerson(String name) throws NoSuchElementException {
        return personRepository.findByName(name)
                .orElseThrow(() -> new NoSuchElementException("No person with such name: " + name));
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoSuchElementException.class)
    public String error404(NoSuchElementException ex){
        return ex.getMessage();
    }


}
