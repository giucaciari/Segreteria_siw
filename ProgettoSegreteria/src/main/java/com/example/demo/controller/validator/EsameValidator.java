package com.example.demo.controller.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.example.demo.model.Esame;
import com.example.demo.service.EsameService;

@Component
public class EsameValidator implements Validator {

	@Autowired
	EsameService esameService;

	@Override
	public void validate(Object o, Errors errors) {
		if (this.esameService.alreadyExists((Esame) o)) {
			errors.reject("esame.duplicato");
		}
	}

	@Override
	public boolean supports(Class<?> aClass) {
		return Esame.class.equals(aClass);
	}

}
