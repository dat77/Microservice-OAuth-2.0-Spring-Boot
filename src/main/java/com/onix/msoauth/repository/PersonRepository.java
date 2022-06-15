package com.onix.msoauth.repository;

import com.onix.msoauth.entities.Level;
import com.onix.msoauth.entities.Person;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PersonRepository extends CrudRepository<Person, Integer> {
    List<Person> findBySkillsContaining(String skills);
    Iterable<Person> findByLevelAndSkillsContaining(Level level, String skills);
}
