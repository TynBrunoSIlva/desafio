package com.Algosoft.desafioapi.model;

import com.Algosoft.desafioapi.enums.Situation;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import javax.persistence.*;

@Entity
public class History {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	private Commitment commitment;

	private Situation situation;

	private LocalDateTime date = LocalDateTime.now();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Commitment getCommitment() {
		return commitment;
	}

	public void setCommitment(Commitment commitment) {
		this.commitment = commitment;
	}

	public Situation getSituation() {
		return situation;
	}

	public void setSituation(Situation situation) {
		this.situation = situation;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}
}
