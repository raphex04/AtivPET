package com.AtivPet.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.AtivPet.dto.PetDto;
import com.AtivPet.entities.Pet;
import com.AtivPet.service.PetService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/pet")
public class PetController {
	private final PetService petService;
	@Autowired
	public PetController(PetService petService) {
		this.petService = petService;
	}
	@PostMapping("/")
	public ResponseEntity<PetDto> criar(@RequestBody @Valid PetDto petDto){
		PetDto salvarPet = petService.salvar(petDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(salvarPet);
	}
	@PutMapping("/{id}")
	public ResponseEntity<PetDto> alterar(@PathVariable Long id, @RequestBody @Valid PetDto petDto){
		PetDto alteraPetDto = petService.atualizar(id, petDto);
		if(alteraPetDto != null) {
			return ResponseEntity.ok(alteraPetDto);
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<Pet> delete(@PathVariable Long id){
		boolean delete = petService.delete(id);
		if(delete) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
		else {
			return ResponseEntity.notFound().build();
		}
		
	}
	@GetMapping("/{id}")
	public ResponseEntity<Pet> buscarPorId(@PathVariable Long id){
		Pet pet = petService.buscarPorId(id);
		if(pet != null) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}
	@GetMapping
	public ResponseEntity<List<Pet>> buscaTodos(){
		List<Pet> Pet = petService.buscarTodos();
		return ResponseEntity.ok(Pet);
	}
}