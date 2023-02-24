package com.prs.people_registry.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
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
    @Pattern(regexp ="^\\d{8}[-\\s]?\\d{4}\\r?$" )
    private String personnummer;
    @NotBlank
    private String name;
    @NotBlank
    private String spouseName;
    private List<ChildrenDto> children;

}
