package com.example.demo.service;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.Prenotazione;
import com.example.demo.repository.PrenotazioneRepository;

@Service
public class PrenotazioneService {
	
	@Autowired
	PrenotazioneRepository prenotazioneRepository;
	

	public boolean alreadyExists(Prenotazione p) {
		return this.findAllPrenotazioni().contains(p);
	}
	
	@Transactional
	public Prenotazione inserisci(Prenotazione pre) {
		return this.prenotazioneRepository.save(pre);
	}
	
	@Transactional
	public void rimuovi(Long id) {
		this.prenotazioneRepository.deleteById(id);
	}
	
	@Transactional
	public void clear() {
		this.prenotazioneRepository.deleteAll();
	}
	
	public Prenotazione searchById(Long id) {
		return this.prenotazioneRepository.findById(id).get();
	}
	
	public List<Prenotazione> findAllPrenotazioni(){
		List<Prenotazione> elencoPre = new ArrayList<Prenotazione>();
		for (Prenotazione p : this.prenotazioneRepository.findAll()) {
			elencoPre.add(p);
		}
		return elencoPre;
	}
}
	
	
	
	
