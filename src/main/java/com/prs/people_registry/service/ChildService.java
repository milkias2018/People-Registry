package com.prs.people_registry.service;

import com.prs.people_registry.dao.ChildDao;
import com.prs.people_registry.dao.PersonDao;
import com.prs.people_registry.dto.ChildrenDto;
import com.prs.people_registry.entity.Child;
import com.prs.people_registry.entity.Person;
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
    public Child saveChild(String personId,ChildrenDto childrenDto) {
        Optional<Person> person=personDao.findById(personId);
        Child child=new Child();
        child.setPersonnummer(childrenDto.getPersonnummer());
        child.setName(childrenDto.getName());
        child.setAge(childrenDto.getAge());
        child.setPerson(person.get());
        return childDao.save(child);
    }
}
