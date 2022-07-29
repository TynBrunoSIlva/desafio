package com.Algosoft.desafioapi.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.Algosoft.desafioapi.model.History;
import com.Algosoft.desafioapi.repository.HistoryRepository;

@Service
public class HistoryService {

    final HistoryRepository repository;

    public HistoryService(HistoryRepository repository) {
        this.repository = repository;
    }

    public List<History> findAll() {
        return repository.findAll();
    }

    public History save(History history) {
        return repository.save(history);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public List<History> findAllByCommitment(Long id) {
        return repository.findAll().stream().filter(history -> history.getCommitment().getId().equals(id)).collect(Collectors.toList());
    }
}
