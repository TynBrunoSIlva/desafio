package com.Algosoft.desafioapi.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Algosoft.desafioapi.model.Participant;
import com.Algosoft.desafioapi.service.ParticipantService;

@RestController
@RequestMapping("participant")
public class ParticipantController {

	final ParticipantService service;

	public ParticipantController(ParticipantService service) {
		this.service = service;
	}

	@GetMapping
	public List<Participant> findAll() {
		return service.findAll();
	}

	@GetMapping("{id}")
	public Optional<Participant> finById(@PathVariable Long id) {
		return service.findById(id);
	}

	@PostMapping
	public Participant save(@RequestBody Participant participant) {
		return service.save(participant);
	}

	@PutMapping
	public Participant update(@RequestBody Participant participant) {
		return service.update(participant);
	}

	@DeleteMapping("{id}")
	public void delete(@PathVariable Long id) {
		service.delete(id);
	}

}
