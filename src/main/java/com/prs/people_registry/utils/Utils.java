package com.prs.people_registry.utils;

import com.prs.people_registry.dto.PersonDto;
import com.prs.people_registry.entity.Person;

public class Utils {
      public static PersonDto EntityToDtoMapper(Person person) {
        PersonDto personDto=new PersonDto();
        personDto.setPersonnummer(person.getPersonnummer());
        personDto.setName(personDto.getName());
        personDto.setSpouseName(person.getSpouseName());
        return personDto;
    }
}
