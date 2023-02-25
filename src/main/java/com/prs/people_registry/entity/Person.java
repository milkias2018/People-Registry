package com.prs.people_registry.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


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
    @OneToMany(mappedBy = "person",fetch = FetchType.EAGER)
    @JsonIgnore
    private List<Child> children=new ArrayList<>();
   }

