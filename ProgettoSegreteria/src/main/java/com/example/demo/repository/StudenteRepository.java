package com.example.demo.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.model.Studente;

public interface StudenteRepository extends CrudRepository<Studente,Long> {
	
	public Studente findByNomeAndCognome(String n, String c);

}
