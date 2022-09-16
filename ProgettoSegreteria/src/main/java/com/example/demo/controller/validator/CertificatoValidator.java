package com.example.demo.controller.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.example.demo.model.Certificato;
import com.example.demo.service.CertificatoService;

@Component
public class CertificatoValidator implements Validator {

	@Autowired
	CertificatoService certificatoService;

	@Override
	public void validate(Object o, Errors errors) {
		if (this.certificatoService.alreadyExists((Certificato) o)) {
			errors.reject("certificato.duplicato");
		}
	}

	@Override
	public boolean supports(Class<?> aClass) {
		return Certificato.class.equals(aClass);
	}

}
