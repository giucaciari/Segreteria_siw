package com.example.demo.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
@Entity
public class Studente {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long Id;
	
	private String nome;
	private String cognome;
	private Long anno;
	
	@OneToMany(cascade=CascadeType.ALL, mappedBy="studente")
	private List<Prenotazione> PrenotazioneStudente;
	
	
	public List<Prenotazione> getPrenotazioniStudenti() {
		return PrenotazioneStudente;
	}
	public void setPrenotazioniStudenti(List<Prenotazione> PrenotazioneStudente) {
		this.PrenotazioneStudente= PrenotazioneStudente;
	}
	public Long getId() {
		return Id;
	}
	public void setId(Long id) {
		Id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCognome() {
		return cognome;
	}
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	public Long getAnno() {
		return this.anno;
	}
	public void setAnno(Long anno) {
		this.anno = anno;
	}
	
	
	

}
