package com.example.sfgpetclinic.services.map;

import com.example.sfgpetclinic.model.Owner;
import com.example.sfgpetclinic.services.OwnerService;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class OwnerServiceMap extends AbstracMapService<Owner, Long> implements OwnerService {
    @Override
    public Set<Owner> findAll() {
        return super.findAll();
    }

    @Override
    public void delete(Owner object) {
        super.delete(object);
    }

    @Override
    public Owner findById(Long id) {
        return super.findByID(id);
    }

    @Override
    public Owner save(Owner object) {
        return super.save(object.getId() ,object);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteByid(id);

    }

    @Override
    public Owner findByLastName(String lastName) {
        return null;
    }
}
