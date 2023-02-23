package com.prs.people_registry.entity;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Child implements Serializable{
@Id
private String personnummer;
@Column
private String name;
@Column
private int age;
@ManyToOne
@JoinColumn(name="person_personnummer", nullable=false)
private Person person;
}
