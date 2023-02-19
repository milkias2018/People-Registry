package com.prs.people_registry.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PersonDto {
    private String personnummer;
    private String name;
    private String spouseName;

    private List<ChildrenDto> children;

}
