package com.Algosoft.desafioapi.controller;

import java.util.List;
import java.util.Optional;

import com.Algosoft.desafioapi.enums.Situation;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Algosoft.desafioapi.model.Commitment;
import com.Algosoft.desafioapi.service.CommitmentService;

@RestController
@RequestMapping("commitment")
public class CommitmentController {

	final CommitmentService service;

	public CommitmentController(CommitmentService service) {
		this.service = service;
	}

	@GetMapping
	public List<Commitment> findAll() {
		return service.findAll();
	}

	@GetMapping("{id}")
	public Commitment findById(@PathVariable Long id) {
		return service.findById(id);
	}

	@GetMapping("participant/{id}")
	public List<Commitment> findByParticipant(@PathVariable Long id) { return service.findByParticipant(id);
	}

	@GetMapping("participant-and-situation/{id}/{situation}")
	public List<Commitment> findByParticipantAndSituation(@PathVariable Long id, @PathVariable String situation) {
		return service.findByParticipantAndSituation(id, situation);
	}

	@PostMapping
	public Commitment save(@RequestBody Commitment commitment) {
		return service.save(commitment);
	}

	@PutMapping("{id}")
	public Commitment update(@RequestBody Commitment commitment, @PathVariable Long id) {
		return service.update(commitment, id);
	}

	@DeleteMapping("{id}")
	public void delete(@PathVariable Long id) {
		service.delete(id);
	}
}
