package com.onix.msoauth.repository;

import com.onix.msoauth.entities.Level;
import com.onix.msoauth.entities.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface PersonRepository extends PagingAndSortingRepository<Person, Integer> {
    Page<Person> findBySkillsContaining(String skills, Pageable pageable);
    List<Person> findByLevelAndSkillsContaining(Level level, String skills);
}
