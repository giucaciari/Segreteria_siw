package com.example.demo.controller.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.example.demo.model.Studente;
import com.example.demo.service.StudenteService;
@Component
public class StudenteValidator implements Validator {

	@Autowired
	StudenteService studenteService;

	@Override
	public void validate(Object o, Errors errors) {
		if (this.studenteService.alreadyExists((Studente) o)) {
			errors.reject("studente.duplicato");
		}
	}

	@Override
	public boolean supports(Class<?> aClass) {
		return Studente.class.equals(aClass);
	}

}
