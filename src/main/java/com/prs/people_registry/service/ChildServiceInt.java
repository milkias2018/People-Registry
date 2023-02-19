package com.prs.people_registry.service;

import com.prs.people_registry.dto.ChildrenDto;
import com.prs.people_registry.entity.Child;
import org.springframework.stereotype.Service;

@Service
public interface ChildServiceInt {
    Child saveChild(String personId,ChildrenDto childrenDto);
}
