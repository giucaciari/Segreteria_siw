package com.example.demo.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.controller.validator.StudenteValidator;
import com.example.demo.model.Studente;
import com.example.demo.service.StudenteService;

@Controller
public class StudenteController {
	
	@Autowired
	StudenteService studenteService;
	@Autowired
	StudenteValidator studenteValidator;
	
	
	//aggiunta dello studente nel modello
	@PostMapping("/admin/studente")
	public String addStudente(@Valid @ModelAttribute("studente") Studente s, BindingResult bindingResult, Model model) {
		this.studenteValidator.validate(s, bindingResult);
		if (!bindingResult.hasErrors()) {
			this.studenteService.inserisci(s);
			model.addAttribute("studente", this.studenteService.searchById(s.getId()));
			model.addAttribute("elencoPrenotazioni", s.getPrenotazioniStudenti());
			return "studente.html";

		} else {
			return "studenteForm.html";
		}
	}

	// Richiede tutti gli studente
	@GetMapping("/elencoStudenti")
	public String getAllStudente(Model model) {
		List<Studente> elencoStudenti = this.studenteService.findAllStudente();
		model.addAttribute("elencoStudenti", elencoStudenti);
		return "elencoStudenti.html";
	}

	//creazione di un nuovo studente e ritorno del form
	@GetMapping("/admin/studenteForm")
	public String getStudenteForm(Model model) {
		model.addAttribute("studente", new Studente());
		return "studenteForm.html";
	}
	
	//torna la pagina dedicata allo studente con il determinato id
	@GetMapping("/studente/{id}")
	public String getStudente(@PathVariable("id") Long id, Model model){
		Studente s = this.studenteService.searchById(id);
		model.addAttribute("studente", s);
		model.addAttribute("elencoPrenotazioni", s.getPrenotazioniStudenti());
		return "studente.html";
	}

	//cancellazione studente
	@GetMapping("/deletestudente")
	public String deleteStudente(@RequestParam Long studId) {
		this.studenteService.rimuovi(studId);
		return "redirect:/elencoStudenti";
	}
	
	//aggiornamento dello studente tramite form
	@GetMapping("/admin/updateStudente")
    public String updateStudenteForm(@RequestParam Long studId, Model model) {
        System.out.println("L'id dello studente: " + studId);
        model.addAttribute("studente", this.studenteService.searchById(studId));
        return "studenteUpdateForm.html";
    }

	//modifica effettiva dello studente con il determinato id
	@PostMapping("/studenteUpdate/{id}")
    public String updateStudente(@Valid @ModelAttribute("studente") Studente s, BindingResult bindingResult, Model model) {
        this.studenteValidator.validate(s, bindingResult);
        if(!bindingResult.hasErrors()) {
            this.studenteService.inserisci(s);
            model.addAttribute("studente", s);
            return "studente.html";
        }
        else {
            return "studenteUpdateForm.html";
        }
    }
	
}
