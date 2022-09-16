package com.example.demo.service;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.Certificato;
import com.example.demo.repository.CertificatoRepository;

@Service
public class CertificatoService {
	
	@Autowired
	CertificatoRepository certificatoRepository;
	

	public boolean alreadyExists(Certificato c) {
		return this.findAllCertificati().contains(c);
	}
	
	@Transactional
	public Certificato inserisci(Certificato c) {
		return this.certificatoRepository.save(c);
	}
	
	@Transactional
	public void rimuovi(Long Id) {
		this.certificatoRepository.deleteById(Id);
	}
	
	@Transactional
	public void clear() {
		this.certificatoRepository.deleteAll();
	}
	
	public Certificato searchById(Long id) {
		return this.certificatoRepository.findById(id).get();
	}
	
	public Certificato searchByNome(String nome) {
		return this.certificatoRepository.findByNome(nome);
	}
	
	public List<Certificato> findAllCertificati(){
		List<Certificato> elencocer = new ArrayList<Certificato>();
		for (Certificato c : this.certificatoRepository.findAll()) {
			elencocer.add(c);
		}
		return elencocer;
	}
}
