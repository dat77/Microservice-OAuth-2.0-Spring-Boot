package com.onix.msoauth.repository;

import com.onix.msoauth.entities.ProjectStatus;
import com.onix.msoauth.entities.ProjectTeam;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;

public interface ProjectTeamRepository extends CrudRepository<ProjectTeam, String> {
    Collection<ProjectTeam> findByStatus(ProjectStatus status);
    @Query("select t from ProjectTeam t where t.code like %?1% ")
    List<ProjectTeam> findByCodeLike(String code);
    List<ProjectTeam> findByCodeContaining(String code);
}
