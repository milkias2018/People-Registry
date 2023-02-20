package com.prs.people_registry.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.prs.people_registry.dto.PersonDto;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Person implements Serializable {
    @Id
    private String personnummer;
    @Column
    private String name;
    @Column
    private String spouseName;
    @OneToMany(mappedBy = "person")
    @JsonBackReference
    private List<Child> children=new ArrayList<>();
   }

