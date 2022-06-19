package com.onix.msoauth.entities;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class PersonalAccomplishment {

    @EmbeddedId
    private PersonalProjectPk personalProjectPk;

    @Column
    private Integer timeCosts;

    @Column
    private String description;

    public PersonalAccomplishment() {

    }

    public PersonalAccomplishment(PersonalProjectPk personalProjectPk, Integer timeCosts, String description) {
        this.personalProjectPk = personalProjectPk;
        this.timeCosts = timeCosts;
        this.description = description;
    }

    public PersonalProjectPk getPersonalProjectPk() {
        return personalProjectPk;
    }

    public Integer getTimeCosts() {
        return timeCosts;
    }

    public String getDescription() {
        return description;
    }

    public void setPersonalProjectPk(PersonalProjectPk personalProjectPk) {
        this.personalProjectPk = personalProjectPk;
    }

    public void setTimeCosts(Integer timeCosts) {
        this.timeCosts = timeCosts;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "PersonalAccomplishment{" +
                "personalProjectPk=" + personalProjectPk +
                ", timeCosts=" + timeCosts +
                ", description='" + description + '\'' +
                '}';
    }
}
