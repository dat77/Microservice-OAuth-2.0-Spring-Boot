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
import java.util.NoSuchElementException;
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
                                         Integer timeCosts, String description) throws NoSuchElementException{
        Person person = personRepository.findByName(personName)
                .orElseThrow(() -> new NoSuchElementException("Service exeption: Person does not exist: " + personName));

        ProjectTeam projectTeam = projectTeamRepository.findById(projectCode)
                .orElseThrow(() -> new NoSuchElementException("Service exeption: Project Team does not exist: " + projectCode));

        return personalAccomplishmentRepository.save(new PersonalAccomplishment(
                new PersonalProjectPk(projectTeam, person),
                timeCosts, description));
    }

    public PersonalAccomplishment create(Integer personId, String projectCode,
                                         Integer timeCosts, String description) throws NoSuchElementException{
        Person person = personRepository.findById(personId)
                .orElseThrow(() -> new NoSuchElementException("Service exeption: Person does not exist: " + personId));

        ProjectTeam projectTeam = projectTeamRepository.findById(projectCode)
                .orElseThrow(() -> new NoSuchElementException("Service exeption: Project Team does not exist: " + projectCode));

        return personalAccomplishmentRepository.save(new PersonalAccomplishment(
                new PersonalProjectPk(projectTeam, person),
                timeCosts, description));
    }

    public List<PersonalAccomplishment> getProjectInfo(String code)  throws NoSuchElementException{
        ProjectTeam projectTeam = projectTeamRepository.findById(code)
                .orElseThrow(() -> new NoSuchElementException("Project Team does not exist: " + code));

        return personalAccomplishmentRepository.findByPkProjectTeamCode(code);
    }

    public List<PersonalAccomplishment> getPersonalInfo(Integer id)  throws NoSuchElementException{
        Person person = personRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Person does not exist: " + id));

        return personalAccomplishmentRepository.findByPkPersonId(id);
    }

    public PersonalAccomplishment getPersonalInfo(Integer personId, String projectCode)  throws NoSuchElementException{
        Person person = personRepository.findById(personId)
                .orElseThrow(() -> new NoSuchElementException("Person does not exist: " + personId));

        ProjectTeam projectTeam = projectTeamRepository.findById(projectCode)
                .orElseThrow(() -> new NoSuchElementException("Project Team does not exist: " + projectCode));

        return personalAccomplishmentRepository.findByPkProjectTeamCodeAndPersonId(projectCode, person.getId())
                .orElseThrow(() -> new NoSuchElementException("There are no accomplishments by: " + personId +" in "+ projectCode));

    }

    public Long getProjectTimeCost(String code)  throws NoSuchElementException{
        ProjectTeam projectTeam = projectTeamRepository.findById(code)
                .orElseThrow(() -> new NoSuchElementException("Project Team does not exist: " + code));

        return personalAccomplishmentRepository.getTimeCostsByProject(code);
    }

    public Long getProjectTimeCost(Integer id)  throws NoSuchElementException{
        Person person = personRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Person does not exist: " + id));

        return personalAccomplishmentRepository.getTimeCostsByPerson(id);
    }

}
