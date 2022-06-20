package com.onix.msoauth.utils;

import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.ANY;
import static com.fasterxml.jackson.annotation.PropertyAccessor.FIELD;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.onix.msoauth.entities.Level;
import com.onix.msoauth.entities.ProjectRole;
import com.onix.msoauth.entities.ProjectStatus;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.List;

public class GetObjectFromJSON {

    public static class GetPerson{
        private String name, skills, startDate, level, projectRole, projectTeamId;
        public static List<GetPerson> readFromFile(String fileName) throws IOException {
            return new ObjectMapper().setVisibility(FIELD, ANY)
                    .readValue(new FileInputStream(fileName), new TypeReference<List<GetPerson>>() {});
        }

        public String getName() {
            return name;
        }

        public String getSkills() {
            return skills;
        }

        public LocalDate getStartDate() {
            return LocalDate.parse(startDate);
        }

        public Level getLevel() {
            return Level.valueOf(level);
        }

        public ProjectRole getProjectRole() {
            return ProjectRole.getByLabel(projectRole);
        }

        public String getProjectTeam() {
            return projectTeamId;
        }
    }

    public static class GetProjectTeam{
        private String code, projectName, description, status;
        public static List<GetProjectTeam> readFromFile(String fileName) throws IOException{
            return new ObjectMapper().setVisibility(FIELD, ANY)
                    .readValue(new FileInputStream(fileName), new TypeReference<List<GetProjectTeam>>() {});
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

        public ProjectStatus getProjectStatus() { return ProjectStatus.valueOf(status);}
    }

    public static class GetAccomplishment{
        private String timeCosts, description, code, name;
        public static List<GetAccomplishment> readFromFile(String fileName) throws IOException{
            return new ObjectMapper().setVisibility(FIELD, ANY)
                    .readValue(new FileInputStream(fileName), new TypeReference<List<GetAccomplishment>>() {});
        }

        public Integer getTimeCosts() {
            return Integer.valueOf(timeCosts);
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
    }

}
