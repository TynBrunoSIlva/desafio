package com.Algosoft.desafioapi.repository;

import com.Algosoft.desafioapi.enums.Situation;
import com.Algosoft.desafioapi.model.Participant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Algosoft.desafioapi.model.Commitment;

import java.util.List;

@Repository
public interface CommitmentRepository extends JpaRepository<Commitment, Long> {
}
