package com.onix.msoauth.utils;

import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.ANY;
import static com.fasterxml.jackson.annotation.PropertyAccessor.FIELD;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.onix.msoauth.entities.Level;
import com.onix.msoauth.entities.ProjectRole;
import com.onix.msoauth.entities.ProjectStatus;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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

    public static class GetRole{
        private String roleName, description;
        public static List<GetRole> readFromFile(String fileName) throws IOException{
            return new ObjectMapper().setVisibility(FIELD, ANY)
                    .readValue(new FileInputStream(fileName), new TypeReference<List<GetRole>>() {});
        }

        public String getRoleName() {
            return roleName;
        }

        public String getDescription() {
            return description;
        }
    }

    public static class GetSecurityUser{
        private String username, password, firstName, lastName, roles;
        public static List<GetSecurityUser> readFromFile(String fileName) throws IOException{
            return new ObjectMapper().setVisibility(FIELD, ANY)
                    .readValue(new FileInputStream(fileName), new TypeReference<List<GetSecurityUser>>() {});
        }

        public String getUsername() {
            return username;
        }

        public String getPassword() {
            return password;
        }

        public String getFirstName() {
            return firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public List<String> getRoles() {
            return Arrays.stream(roles.split(" ")).collect(Collectors.toList());
        }
    }


}
