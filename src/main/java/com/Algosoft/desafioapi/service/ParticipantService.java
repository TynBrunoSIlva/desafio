package com.Algosoft.desafioapi.service;

import java.util.List;
import java.util.Optional;

import com.Algosoft.desafioapi.model.Commitment;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.Algosoft.desafioapi.model.Participant;
import com.Algosoft.desafioapi.repository.ParticipantRepository;

@Service
public class ParticipantService {

    final ParticipantRepository repository;

    final CommitmentService commitmentService;

    public ParticipantService(ParticipantRepository repository, CommitmentService commitmentService) {
        this.repository = repository;
        this.commitmentService = commitmentService;
    }

    public List<Participant> findAll() {
        return repository.findAll();
    }

    public Participant findById(Long id) {
        Optional<Participant> result = repository.findById(id);
        if (result.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Participante não Encontrado");
        }
        return result.get();
    }

    public Participant save(Participant participant) {
        return repository.save(participant);
    }

    public Participant update(Participant participant, Long id) {
        Optional<Participant> result = repository.findById(id);
        if (result.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Participante Não Encontrado");
        }
        participant.setId(id);
        return repository.save(participant);
    }

    public void delete(Long id) {
        Optional<Participant> result = repository.findById(id);
        if (result.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Participante Não Encontrado");
        }
        List<Commitment> commitments = commitmentService.findAll();
        if (commitments.stream().anyMatch(commitment -> commitment.getParticipants().stream().anyMatch(participant -> participant.getId().equals(id)))) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Participante esta vinculado a um Compromisso");
        }
        repository.deleteById(id);
    }
}
