package com.onix.msoauth.repository;

import com.onix.msoauth.entities.ProjectStatus;
import com.onix.msoauth.entities.ProjectTeam;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.List;

public interface ProjectTeamRepository extends CrudRepository<ProjectTeam, String> {
    Collection<ProjectTeam> findByStatus(ProjectStatus status);
    List<ProjectTeam> findByCodeLike(String code);
}
