package com.example.demo.repository;
import org.springframework.data.repository.CrudRepository;

import com.example.demo.model.Esame;

public interface EsameRepository extends CrudRepository<Esame,Long> {
	
	public Esame findByNome(String s);

}
