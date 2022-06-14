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

    protected ProjectTeam() {
    }

    public ProjectTeam(String code, String projectName, String description) {
        this.code = code;
        this.projectName = projectName;
        this.description = description;
    }


}
