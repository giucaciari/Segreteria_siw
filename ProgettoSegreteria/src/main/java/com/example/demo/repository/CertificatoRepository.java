package com.example.demo.repository;
import org.springframework.data.repository.CrudRepository;

import com.example.demo.model.Certificato;

public interface CertificatoRepository extends CrudRepository<Certificato,Long> {
	
	public Certificato findByNome(String s);

	

}

