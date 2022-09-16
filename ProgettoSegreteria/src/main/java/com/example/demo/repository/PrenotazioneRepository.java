package com.example.demo.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.model.Prenotazione;

public interface PrenotazioneRepository extends CrudRepository<Prenotazione,Long> {
	
	public Prenotazione findByNome(String s);

}

