package com.onix.msoauth.repository;

import com.onix.msoauth.entities.Person;
import org.springframework.data.repository.CrudRepository;

public interface PersonRepository extends CrudRepository<Person, Integer> {

}
