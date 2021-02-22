package com.example.sfgpetclinic.services.map;

import com.example.sfgpetclinic.model.Speciality;
import com.example.sfgpetclinic.services.SpecialtiesService;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class SpecialityMapService extends AbstracMapService<Speciality, Long> implements SpecialtiesService {
    @Override
    public Set<Speciality> findAll() {
        return super.findAll();
    }

    @Override
    public void delete(Speciality object) {
        super.delete(object);
    }

    @Override
    public Speciality save(Speciality object) {
        return super.save(object);
    }

    @Override
    public Speciality findById(Long id) {
        return super.findByID(id);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteByid(id);
    }
}
