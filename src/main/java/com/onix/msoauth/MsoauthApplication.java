package com.onix.msoauth;

import com.onix.msoauth.services.PersonService;
import com.onix.msoauth.services.ProjectTeamService;
import com.onix.msoauth.utils.GetObjectFromJSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;


@SpringBootApplication
public class MsoauthApplication implements CommandLineRunner {
	@Autowired
	private PersonService personService;
	@Autowired
	private ProjectTeamService projectTeamService;


	public static void main(String[] args) {

		SpringApplication.run(MsoauthApplication.class, args);

	}

	@Override
	public void run(String... args) throws Exception {
		loadTestDataAtStartup();
	}

	private void loadTestDataAtStartup() throws IOException {
		projectTeamService.create("DEV000101KR", "bench", "Await/Trainee");
//		projectTeamService.create("DEV220101KR", "insuranceCH", "CRM System for InsGlob Corp.");
//		projectTeamService.create("DEV220201KR", "AgroSearcher", "GIS for Tomato LTD");
//		projectTeamService.create("DEV220205KY", "Aviadrom", "ERP System for AEGEAN");
//		projectTeamService.create("DEV220210KR", "fINHelper", "quote analyzer for UBS");
		GetObjectFromJSON.GetProjectTeam.readFromFile("initData/projectTeam.json")
				.forEach(team -> projectTeamService.create(
						team.getCode(),
						team.getProjectName(),
						team.getDescription()
				));
		System.out.println("Project Teams loaded: " +projectTeamService.count());
		GetObjectFromJSON.GetPerson.readFromFile("initData/persons.json")
				.forEach(person -> personService.create(
						person.getName(),
						person.getSkills(),
						person.getStartDate(),
						person.getLevel(),
						person.getProjectRole(),
						person.getProjectTeam()
				));
		System.out.println("Persons loaded:" + personService.count());

	}




}
