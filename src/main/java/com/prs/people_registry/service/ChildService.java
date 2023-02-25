package com.prs.people_registry.service;

import com.prs.people_registry.dao.ChildDao;
import com.prs.people_registry.dao.PersonDao;
import com.prs.people_registry.dto.ChildrenDto;
import com.prs.people_registry.entity.Child;
import com.prs.people_registry.entity.Person;
import com.prs.people_registry.exception.PersonNotFoundException;
import com.prs.people_registry.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ChildService implements ChildServiceInt {
    @Autowired
    private PersonDao personDao;
    @Autowired
    private ChildDao childDao;

    @Override
    public ChildrenDto saveChild(String personId, ChildrenDto childrenDto) throws PersonNotFoundException {
        Person person = personDao.findById(personId)
                .orElseThrow(() -> new PersonNotFoundException("Person not found"));

        Child child = new Child();
        child.setPersonnummer(childrenDto.getPersonnummer());
        child.setName(childrenDto.getName());
        child.setAge(childrenDto.getAge());
        child.setPerson(person);

        return Utils.EntityToDtoMapper(childDao.save(child));

    }
}
