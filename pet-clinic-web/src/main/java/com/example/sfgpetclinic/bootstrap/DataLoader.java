package com.example.sfgpetclinic.bootstrap;

import com.example.sfgpetclinic.model.Owner;
import com.example.sfgpetclinic.model.Pet;
import com.example.sfgpetclinic.model.PetType;
import com.example.sfgpetclinic.model.Vet;
import com.example.sfgpetclinic.services.OwnerService;
import com.example.sfgpetclinic.services.PetTypeService;
import com.example.sfgpetclinic.services.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

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
        owner1.setAddress("123 Congonhas");
        owner1.setCity("parnamirim");
        owner1.setTelephone("12354896");

        Pet mikesPet = new Pet();
        mikesPet.setPetType(savedDogPetType);
        mikesPet.setOwner(owner1);
        mikesPet.setBirDate(LocalDate.now());
        mikesPet.setName("Masha");
        owner1.getPets().add(mikesPet);

        ownerService.save(owner1);

        Owner owner2 = new Owner();

        owner2.setFirstName("Jessica");
        owner2.setLastName("Clarissa");
        owner2.setAddress("123 Cerro");
        owner2.setCity("natal");
        owner2.setTelephone("12354896");

        Pet fionaCat = new Pet();
        fionaCat.setName("Apolo");
        fionaCat.setOwner(owner2);
        fionaCat.setBirDate(LocalDate.now());
        fionaCat.setPetType(savedCatPetType);
        owner2.getPets().add(fionaCat);

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
