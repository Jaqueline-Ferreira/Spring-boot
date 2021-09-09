package com.lojaDeGames.lojaDeGames.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lojaDeGames.lojaDeGames.model.Categoria;
import com.lojaDeGames.lojaDeGames.repository.CategoriaRepository;

@RestController
@RequestMapping("/Categoria")
@CrossOrigin("*")
public class CategoriaController {
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@GetMapping(path = "/todos")
	public List<Categoria> listarTodasAsCategorias() {
		
		return categoriaRepository.findAll();
	}
	
	@GetMapping(path = "/{id}")
	public 	ResponseEntity<Optional<Categoria>>procurarCategoriaPorId
		(@PathVariable(value = "id") Long id) {
		
		return ResponseEntity.status(HttpStatus.OK).body(categoriaRepository.findById(id));
	}
	
	@GetMapping(path = "/buscaPorTexto")
	public ResponseEntity<List<Categoria>> procurarPorTexto
		(@RequestBody String texto) {
		
		return ResponseEntity.status(HttpStatus.CREATED).body(categoriaRepository.findAllByTextoContainingIgnoreCase(texto));
	}
	
	@PostMapping
	public ResponseEntity<Categoria> post (Categoria categoria){
		return ResponseEntity.status(HttpStatus.CREATED).body(categoriaRepository.save(categoria));
	}
	
	@PutMapping
	public ResponseEntity<Categoria> put (Categoria categoria){
		return ResponseEntity.status(HttpStatus.OK).body(categoriaRepository.save(categoria));
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable long id) {
		categoriaRepository.deleteById(id);
	}
	
}