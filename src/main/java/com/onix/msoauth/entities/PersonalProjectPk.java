package com.onix.msoauth.entities;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class PersonalProjectPk implements Serializable {

    @ManyToOne
    private ProjectTeam projectTeam;

    @ManyToOne
    private Person person;

    public PersonalProjectPk() {
    }

    public PersonalProjectPk(ProjectTeam projectTeam, Person person) {
        this.projectTeam = projectTeam;
        this.person = person;
    }

    @Override
    public String toString() {
        return "PersonalProjectPk{" +
                "projectTeam=" + projectTeam +
                ", person=" + person +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonalProjectPk that = (PersonalProjectPk) o;
        return Objects.equals(projectTeam, that.projectTeam) && Objects.equals(person, that.person);
    }

    @Override
    public int hashCode() {
        return Objects.hash(projectTeam, person);
    }

    public ProjectTeam getProjectTeam() {
        return projectTeam;
    }

    public Person getPerson() {
        return person;
    }

    public void setProjectTeam(ProjectTeam projectTeam) {
        this.projectTeam = projectTeam;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
