package com.onix.msoauth.entities;

public class AccomplishmentDto {
    private int timeCosts;
    private String description;
    private String code;
    private String name;

    public AccomplishmentDto(int timeCosts, String description, String code, String name) {
        this.timeCosts = timeCosts;
        this.description = description;
        this.code = code;
        this.name = name;
    }

    public AccomplishmentDto(PersonalAccomplishment personalAccomplishment){
        this.timeCosts = personalAccomplishment.getTimeCosts();
        this.description = personalAccomplishment.getDescription();
        this.code = personalAccomplishment.getPersonalProjectPk().getProjectTeam().getCode();
        this.name = personalAccomplishment.getPersonalProjectPk().getPerson().getName();

    }

    public AccomplishmentDto() {
    }

    @Override
    public String toString() {
        return "AccomplishmentDto{" +
                "timeCosts=" + timeCosts +
                ", description='" + description + '\'' +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    public int getTimeCosts() {
        return timeCosts;
    }

    public String getDescription() {
        return description;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public void setTimeCosts(int timeCosts) {
        this.timeCosts = timeCosts;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setName(String name) {
        this.name = name;
    }
}
