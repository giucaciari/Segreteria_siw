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

import com.example.demo.controller.validator.PrenotazioneValidator;
import com.example.demo.model.Prenotazione;
import com.example.demo.model.Studente;
import com.example.demo.service.PrenotazioneService;
import com.example.demo.service.StudenteService;
import com.example.demo.service.EsameService;

@Controller
public class PrenotazioneController {
	@Autowired
	PrenotazioneService prenotazioneService;
	@Autowired
	PrenotazioneValidator prenotazioneValidator;
	@Autowired
	EsameService esameService;
	@Autowired
	StudenteService studenteService;
	

	
/*
 * *
 * 
 * aggiunta del prenotazione nel modello
 * 
 */
	@PostMapping("/admin/prenotazione")
	public String addPrenotazione(@Valid @ModelAttribute("prenotazione") Prenotazione p, BindingResult bindingResult, @RequestParam(name = "studenteScelto") Long id, Model model) {
		
		this.prenotazioneValidator.validate(p, bindingResult);
		
		if (!bindingResult.hasErrors()) {
			Studente s = this.studenteService.searchById(id);
			p.setStudente(s);
			s.getPrenotazioniStudenti().add(p);
			this.studenteService.inserisci(s);
			
			
			model.addAttribute("prenotazione", p);
			model.addAttribute("elencoEsami",p.getEsame());
			return "prenotazione.html";

		} 
		model.addAttribute("prenotazione", p);
		return "prenotazioneForm.html";
		
	}

	// Richiede tutte le prenotazioni
	@GetMapping("/elencoPrenotazioni")
	public String getAllPrenotazioni(Model model) {
		List<Prenotazione> elencop = this.prenotazioneService.findAllPrenotazioni();
		model.addAttribute("elencoPrenotazioni", elencop);
		return "elencoPrenotazioni.html";
	}


	//creazione di una nuova prenotazione e ritorno del form
	@GetMapping("/admin/prenotazioneForm")
	public String getprenotazioneForm(Model model) {
		model.addAttribute("prenotazione", new Prenotazione());
		model.addAttribute("studenteDisponibili", this.studenteService.findAllStudente());
		return "prenotazioneForm.html";
	}

	
	//torna la pagina dedicata alla prenotazione con il determinato id
	@GetMapping("/prenotazione/{id}")
	public String getprenotazione(@PathVariable("id") Long id, Model model){
		Prenotazione pre = this.prenotazioneService.searchById(id);
		model.addAttribute("prenotazione", pre);
		model.addAttribute("elencoEsami", pre.getEsame());
		return "prenotazione.html";
	}

	//cancellazione prenotazione
	@GetMapping("/deleteprenotazione")
	public String deletePrenotazione(@RequestParam Long preId) {
		this.prenotazioneService.rimuovi(preId);
		return "redirect:/elencoPrenotazioni";
	}

	//aggiornamento della prenotazione tramite form
	@GetMapping("/admin/updatePrenotazione")
    public String updatePrenotazioneForm(@RequestParam Long preId, Model model) {
        System.out.println("L'id della prenotazione: " + preId);
        model.addAttribute("prenotazione", this.prenotazioneService.searchById(preId));
        model.addAttribute("StudenteDisponibili", this.studenteService.findAllStudente());
        return "prenotazioneUpdateForm.html";
    }

	//modifica effettiva della prenotazione con il determinato id
	@PostMapping("/prenotazioneUpdate/{id}")
    public String updatePrenotazione(@Valid @ModelAttribute("prenotazione") Prenotazione pre, BindingResult bindingResult, Model model) {
        this.prenotazioneValidator.validate(pre, bindingResult);
        if(!bindingResult.hasErrors()) {
            this.prenotazioneService.inserisci(pre);
            model.addAttribute("prenotazione", pre);
            model.addAttribute("StudentiDisponibili", this.studenteService.findAllStudente());
            return "prenotazione.html";
        }
        else {
            return "prenotazioneUpdateForm.html";
        }
    }
	




}
