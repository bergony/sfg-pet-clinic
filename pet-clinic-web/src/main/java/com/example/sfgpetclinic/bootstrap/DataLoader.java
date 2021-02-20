package com.example.sfgpetclinic.bootstrap;

import com.example.sfgpetclinic.model.Owner;
import com.example.sfgpetclinic.model.Person;
import com.example.sfgpetclinic.model.Vet;
import com.example.sfgpetclinic.services.OwnerService;
import com.example.sfgpetclinic.services.VetService;
import com.example.sfgpetclinic.services.map.OwnerServiceMap;
import com.example.sfgpetclinic.services.map.VetServiceMap;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;

    public DataLoader(OwnerService ownerService, VetService vetService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
    }


    @Override
    public void run(String... args) throws Exception {

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
