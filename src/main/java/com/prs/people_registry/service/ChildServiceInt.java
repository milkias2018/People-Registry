package com.prs.people_registry.service;

import com.prs.people_registry.dto.ChildrenDto;
import com.prs.people_registry.entity.Child;
import com.prs.people_registry.exception.PersonNotFoundException;
import org.springframework.stereotype.Service;

@Service
public interface ChildServiceInt {
    ChildrenDto saveChild(String personId,ChildrenDto childrenDto) throws PersonNotFoundException;
}
