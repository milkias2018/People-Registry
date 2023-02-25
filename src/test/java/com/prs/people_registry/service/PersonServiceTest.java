package com.prs.people_registry.service;

import com.prs.people_registry.dto.ChildrenDto;
import com.prs.people_registry.dto.PersonDto;
import com.prs.people_registry.exception.ChildNotFoundException;
import com.prs.people_registry.exception.PersonNotFoundException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@RunWith(SpringRunner.class)
class PersonServiceTest {
    @Autowired
    private PersonService personService;
    @Autowired
    private ChildService childService;
    private PersonDto personDtoFromDB;

    @BeforeEach
    void init() throws PersonNotFoundException {
       personDtoFromDB=personService.savePerson(personDtoPayload());
       childService.saveChild(personDtoFromDB.getPersonnummer(), childrenDtoPayload());
    }

    @Test
    void getOldestChild() throws PersonNotFoundException, ChildNotFoundException {
        assertEquals("Senior Baby", personService.getOldestChild(personDtoFromDB.getPersonnummer()).getOldestChildName());
    }

    @Test
    void savePerson() {
        assertEquals(personDtoFromDB.getPersonnummer(),personDtoPayload().getPersonnummer());
    }

    @Test
    void fetchPersonWithChildren() throws PersonNotFoundException {
        PersonDto personDto=personService.fetchPersonWithChildren(personDtoFromDB.getPersonnummer());
        assertEquals(personDto.getChildren().get(0).getName(),childrenDtoPayload().getName());
    }

    private PersonDto personDtoPayload() {
        PersonDto personDto = new PersonDto();
        personDto.setPersonnummer("19780909-0202");
        personDto.setName("John Doe");
        personDto.setSpouseName("Hanna");
        return personDto;
    }

    private ChildrenDto childrenDtoPayload() {
        ChildrenDto childrenDto = new ChildrenDto();
        childrenDto.setPersonnummer("19980909-3202");
        childrenDto.setName("Senior Baby");
        childrenDto.setAge(16);
        return childrenDto;
    }
}