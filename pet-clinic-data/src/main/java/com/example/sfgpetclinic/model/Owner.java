package com.example.sfgpetclinic.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Setter
@Getter
public class Owner extends Person {

    private Set<Pet> pets;
}
