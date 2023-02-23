
package com.prs.people_registry.service;

import com.prs.people_registry.dao.PersonDao;
import com.prs.people_registry.dto.ChildDto;
import com.prs.people_registry.dto.ChildrenDto;
import com.prs.people_registry.dto.PersonDto;
import com.prs.people_registry.entity.Child;
import com.prs.people_registry.entity.Person;
import com.prs.people_registry.exception.ChildNotFoundException;
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
    public ChildDto getOldestChild(String personId) throws ChildNotFoundException {
        Optional<Person> person = personDao.findById(personId);
        return person.isPresent()?person.get().getChildren()
                .stream()
                .max(Comparator.comparing(Child::getAge))
                .map(child -> new ChildDto(child.getPersonnummer(), child.getName()))
                .orElseThrow(() -> new ChildNotFoundException("Child not found")):null;
    }


    @Override
    public PersonDto savePerson(PersonDto personDto) {
        Person person = new Person();
        person.setPersonnummer(personDto.getPersonnummer());
        person.setName(personDto.getName());
        person.setSpouseName(personDto.getSpouseName());
        Person savedPerson=personDao.save(person);

        return Utils.EntityToDtoMapper(savedPerson);

    }

    @Override
    public PersonDto fetchPersonWithChildren(String personId) {
        Optional<Person> person = personDao.findById(personId);
        List<ChildrenDto> childrenDtos = person.get().getChildren().stream()
                .map(child -> new ChildrenDto(child.getPersonnummer(), child.getName(), child.getAge()))
                .collect(Collectors.toList());

        PersonDto personDto = new PersonDto();
        personDto.setName(person.get().getName());
        personDto.setSpouseName(person.get().getSpouseName());
        personDto.setPersonnummer(person.get().getPersonnummer());
        personDto.setChildren(childrenDtos);

        return personDto;

    }
}

