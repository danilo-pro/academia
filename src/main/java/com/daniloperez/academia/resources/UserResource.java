package com.daniloperez.academia.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.daniloperez.academia.dto.PerfilDTO;
import com.daniloperez.academia.services.UserService;

@RestController
@RequestMapping(value="/usuarios")
public class UserResource {
	
	@Autowired UserService service;
	
	//Busca de usuario por Email
	@RequestMapping(value="/email", method=RequestMethod.GET)
	public ResponseEntity<?> find(@RequestParam(value="value") String email) {
		PerfilDTO obj = service.findByEmail(email);
		
		return ResponseEntity.ok().body(obj);
	}
}
