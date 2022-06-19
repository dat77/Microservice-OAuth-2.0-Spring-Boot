package com.onix.msoauth.repository;

import com.onix.msoauth.entities.Level;
import com.onix.msoauth.entities.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;
import java.util.Optional;

@RepositoryRestResource(collectionResourceRel = "employees", path = "employees")
public interface PersonRepository extends PagingAndSortingRepository<Person, Integer> {
    Page<Person> findBySkillsContaining(String skills, Pageable pageable);
    List<Person> findByLevelAndSkillsContaining(Level level, String skills);

    Optional<Person> findByName(String name);

    @Override
    @RestResource(exported = true)
    <S extends Person> S save(S entity);

    @Override
    @RestResource(exported = false)
    <S extends Person> Iterable<S> saveAll(Iterable<S> entities);

    @Override
    @RestResource(exported = false)
    void deleteById(Integer integer);

    @Override
    @RestResource(exported = false)
    void delete(Person entity);

    @Override
    @RestResource(exported = false)
    void deleteAllById(Iterable<? extends Integer> integers);

    @Override
    @RestResource(exported = false)
    void deleteAll(Iterable<? extends Person> entities);

    @Override
    @RestResource(exported = false)
    void deleteAll();
}
