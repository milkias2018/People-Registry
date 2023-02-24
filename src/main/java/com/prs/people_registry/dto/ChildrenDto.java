package com.prs.people_registry.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ChildrenDto implements Serializable {
    @Pattern(regexp ="^\\d{8}[-\\s]?\\d{4}\\r?$" )
    private String personnummer;
    @NotBlank
    @Size(max = 100)
    private String name;
    @NotBlank
    @Max(130)
    private int age;
   }
