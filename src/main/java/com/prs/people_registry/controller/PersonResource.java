package com.prs.people_registry.controller;

import com.prs.people_registry.dao.ChildDao;
import com.prs.people_registry.dto.ChildDto;
import com.prs.people_registry.dto.ChildrenDto;
import com.prs.people_registry.dto.PersonDto;
import com.prs.people_registry.entity.Child;
import com.prs.people_registry.entity.Person;
import com.prs.people_registry.exception.ChildNotFoundException;
import com.prs.people_registry.exception.PersonNotFoundException;
import com.prs.people_registry.service.ChildService;
import com.prs.people_registry.service.PersonService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/persons")
public class PersonResource {
    @Autowired
    private PersonService personService;
    @Autowired
    private ChildService childService;

    /*
     * Endpoint to save a person
     *
     * */
    @PostMapping
    public ResponseEntity<String> savePerson(@Valid @RequestBody PersonDto personDto) {

        try {
            if (personDto != null) {
                PersonDto personDtoFromDB=personService.savePerson(personDto);
                return ResponseEntity.ok().body(personDtoFromDB.getPersonnummer());
            }
        } catch (NullPointerException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    /*
    *
    * Endpoint to store child for a person
    *  */
    @PostMapping("/{personId}/child")
    public ResponseEntity<String> saveChildForPerson(@Valid @RequestBody ChildrenDto childrenDto, @PathVariable String personId) {
        try {
            if (childrenDto != null && personId != null) {
                ChildrenDto childrenDtoFromDB=childService.saveChild(personId, childrenDto);
                return ResponseEntity.ok().body(childrenDtoFromDB.getPersonnummer());
            }
        } catch (PersonNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    /*
    *
    * Endpoint to get info about person
    * */

    @GetMapping(value = "/{personId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PersonDto> fetchPersonInfo(@PathVariable String personId) {

        try {
            if (personId != null) {
                PersonDto person= personService.fetchPersonWithChildren(personId);
                return ResponseEntity.ok(person);
            }
        } catch (PersonNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    /*
    *
    * Endpoint to get oldest child name
    * */
    @GetMapping(value = "/{personId}", params = "oldestChild",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ChildDto> getOldestChild(@PathVariable String personId,@RequestParam(name = "oldestChild") boolean oldestChild) {

        try {
            if (personId != null&& oldestChild) {
                ChildDto childDto = personService.getOldestChild(personId);
                return ResponseEntity.ok(childDto);
            }
        } catch (ChildNotFoundException | PersonNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

}
