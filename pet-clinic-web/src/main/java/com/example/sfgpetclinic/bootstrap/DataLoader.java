package com.example.sfgpetclinic.bootstrap;

import com.example.sfgpetclinic.model.*;
import com.example.sfgpetclinic.services.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;
    private final SpecialityService specialityService;
    private final VisitService visitService;

    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService, SpecialityService specialityService, VisitService visitService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
        this.specialityService = specialityService;
        this.visitService = visitService;
    }


    @Override
    public void run(String... args) throws Exception {

        int count = petTypeService.findAll().size();

        if(count == 0)
            loadData();
    }

    private void loadData() {
        PetType dog = new PetType();
        dog.setName("dog");
        PetType savedDogPetType = petTypeService.save(dog);

        PetType cat = new PetType();
        cat.setName("cat");
        PetType savedCatPetType = petTypeService.save(cat);

        Speciality radiology = new Speciality();
        radiology.setDescription("Raidlogy");
        Speciality savedRadiology = specialityService.save(radiology);

        Speciality surgery = new Speciality();
        radiology.setDescription("Surgery");
        Speciality savedSurgery = specialityService.save(surgery);

        Speciality dentistry = new Speciality();
        radiology.setDescription("Dentistry");
        Speciality savedDentistry = specialityService.save(dentistry);

        Owner owner1 = Owner.builder().build();

        owner1.setFirstName("bergony");
        owner1.setLastName("Bandeira");
        owner1.setAddress("123 Congonhas");
        owner1.setCity("parnamirim");
        owner1.setTelephone("12354896");

        Pet mikesPet = new Pet();
        mikesPet.setPetType(savedDogPetType);
        mikesPet.setOwner(owner1);
        mikesPet.setBirthDate(LocalDate.now());
        mikesPet.setName("Masha");
        owner1.getPets().add(mikesPet);

        ownerService.save(owner1);

        Owner owner2 = Owner.builder().build();

        owner2.setFirstName("Jessica");
        owner2.setLastName("Clarissa");
        owner2.setAddress("123 Cerro");
        owner2.setCity("natal");
        owner2.setTelephone("12354896");

        Pet fionaCat = new Pet();
        fionaCat.setName("Apolo");
        fionaCat.setOwner(owner2);
        fionaCat.setBirthDate(LocalDate.now());
        fionaCat.setPetType(savedCatPetType);
        owner2.getPets().add(fionaCat);

        ownerService.save(owner2);

        Visit catVisit = new Visit();
        catVisit.setPet(fionaCat);
        catVisit.setDate(LocalDate.now());
        catVisit.setDescription("Snnez kitty");

        visitService.save(catVisit);

        System.out.println("Laoded Owners...");

        Vet vet1 = Vet.builder().build();

        vet1.setFirstName("Bruno");
        vet1.setLastName("bandeira");
        vet1.getSpecialities().add(savedRadiology);

        vetService.save(vet1);

        Vet vet2 = Vet.builder().build();

        vet2.setFirstName("Lais");
        vet2.setLastName("bandeira");
        vet2.getSpecialities().add(savedSurgery);

        vetService.save(vet2);

        System.out.println("Laoded Vets..");
    }
}
