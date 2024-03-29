package com.lojaDeGames.lojaDeGames.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lojaDeGames.lojaDeGames.model.Produto;
														//
public interface ProdutoRepository  extends JpaRepository<Produto, Long> {

	 public List<Produto> findAllByTextoContainingIgnoreCase(String texto);

}
