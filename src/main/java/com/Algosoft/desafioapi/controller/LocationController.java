package com.Algosoft.desafioapi.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.Algosoft.desafioapi.model.Location;
import com.Algosoft.desafioapi.service.LocationService;

@RestController
@RequestMapping("location")
public class LocationController {

	final LocationService service;

	public LocationController(LocationService service) {
		this.service = service;
	}

	@GetMapping
	public List<Location> findAll() {
		return service.findAll();
	}

	@GetMapping("{id}")
	public Location findById(@PathVariable Long id) {
		return service.findById(id);
	}

	@PostMapping
	public Location save(@RequestBody Location location) {
		return service.save(location);
	}

	@PutMapping("{id}")
	public Location update(@RequestBody Location location, @PathVariable Long id) {
		return service.update(location, id);
	}

	@DeleteMapping("{id}")
	public void delete(@PathVariable Long id) {
		service.delete(id);
	}
}
