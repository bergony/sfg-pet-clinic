package com.example.sfgpetclinic.controllers;

import com.example.sfgpetclinic.model.Owner;
import com.example.sfgpetclinic.services.OwnerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class OwnerControllerTest {

    @Mock
    OwnerService ownerService;

    @InjectMocks
    OwnerController controller;

    Set<Owner> owners;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        owners = new HashSet<>();
        Owner owner = Owner.builder().build();
        owner.setId(1l);
        Owner owner1 = Owner.builder().build();
        owner1.setId(2l);

        owners.add(owner);
        owners.add(owner1);

        mockMvc = MockMvcBuilders
                .standaloneSetup(controller)
                .build();
    }

    @Test
    void initCreationForm() throws Exception {
        mockMvc.perform(get("/owners/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("owners/createOrUpdateOwnerForm"))
                .andExpect(model().attributeExists("owner"));

        verifyNoInteractions(ownerService);
    }

    @Test
    void processCreationForm() throws Exception {

        Owner owner = Owner.builder().build();
        owner.setId(1l);

        when(ownerService.save(ArgumentMatchers.any())).thenReturn(owner);

        mockMvc.perform(post("/owners/new"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/owners/1"))
                .andExpect(model().attributeExists("owner"));

        verify(ownerService).save(ArgumentMatchers.any());
    }

    @Test
    void initUpdateOwnerForm() throws Exception {

        Owner owner = Owner.builder().build();
        owner.setId(1l);

        when(ownerService.findById(anyLong())).thenReturn(owner);

        mockMvc.perform(get("/owners/1/edit"))
                .andExpect(status().isOk())
                .andExpect(view().name("owners/createOrUpdateOwnerForm"))
                .andExpect(model().attributeExists("owner"));

        verifyNoMoreInteractions(ownerService);
    }

    @Test
    void processUpdateOwnerForm() throws Exception {
        Owner owner = Owner.builder().build();
        owner.setId(1l);

        when(ownerService.save(ArgumentMatchers.any())).thenReturn(owner);
        mockMvc.perform(post("/owners/1/edit"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/owners/1"))
                .andExpect(model().attributeExists("owner"));

        verify(ownerService).save(ArgumentMatchers.any());
    }


    @Test
    void findOwners() throws Exception {

        mockMvc.perform(get("/owners/find"))
                .andExpect(status().isOk())
                .andExpect(view().name("owners/findOwners"))
                .andExpect(model().attributeExists("owner"));

        verifyNoInteractions(ownerService);
    }

    @Test
    void processFindFormReturnMany() throws Exception {

        Owner owner = Owner.builder().build();
        owner.setId(1l);
        Owner owner2 = Owner.builder().build();
        owner2.setId(2l);

        when(ownerService.findAllByLastNameLike(anyString())).thenReturn(Arrays.asList(owner, owner2));

        mockMvc.perform(get("/owners"))
                .andExpect(status().isOk())
                .andExpect(view().name("owners/ownersList"))
                .andExpect(model().attribute("selections", hasSize(2)));
    }

    @Test
    void processFindFormReturnOne() throws Exception {

        Owner owner = Owner.builder().build();
        owner.setId(1l);
        when(ownerService.findAllByLastNameLike((anyString()))).thenReturn(Arrays.asList(owner));

        mockMvc.perform(get("/owners"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:owners/1"));

    }


    @Test
    void displaOwner() throws Exception {
        Owner owner = Owner.builder().build();
        owner.setId(1l);

        when(ownerService.findById(anyLong())).thenReturn(owner);

        mockMvc.perform(get("/owners/123"))
                .andExpect(status().isOk())
                .andExpect(view().name("owners/ownerDetails"))
                .andExpect(model().attribute("owner", hasProperty("id", is(1l))));
    }
}