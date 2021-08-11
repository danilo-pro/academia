package com.daniloperez.academia.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.daniloperez.academia.domain.Estabelecimento;
import com.daniloperez.academia.services.EstabelecimentoService;

@RestController
@RequestMapping(value="/estabelecimentos")
public class EstabelecimentoResource {
	
	@Autowired
	private EstabelecimentoService service;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable Integer id) {
		
		Estabelecimento obj = service.find(id);
		
		return ResponseEntity.ok().body(obj);
	}
}
