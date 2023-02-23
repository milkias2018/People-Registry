package com.prs.people_registry.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PersonDto implements Serializable {
    private String personnummer;
    private String name;
    private String spouseName;
    private List<ChildrenDto> children;

}
