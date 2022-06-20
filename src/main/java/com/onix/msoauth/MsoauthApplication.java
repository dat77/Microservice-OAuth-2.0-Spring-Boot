package com.onix.msoauth;

import com.onix.msoauth.services.PersonService;
import com.onix.msoauth.services.PersonalAccomplishmentService;
import com.onix.msoauth.services.ProjectTeamService;
import com.onix.msoauth.utils.GetObjectFromJSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;


@SpringBootApplication
public class MsoauthApplication implements CommandLineRunner {

	@Value("${msoauth.personsfile}")
	private String personsFile;

	@Value("${msoauth.teamsfile}")
	private String teamsFile;
	@Value("${msoauth.accomplishmentfile}")
	private String accomplishmentfile;


	@Autowired
	private PersonService personService;
	@Autowired
	private ProjectTeamService projectTeamService;
	@Autowired
	private PersonalAccomplishmentService personalAccomplishmentService;


	public static void main(String[] args) {

		SpringApplication.run(MsoauthApplication.class, args);

	}

	@Override
	public void run(String... args) throws Exception {
		loadTestDataAtStartup();
	}

	private void loadTestDataAtStartup() throws IOException {
		//projectTeamService.create("DEV000101KR", "bench", "Await/Trainee");
		GetObjectFromJSON.GetProjectTeam.readFromFile(teamsFile)
				.forEach(team -> projectTeamService.create(
						team.getCode(),
						team.getProjectName(),
						team.getDescription(),
						team.getProjectStatus()
				));
		System.out.println("Project Teams loaded: " +projectTeamService.count());
		GetObjectFromJSON.GetPerson.readFromFile(personsFile)
				.forEach(person -> personService.create(
						person.getName(),
						person.getSkills(),
						person.getStartDate(),
						person.getLevel(),
						person.getProjectRole(),
						person.getProjectTeam()
				));
		System.out.println("Persons loaded: " + personService.count());
		GetObjectFromJSON.GetAccomplishment.readFromFile(accomplishmentfile)
				.forEach(accomplishment -> personalAccomplishmentService.create(
						accomplishment.getName(),
						accomplishment.getCode(),
						accomplishment.getTimeCosts(),
						accomplishment.getDescription()
				));
		System.out.println("Accomplishments loaded: " + personalAccomplishmentService.count());

	}




}
