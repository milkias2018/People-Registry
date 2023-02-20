
package com.prs.people_registry.service;

import com.prs.people_registry.dto.ChildDto;
import com.prs.people_registry.dto.PersonDto;
import com.prs.people_registry.entity.Child;
import com.prs.people_registry.entity.Person;
import com.prs.people_registry.exception.ChildNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface PersonServiceInt {
    ChildDto getOldestChild(String personId) throws ChildNotFoundException;
    Person savePerson(PersonDto personDto);
    PersonDto fetchPersonWithChildren(String personId);
}

