package com.example.demo.controller.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.example.demo.model.Prenotazione;
import com.example.demo.service.PrenotazioneService;

@Component
public class PrenotazioneValidator implements Validator {

	@Autowired
	PrenotazioneService prenotazioneService;

	@Override
	public void validate(Object o, Errors errors) {
		if (this.prenotazioneService.alreadyExists((Prenotazione) o)) {
			errors.reject("prenotazione.duplicato");
		}
	}

	@Override
	public boolean supports(Class<?> aClass) {
		return Prenotazione.class.equals(aClass);
	}

}
