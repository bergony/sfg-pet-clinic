package com.example.sfgpetclinic.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class Pet extends BaseEntity {

    private PetType petType;
    private Owner owner;
    private LocalDate birDate;
}
