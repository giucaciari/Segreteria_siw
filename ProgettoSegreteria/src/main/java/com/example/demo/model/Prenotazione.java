package com.example.demo.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;

@Entity
public class Prenotazione {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long Id;
	
	@NotBlank
	private String nome;
	
	@ManyToOne
	private Studente studente;
	@OneToOne
	private Esame esame;
	
	
	public Studente getStudente() {
		return studente;
	}
	public void setStudente(Studente studente) {
		this.studente = studente;
	}
	public Esame getEsame() {
		return this.esame;
	}
	public void setEsame(Esame esame) {
		 this.esame=esame;
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
	
	
	@Override
	public boolean equals(Object obj) {
		Prenotazione p = (Prenotazione)obj;
		return this.nome.equals(p.getNome());
	}
	

}