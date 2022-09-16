package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.Esame;
import com.example.demo.model.Certificato;
import com.example.demo.repository.EsameRepository;

@Service
public class EsameService {
	
	@Autowired 
	EsameRepository esameRepository;
	
	@Autowired
	CertificatoService cs;
	
	
	public boolean alreadyExists(Esame e) {
		return this.findAllEsami().contains(e);
	}
	
	@Transactional
	public Esame inserisci(Esame e) {
		return this.esameRepository.save(e);
	}
	
	public void inserisciPiattoIngrediente(Certificato c) {
		this.cs.inserisci(c);
		
	}
	
	@Transactional
	public void rimuovi(Long Id) {
		this.esameRepository.deleteById(Id);
	}
	
	@Transactional
	public void clear() {
		this.esameRepository.deleteAll();
	}
	
	public Esame searchById(Long id) {
		return this.esameRepository.findById(id).get();
	}
	
	public Esame searchByNome(String nome) {
		return this.esameRepository.findByNome(nome);
	}
	
	public List<Esame> findAllEsami(){
		List<Esame> elencoes = new ArrayList<Esame>();
		for (Esame e : this.esameRepository.findAll()) {
			elencoes.add(e);
		}
		return elencoes;
	}
}
