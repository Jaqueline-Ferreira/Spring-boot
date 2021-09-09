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

import com.lojaDeGames.lojaDeGames.model.Produto;
import com.lojaDeGames.lojaDeGames.repository.ProdutoRepository;

@RestController
@RequestMapping("/Produto")
@CrossOrigin("*")
public class ProdutoController {
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@GetMapping(path = "/todos")
	public List<Produto> listarTodosOsProdutos() {
		
		return produtoRepository.findAll();
	}
	
	@GetMapping(path = "/{id}")
	public 	Produto procurarProdutoPorId
		(@PathVariable(value = "id") Long id) {
		
		Optional<Produto> produtoRetornadoFindById; 
													//OPTIOANL(PRODUTO)
		produtoRetornadoFindById =  produtoRepository.findById(id);
	
		return produtoRetornadoFindById.get();
	}
	
	@GetMapping(path = "/buscaPorTexto")
	public 	Produto procurarPorTexto
		(@RequestBody String texto) {
		
		Produto produto;
		
		produto = (Produto)produtoRepository.findAllByTextoContainingIgnoreCase(texto);
	
		return produto;
	}
	
	@PostMapping
	public ResponseEntity<Produto> post (Produto produto){
		return ResponseEntity.status(HttpStatus.CREATED).body(produtoRepository.save(produto));
	}
	
	@PutMapping
	public ResponseEntity<Produto> put (Produto produto){
		return ResponseEntity.status(HttpStatus.OK).body(produtoRepository.save(produto));
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable long id) {
		produtoRepository.deleteById(id);
	}


}
