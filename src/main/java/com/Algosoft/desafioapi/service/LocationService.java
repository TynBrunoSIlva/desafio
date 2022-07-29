package com.Algosoft.desafioapi.service;

import java.util.List;
import java.util.Optional;

import com.Algosoft.desafioapi.model.Commitment;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.Algosoft.desafioapi.model.Location;
import com.Algosoft.desafioapi.repository.LocationRepository;

@Service
public class LocationService {

	final LocationRepository repository;

	final CommitmentService commitmentService;

	public LocationService(LocationRepository repository, CommitmentService commitmentService) {
		this.repository = repository;
		this.commitmentService = commitmentService;
	}

	public List<Location> findAll() {
		return repository.findAll();
	}

	public Location findById(Long id) {
		Optional<Location> result = repository.findById(id);
		if (result.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Localização Não Encontrada");
		}
		return result.get();
	}

	public Location save(Location location) {
		return repository.save(location);
	}

	public Location update(Location location, Long id) {
		Optional<Location> result = repository.findById(id);
		if (result.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Localização Não Encontrada");
		}
		location.setId(id);
		return repository.save(location);
	}

	public void delete(Long id) {
		List<Commitment> commitments = commitmentService.findAll();
		if(commitments.stream().anyMatch(commitment ->  commitment.getLocation().getId().equals(id))){
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Localização vInculada ao Compromisso ");
		}
		repository.deleteById(id);
	}

}
