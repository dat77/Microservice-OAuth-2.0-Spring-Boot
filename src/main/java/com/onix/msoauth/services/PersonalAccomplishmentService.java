package com.onix.msoauth.services;

import com.onix.msoauth.entities.Person;
import com.onix.msoauth.entities.PersonalAccomplishment;
import com.onix.msoauth.entities.PersonalProjectPk;
import com.onix.msoauth.entities.ProjectTeam;
import com.onix.msoauth.repository.PersonRepository;
import com.onix.msoauth.repository.PersonalAccomplishmentRepository;
import com.onix.msoauth.repository.ProjectTeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonalAccomplishmentService {
    private PersonRepository personRepository;
    private ProjectTeamRepository projectTeamRepository;
    private PersonalAccomplishmentRepository personalAccomplishmentRepository;

    @Autowired
    public PersonalAccomplishmentService(PersonRepository personRepository, ProjectTeamRepository projectTeamRepository,
                                         PersonalAccomplishmentRepository personalAccomplishmentRepository) {
        this.personRepository = personRepository;
        this.projectTeamRepository = projectTeamRepository;
        this.personalAccomplishmentRepository = personalAccomplishmentRepository;
    }


    public PersonalAccomplishment create(String personName, String projectCode,
                                         Integer timeCosts, String description){
        Person person = personRepository.findByName(personName)
                .orElseThrow(() -> new RuntimeException("Person does not exist: " + personName));

        ProjectTeam projectTeam = projectTeamRepository.findById(projectCode)
                .orElseThrow(() -> new RuntimeException("Project Team does not exist: " + projectCode));

        return personalAccomplishmentRepository.save(new PersonalAccomplishment(
                new PersonalProjectPk(projectTeam, person),
                timeCosts, description));
    }

    List<PersonalAccomplishment> getProjectInfo(String code){
        ProjectTeam projectTeam = projectTeamRepository.findById(code)
                .orElseThrow(() -> new RuntimeException("Project Team does not exist: " + code));

        return personalAccomplishmentRepository.findByPkProjectTeamCode(code);
    }

    PersonalAccomplishment getPersonalInfo(String personName, String projectCode){
        Person person = personRepository.findByName(personName)
                .orElseThrow(() -> new RuntimeException("Person does not exist: " + personName));

        ProjectTeam projectTeam = projectTeamRepository.findById(projectCode)
                .orElseThrow(() -> new RuntimeException("Project Team does not exist: " + projectCode));

        return personalAccomplishmentRepository.findByPkProjectTeamCodeAndPersonId(projectCode, person.getId())
                .orElseThrow(() -> new RuntimeException("There are no accomplishments by: " + personName +" in "+ projectCode));

    }

    Long getProjectTimeCost(String code){
        ProjectTeam projectTeam = projectTeamRepository.findById(code)
                .orElseThrow(() -> new RuntimeException("Project Team does not exist: " + code));

        return personalAccomplishmentRepository.getTimeCostsByProject(code);
    }
    
}
