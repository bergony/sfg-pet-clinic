package com.example.sfgpetclinic.services.map;

import com.example.sfgpetclinic.model.BaseEntity;

import java.util.*;

public abstract class AbstracMapService<T extends BaseEntity, ID extends Long> {

    protected Map<Long, T> map = new HashMap<>();

    Set<T> findAll(){
        return new HashSet<>(map.values());
    }

    T findByID(ID id) {
        return map.get(id);
    }

    T save(T object){

        if(object != null){
            if(object.getId() == null){
                object.setId(getNextID());
            }
            map.put(object.getId(), object);
        } else {
            throw  new RuntimeException("Object is NUll");
        }

        return object;
    }

    void deleteByid(ID id){
        map.remove(id);
    }

    void delete(T object){
        map.entrySet().removeIf(entry -> entry.getValue().equals(object));
    }

    private Long getNextID(){

        Long nextId = null;

        try {
            nextId = Collections.max(map.keySet()) + 1;
        } catch (NoSuchElementException e){
            nextId = 1L;
        }

        return nextId;
    }
}
