package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.Studente;
import com.example.demo.repository.StudenteRepository;

@Service
public class StudenteService {
	
	@Autowired
	StudenteRepository studenteRepository;
	

	public boolean alreadyExists(Studente s) {
		return this.findAllStudente().contains(s);
	}
	
	@Transactional
	public Studente inserisci(Studente stud) {
		return this.studenteRepository.save(stud);
	}
	
	@Transactional
	public void rimuovi(Long id) {
		this.studenteRepository.deleteById(id);
	}
	
	@Transactional
	public void clear() {
		this.studenteRepository.deleteAll();
	}
	
	public Studente searchById(Long id) {
		return this.studenteRepository.findById(id).get();
	}
	
	public List<Studente> findAllStudente(){
		List<Studente> elencoStud = new ArrayList<Studente>();
		for (Studente s : this.studenteRepository.findAll()) {
			elencoStud.add(s);
		}
		return elencoStud;
	}
}

