
package com.prs.people_registry.service;

import com.prs.people_registry.dto.ChildDto;
import com.prs.people_registry.dto.PersonDto;
import com.prs.people_registry.exception.ChildNotFoundException;
import org.springframework.stereotype.Service;

@Service
public interface PersonServiceInt {
    ChildDto getOldestChild(String personId) throws ChildNotFoundException;
    PersonDto savePerson(PersonDto personDto);
    PersonDto fetchPersonWithChildren(String personId);
}

