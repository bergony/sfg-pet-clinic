package com.example.sfgpetclinic.services.map;

import com.example.sfgpetclinic.model.Owner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class OwnerMapServiceTest {

    OwnerMapService ownerMapService;

    final Long ownerID = 1L;
    final String lastName = "Bergony";

    @BeforeEach
    void setUp() {
        ownerMapService = new OwnerMapService(new PetTypeMapService(),
                new PetMapService());

        Owner owner = Owner.builder().build();
        owner.setId(ownerID);
        owner.setLastName(lastName);

        ownerMapService.save(owner);
    }

    @Test
    void findAll() {
        Set<Owner> owners = ownerMapService.findAll();

        assertEquals(ownerID,owners.size());

    }


    @Test
    void findById() {
        Owner owner = ownerMapService.findById(ownerID);
        assertEquals(ownerID,owner.getId());
    }

    @Test
    void saveExistingId() {
        Owner owner2 = Owner.builder().build();
        long id = 2l;
        owner2.setId(id);

        Owner savedOwner = ownerMapService.save(owner2);

        assertEquals(id, savedOwner.getId());




    }

    @Test
    void saveNoId() {
        Owner savedOwner = ownerMapService.save(Owner.builder().build());
        assertNotNull(savedOwner);
        assertNotNull(savedOwner.getId());
    }

    @Test
    void delete() {

        ownerMapService.delete(ownerMapService.findById(ownerID));

        assertEquals(0, ownerMapService.findAll().size());
    }


    @Test
    void deleteById() {
        ownerMapService.deleteById(ownerID);
        assertEquals(0, ownerMapService.findAll().size());

    }

    @Test
    void findByLastName() {
        Owner bergony = ownerMapService.findByLastName(lastName);
        assertNotNull(bergony);
        assertEquals(ownerID, bergony.getId());
    }
    @Test
    void findByLastNameNotFound() {
        Owner bergony = ownerMapService.findByLastName("foo");
        assertNull(bergony);
    }
}