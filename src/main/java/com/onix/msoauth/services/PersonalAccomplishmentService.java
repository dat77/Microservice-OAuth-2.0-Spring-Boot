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

import java.util.*;

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

    public PersonalAccomplishment update(Integer personId, String projectCode,
                                         Integer timeCosts, String description) throws NoSuchElementException{
        PersonalAccomplishment personalAccomplishment = getPersonalInfoByProject(personId,projectCode);
        personalAccomplishment.setTimeCosts(timeCosts);
        personalAccomplishment.setDescription(description);
        return personalAccomplishmentRepository.save(personalAccomplishment);
    }

    public PersonalAccomplishment patch(Integer personId, String projectCode,
                                         Integer timeCosts, String description) throws NoSuchElementException{
        PersonalAccomplishment personalAccomplishment = getPersonalInfoByProject(personId,projectCode);
        if (timeCosts != null && timeCosts != 0) personalAccomplishment.setTimeCosts(timeCosts);
        if (description != null) personalAccomplishment.setDescription(description);
        return personalAccomplishmentRepository.save(personalAccomplishment);
    }

    public void delete(Integer personId, String projectCode)
            throws NoSuchElementException{
        PersonalAccomplishment personalAccomplishment = getPersonalInfoByProject(personId,projectCode);
        personalAccomplishmentRepository.delete(personalAccomplishment);
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

    public PersonalAccomplishment getPersonalInfoByProject(Integer personId, String projectCode)
            throws NoSuchElementException{
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

    public Map<String, Long> getProductiveCosts(Integer id) throws NoSuchElementException{
        Map<String, Long> res = new HashMap<String, Long>();
        res.put( "productive costs", personalAccomplishmentRepository.findByPkPersonId(id).stream()
                .filter(ac -> !ac.getPersonalProjectPk().getProjectTeam().getCode().equals("DEV000101KR"))
                .mapToLong(PersonalAccomplishment::getTimeCosts).sum());
        return res;
    }

    public Long count(){
        return personalAccomplishmentRepository.count();
    }

}
