package com.onix.msoauth.repository;

import com.onix.msoauth.entities.PersonalAccomplishment;
import com.onix.msoauth.entities.PersonalProjectPk;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
import java.util.Optional;

@RepositoryRestResource(exported = false)
public interface PersonalAccomplishmentRepository extends CrudRepository<PersonalAccomplishment, PersonalProjectPk> {

    @Query("select pa from PersonalAccomplishment pa " +
            "where pa.personalProjectPk.projectTeam.code = ?1")
    List<PersonalAccomplishment> findByPkProjectTeamCode(String code);

    @Query("select pa from PersonalAccomplishment pa " +
            "where pa.personalProjectPk.person.id = ?1")
    List<PersonalAccomplishment> findByPkPersonId(Integer id);

    @Query("select pa from PersonalAccomplishment pa " +
            "where pa.personalProjectPk.projectTeam.code = ?1 " +
            "and pa.personalProjectPk.person.id = ?2")
    Optional<PersonalAccomplishment> findByPkProjectTeamCodeAndPersonId(String code, Integer id);

    @Query("select sum(pa.timeCosts) " +
           "from PersonalAccomplishment pa " +
           "where pa.personalProjectPk.projectTeam.code = ?1")
    Long getTimeCostsByProject(String code);

    @Query("select sum(pa.timeCosts) " +
            "from PersonalAccomplishment pa " +
            "where pa.personalProjectPk.person.id = ?1")
    Long getTimeCostsByPerson(Integer id);


}
