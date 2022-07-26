package com.Algosoft.desafioapi.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Algosoft.desafioapi.model.History;
import com.Algosoft.desafioapi.service.HistoryService;

@RestController
@RequestMapping("history")
public class HistoryController {

	final HistoryService service;

	public HistoryController(HistoryService  service) {
		this. service =  service;
	}

	@GetMapping
	public List<History> findAll() {
		return  service.findAll();
	}

	@GetMapping("commitment/{id}")
	public List<History> findAllByCommitment(@PathVariable Long id){
		return service.findAllByCommitment(id);
	}


}
