
package com.prs.people_registry.service;

import com.prs.people_registry.dao.PersonDao;
import com.prs.people_registry.dto.ChildDto;
import com.prs.people_registry.dto.ChildrenDto;
import com.prs.people_registry.dto.PersonDto;
import com.prs.people_registry.entity.Child;
import com.prs.people_registry.entity.Person;
import com.prs.people_registry.exception.ChildNotFoundException;
import com.prs.people_registry.exception.PersonNotFoundException;
import com.prs.people_registry.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class PersonService implements PersonServiceInt {
    @Autowired
    private PersonDao personDao;

    @Override
    public ChildDto getOldestChild(String personId) throws ChildNotFoundException, PersonNotFoundException {
        Person person = personDao.findById(personId)
                .orElseThrow(() -> new PersonNotFoundException("Person not found"));
        return person.getChildren()
                .stream()
                .max(Comparator.comparing(Child::getAge))
                .map(child -> new ChildDto(child.getPersonnummer(), child.getName()))
                .orElseThrow(() -> new ChildNotFoundException("Child not found"));
    }


    @Override
    public PersonDto savePerson(PersonDto personDto) {
        Person person = new Person();
        person.setPersonnummer(personDto.getPersonnummer());
        person.setName(personDto.getName());
        person.setSpouseName(personDto.getSpouseName());
        return Utils.EntityToDtoMapper(personDao.save(person));

    }

    @Override
    public PersonDto fetchPersonWithChildren(String personId) throws PersonNotFoundException {
        Person person = personDao.findById(personId)
                .orElseThrow(() -> new PersonNotFoundException("Person not found"));
        List<ChildrenDto> childrenDtos = person.getChildren().stream()
                .map(child -> new ChildrenDto(child.getPersonnummer(), child.getName(), child.getAge()))
                .collect(Collectors.toList());

        PersonDto personDto = new PersonDto();
        personDto.setName(person.getName());
        personDto.setSpouseName(person.getSpouseName());
        personDto.setPersonnummer(person.getPersonnummer());
        personDto.setChildren(childrenDtos);

        return personDto;

    }
}

