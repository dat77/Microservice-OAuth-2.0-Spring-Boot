package com.onix.msoauth.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class ProjectTeam {
    @Id
    @Column(length = 64)
    private String code;

    @Column(length = 256)
    private String projectName;

    @Column(length = 1024)
    private String description;

    @Column
    private ProjectStatus status;

    protected ProjectTeam() {
    }

    public ProjectTeam(String code, String projectName, String description, ProjectStatus status) {
        this.code = code;
        this.projectName = projectName;
        this.description = description;
        this.status = status;
    }

    public String getCode() {
        return code;
    }

    public String getProjectName() {
        return projectName;
    }

    public String getDescription() {
        return description;
    }

    public ProjectStatus getStatus() {
        return status;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStatus(ProjectStatus status) {
        this.status = status;
    }
}
