package com.AtivPet.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.AtivPet.entities.Pet;

public interface PetRepository extends JpaRepository<Pet, Long> {

}
