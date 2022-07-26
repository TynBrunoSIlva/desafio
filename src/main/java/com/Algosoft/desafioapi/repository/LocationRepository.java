package com.Algosoft.desafioapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Algosoft.desafioapi.model.Location;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {

}
