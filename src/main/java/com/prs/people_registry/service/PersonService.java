
package com.prs.people_registry.service;

import com.prs.people_registry.dao.ChildDao;
import com.prs.people_registry.dao.PersonDao;
import com.prs.people_registry.dto.ChildDto;
import com.prs.people_registry.dto.PersonDto;
import com.prs.people_registry.entity.Child;
import com.prs.people_registry.entity.Person;
import com.prs.people_registry.exception.ChildNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;

@Component
public class PersonService implements PersonServiceInt {
    @Autowired
    private PersonDao personDao;
    @Autowired
    private ChildDao childDao;

    @Override
    public ChildDto getOldestChild(String personId) throws ChildNotFoundException {
        List<Child> childDto = childDao.findByPersonPersonnummer(personId);
        Child maxChild = childDto.stream()
                .max(Comparator.comparing(Child::getAge))
                .get();

        ChildDto childDtoMap = new ChildDto();
        childDtoMap.setPersonnummer(maxChild.getPerson().getPersonnummer());
        childDtoMap.setOldestChildName(maxChild.getName());

        return childDtoMap;
    }

    @Override
    public Person savePerson(PersonDto personDto) {
        Person person = new Person();
        person.setPersonnummer(personDto.getPersonnummer());
        person.setName(personDto.getName());
        person.setSpouseName(personDto.getSpouseName());
        return personDao.save(person);
    }

    @Override
    public List<Child> fetchPersonWithChildren(String personId) {
        List<Child> list = childDao.findByPersonPersonnummer(personId);
        return list;

    }
}

