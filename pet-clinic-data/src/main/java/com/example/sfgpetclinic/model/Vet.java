package com.example.sfgpetclinic.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class Vet extends Person {

    private Set<Speciality> specialities;

}
