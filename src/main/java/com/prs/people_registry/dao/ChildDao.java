
package com.prs.people_registry.dao;

import com.prs.people_registry.entity.Child;
import com.prs.people_registry.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChildDao extends JpaRepository<Child, String> {
}

