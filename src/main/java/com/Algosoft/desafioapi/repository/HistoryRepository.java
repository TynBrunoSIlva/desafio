package com.Algosoft.desafioapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Algosoft.desafioapi.model.History;

@Repository
public interface HistoryRepository extends JpaRepository<History, Long> {

}
