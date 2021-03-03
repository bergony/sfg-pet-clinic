package com.example.sfgpetclinic.formatters;

import com.example.sfgpetclinic.model.PetType;
import com.example.sfgpetclinic.services.PetTypeService;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Collection;
import java.util.Locale;

@Component
public class PetTypeFormatter implements Formatter<PetType> {

    private final PetTypeService petTypeService;

    public PetTypeFormatter(PetTypeService petTypeService) {
        this.petTypeService = petTypeService;
    }

    @Override
    public String print(PetType petType, Locale locale){
        return petType.getName();
    }

    @Override
    public PetType parse(String text, Locale locale) throws ParseException{
        Collection<PetType> findPetTypes = petTypeService.findAll();
        for (PetType type: findPetTypes) {
            if(type.getName().equals(text)) {
                return type;
            }
        }
        throw  new ParseException("Type not Found" + text,0);
    }
}
