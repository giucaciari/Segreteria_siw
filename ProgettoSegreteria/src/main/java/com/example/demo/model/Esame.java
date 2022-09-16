package com.example.demo.model;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Esame {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long Id;
	
	
	//senza cascade all dà problemi nella creazione dell'oggetto stesso
	@OneToMany( cascade = {CascadeType.ALL}, mappedBy ="esame")
	private List<Certificato> certificati;
	
	private String nome;
	
	private String certificato;
	
	private String aula;
	private String descr;
	
	@OneToOne( cascade = {CascadeType.ALL})
	private Prenotazione prenotazione;
	
	
	public Prenotazione getPrenotazione() {
		return prenotazione;
	}
	public void setPrenotazione(Prenotazione prenotazione) {
		this.prenotazione = prenotazione;
	}
	public Long getId() {
		return Id;
	}
	public List<Certificato> getCertificati() {
		return certificati;
	}
	public void setCertificati(List<Certificato> certificati) {
		this.certificati = certificati;
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
	public String getDescr() {
		return descr;
	}
	public void setDescr(String descr) {
		this.descr = descr;
	}
	public String getAula() {
		return aula;
	}
	public void setAula(String aula) {
		this.aula= aula;
	}
	public String getCertificato() {
		return certificato;
	}
	public void setCertificato(String certificato) {
		this.certificato= certificato;
	}

}
