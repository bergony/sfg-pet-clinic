package com.example.sfgpetclinic.bootstrap;

import com.example.sfgpetclinic.model.Owner;
import com.example.sfgpetclinic.model.PetType;
import com.example.sfgpetclinic.model.Vet;
import com.example.sfgpetclinic.services.OwnerService;
import com.example.sfgpetclinic.services.PetTypeService;
import com.example.sfgpetclinic.services.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;

    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
    }


    @Override
    public void run(String... args) throws Exception {

        PetType dog = new PetType();
        dog.setName("dog");
        PetType savedDogPetType = petTypeService.save(dog);

        PetType cat = new PetType();
        cat.setName("cat");
        PetType savedCatPetType = petTypeService.save(cat);

        Owner owner1 = new Owner();

        owner1.setFirstName("bergony");
        owner1.setLastName("Bandeira");

        ownerService.save(owner1);

        Owner owner2 = new Owner();

        owner2.setFirstName("Jessica");
        owner2.setLastName("Clarissa");

        ownerService.save(owner2);

        System.out.println("Laoded Owners...");

        Vet vet1 = new Vet();

        vet1.setFirstName("Bruno");
        vet1.setLastName("bandeira");

        vetService.save(vet1);

        Vet vet2 = new Vet();

        vet2.setFirstName("Lais");
        vet2.setLastName("bandeira");

        vetService.save(vet2);

        System.out.println("Laoded Vets..");
    }
}
