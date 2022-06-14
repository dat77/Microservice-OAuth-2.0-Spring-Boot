package com.onix.msoauth.services;

import com.onix.msoauth.entities.Level;
import com.onix.msoauth.entities.Person;
import com.onix.msoauth.entities.ProjectRole;
import com.onix.msoauth.entities.ProjectTeam;
import com.onix.msoauth.repository.PersonRepository;
import com.onix.msoauth.repository.ProjectTeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class PersonService {
    private PersonRepository personRepository;
    private ProjectTeamRepository projectTeamRepository;

    @Autowired
    public PersonService(PersonRepository personRepository, ProjectTeamRepository projectTeamRepository) {
        this.personRepository = personRepository;
        this.projectTeamRepository = projectTeamRepository;
    }

    public Person create(String name, String skills, LocalDate startDate, Level level,
                         ProjectRole projectRole, String projectTeamId){
        ProjectTeam projectTeam = projectTeamRepository.findById(projectTeamId)
                .orElseThrow(() -> new RuntimeException("Project Team does not exist: " + projectTeamId));

        return personRepository.save(new Person(name, skills, startDate, level, projectRole, projectTeam));
    }

    public long count(){
        return personRepository.count();
    }

}
