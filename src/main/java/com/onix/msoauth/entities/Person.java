package com.onix.msoauth.entities;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Person {
    @Id
    @GeneratedValue
    private Integer id;

    @Column
    private String name;
    @Column
    private String skills;
    @Column
    private LocalDate startDate;
    @Column
    @Enumerated
    private Level level;
    @Column
    @Enumerated
    private ProjectRole projectRole;


    @ManyToOne
    private ProjectTeam projectTeam;

    protected Person() {
    }

    public Person(String name, String skills, LocalDate startDate, Level level,
                  ProjectRole projectRole, ProjectTeam projectTeam) {
        this.name = name;
        this.skills = skills;
        this.startDate = startDate;
        this.level = level;
        this.projectRole = projectRole;
        this.projectTeam = projectTeam;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSkills() {
        return skills;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public Level getLevel() {
        return level;
    }

    public ProjectRole getProjectRole() {
        return projectRole;
    }

    public ProjectTeam getProjectTeam() {
        return projectTeam;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public void setProjectRole(ProjectRole projectRole) {
        this.projectRole = projectRole;
    }

    public void setProjectTeam(ProjectTeam projectTeam) {
        this.projectTeam = projectTeam;
    }
}
