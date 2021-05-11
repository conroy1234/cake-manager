package com.waracle.cakemgr.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.waracle.cakemgr.entity.CakeEntity;
import com.waracle.cakemgr.service.CakeService;

@RestController(value = "/")
public class CakeApi {

	@Autowired
	private CakeService cakeService;

	@GetMapping(path = "/cakes")
	public Iterable<CakeEntity> findAllCakes() {
		return cakeService.findAll();
	}

	@GetMapping(path = "/cakes/id/{id}")
	public CakeEntity findCakeById(@PathVariable int id) {
		return cakeService.findById(id);
	}

	@PostMapping(path = "/cakes")
	public ResponseEntity<URI> createCakes(@RequestBody CakeEntity cake) {
		CakeEntity entity = cakeService.createNewCake(cake);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/id/{id}")
				.buildAndExpand(entity.getEmployeeId()).toUri();
		return new ResponseEntity<>(location, HttpStatus.CREATED);
	}
	
	@PostMapping(path = "/cakes/write")
	public CakeEntity writeCake(@RequestBody CakeEntity cake) {
		CakeEntity entity = cakeService.createCake(cake);		
		return entity;
	}


}
