package com.Algosoft.desafioapi.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.Algosoft.desafioapi.enums.Situation;
import com.Algosoft.desafioapi.model.History;
import com.Algosoft.desafioapi.model.Participant;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.Algosoft.desafioapi.model.Commitment;
import com.Algosoft.desafioapi.repository.CommitmentRepository;

@Service
public class CommitmentService {

	final CommitmentRepository repository;

	final HistoryService historyService;

	public CommitmentService(CommitmentRepository repository, HistoryService historyService) {
		this.repository = repository;
		this.historyService = historyService;
	}

	public List<Commitment> findAll() {
		return repository.findAll();
	}

	public List<Commitment> findByParticipant(Long id) {
		return repository.findAll().stream().filter(commitment -> commitment.getParticipants().stream().anyMatch(participant -> participant.getId().equals(id))).collect(Collectors.toList());
	}
	public List<Commitment> findByParticipantAndSituation(Long id, String situation) {
		return repository.findAll().stream().filter(commitment -> commitment.getParticipants().stream().anyMatch(participant -> participant.getId().equals(id))).filter(commitment -> commitment.getSituation().toString().equals(situation)).collect(Collectors.toList());
	}

	public Commitment findById(Long id) {
		Optional<Commitment> result = repository.findById(id);
		if (result.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Compromisso Não Encontrado");
		}
		return result.get();
	}

	public Commitment save(Commitment commitment) {
		List<Commitment> validation = findAllByParticipants(commitment.getParticipants()).stream().filter(commitment1 -> commitment1.getSituation().equals(Situation.PENDING)).collect(Collectors.toList());
		if (validation.stream().anyMatch(commitment1 -> commitment1.getDate().toLocalDate().isEqual(commitment.getDate().toLocalDate()))) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Participante ja Possui um Compromisso de Mesma Data");
		}
		return repository.save(commitment);
	}

	public Commitment update(Commitment commitment, Long id) {
		Optional<Commitment> result = repository.findById(id);
		if (result.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Compromisso Não Encontrado");
			}
		if(result.get().getSituation() != Situation.PENDING){
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Situação invalida");
			}

		History history = new History();
		commitment.setId(id);
		repository.save(commitment);
		history.setCommitment(commitment);
		history.setDate(LocalDateTime.now());
		history.setSituation(commitment.getSituation());
		historyService.save(history);
		return commitment;
	}

	public void delete(Long id) {
		Optional<Commitment> result = repository.findById(id);
		if (result.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Compromisso Não Encontrado");
		}
		if (result.get().getSituation() != Situation.PENDING){
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Situação Invalida");
		}
		repository.deleteById(id);
	}

	public List<Commitment> findAllByParticipants(List<Participant> participants) {
		return repository.findAll().stream().filter(commitment -> commitment.getParticipants().stream().anyMatch(participant -> participants.stream().anyMatch(participant1 -> participant1.getId().equals(participant.getId())))).collect(Collectors.toList());
	}
}
