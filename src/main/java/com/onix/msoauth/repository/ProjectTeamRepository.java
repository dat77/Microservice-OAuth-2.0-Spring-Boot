package com.onix.msoauth.repository;

import com.onix.msoauth.entities.ProjectStatus;
import com.onix.msoauth.entities.ProjectTeam;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.Collection;
import java.util.List;

@RepositoryRestResource(collectionResourceRel = "teams", path = "teams")
public interface ProjectTeamRepository extends CrudRepository<ProjectTeam, String> {
    Collection<ProjectTeam> findByStatus(ProjectStatus status);
    @Query("select t from ProjectTeam t where t.code like %?1% ")
    List<ProjectTeam> findByCodeLike(String code);
    List<ProjectTeam> findByCodeContaining(String code); // the same as above


    @Override
    @RestResource(exported = true)
    <S extends ProjectTeam> S save(S entity);

    @Override
    @RestResource(exported = false)
    <S extends ProjectTeam> Iterable<S> saveAll(Iterable<S> entities);

    @Override
    @RestResource(exported = false)
    void deleteById(String s);

    @Override
    @RestResource(exported = false)
    void delete(ProjectTeam entity);

    @Override
    @RestResource(exported = false)
    void deleteAllById(Iterable<? extends String> strings);

    @Override
    @RestResource(exported = false)
    void deleteAll(Iterable<? extends ProjectTeam> entities);

    @Override
    @RestResource(exported = false)
    void deleteAll();
}
