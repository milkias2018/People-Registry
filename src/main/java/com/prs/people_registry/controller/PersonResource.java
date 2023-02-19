package com.prs.people_registry.controller;

import com.prs.people_registry.dao.ChildDao;
import com.prs.people_registry.dto.ChildDto;
import com.prs.people_registry.dto.ChildrenDto;
import com.prs.people_registry.dto.PersonDto;
import com.prs.people_registry.entity.Child;
import com.prs.people_registry.entity.Person;
import com.prs.people_registry.exception.ChildNotFoundException;
import com.prs.people_registry.service.ChildService;
import com.prs.people_registry.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity savePerson(@RequestBody PersonDto personDto) {

        try {
            if (personDto != null) {
                personService.savePerson(personDto);
                return ResponseEntity.ok().body(personService.savePerson(personDto));
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
    public ResponseEntity saveChildForPerson(@RequestBody ChildrenDto childrenDto, @PathVariable String personId) {
        try {
            if (childrenDto != null && personId != null) {
                childService.saveChild(personId, childrenDto);
                return ResponseEntity.ok().body(childService.saveChild(personId, childrenDto));
            }
        } catch (NullPointerException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    /*
    *
    * Endpoint to get info about person
    * */

    @GetMapping(value = "/{personId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity fetchPersonInfo(@PathVariable String personId) {

        try {
            if (personId != null) {
                List<Child> personChildren = personService.fetchPersonWithChildren(personId);
                return ResponseEntity.ok(personChildren);
            }
        } catch (NullPointerException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    /*
    *
    * Endpoint to get oldest child name
    * */
    @GetMapping(value = "/{personId}/oldestChild", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getOldestChild(@PathVariable String personId) {

        try {
            if (personId != null) {
                ChildDto childDto = personService.getOldestChild(personId);
                return ResponseEntity.ok(childDto);
            }
        } catch (ChildNotFoundException | NullPointerException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

}
