package com.Algosoft.desafioapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Algosoft.desafioapi.model.Participant;

@Repository
public interface ParticipantRepository extends JpaRepository<Participant, Long>{
}
