package com.prs.people_registry.dao;

import com.prs.people_registry.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonDao extends JpaRepository<Person, String> {
    Optional<Person> findById(String personId);

}
