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

import com.example.demo.controller.validator.EsameValidator;
import com.example.demo.model.Prenotazione;
import com.example.demo.model.Esame;
import com.example.demo.service.PrenotazioneService;
import com.example.demo.service.CertificatoService;
import com.example.demo.service.EsameService;

@Controller
public class EsameController {

	@Autowired
	EsameService esameService;
	
	@Autowired 
	EsameValidator esameValidator;
	
	@Autowired
	PrenotazioneService prenotazioneService;
	
	@Autowired
	CertificatoService certificatoService;
	
	
	//aggiunta dell'esame nel modello
	@PostMapping("/admin/esame")
	public String addEsame(@Valid @ModelAttribute("esame") Esame e, BindingResult bindingResult, @RequestParam(name = "PrenotazioneScelta") Long id, Model model) {
		
		this.esameValidator.validate(e, bindingResult);
		
		
		if (!bindingResult.hasErrors()) {
			Prenotazione pre = this.prenotazioneService.searchById(id);
			e.setPrenotazione(pre);
			pre.setEsame(e);
			this.prenotazioneService.inserisci(pre);
			model.addAttribute("esame", e);
			model.addAttribute("elencoCertificazioni",e.getCertificati());
			return "esame.html";
		} 
		
		model.addAttribute("esame", e);
		return "esameForm.html";
	}

	// Richiede tutti gli esami
	@GetMapping("/elencoEsami")
	public String getAllEsami(Model model) {
		List<Esame> elencoEsami = this.esameService.findAllEsami();
		model.addAttribute("elencoEsami", elencoEsami);
		return "elencoEsami.html";
	}

	//creazione di un nuovo esame e ritorno del form
	@GetMapping("/admin/esameForm")
	public String getEsameForm(Model model) {
		model.addAttribute("esame", new Esame());
        model.addAttribute("prenotazioniDisponibili", this.prenotazioneService.findAllPrenotazioni());
		return "esameForm.html";
	}

	//torna la pagina dedicata all'esame con il determinato id
	@GetMapping("/esame/{id}")
	public String getPiatto(@PathVariable("id") Long id, Model model){
		Esame esame = this.esameService.searchById(id);
		model.addAttribute("esame", esame);
		model.addAttribute("elencoCertificati", esame.getCertificati());
		return "esame.html";
	}

	//cancellazione esame
	@GetMapping("/deleteEsame")
	public String deleteEsame(@RequestParam Long esameId) {
		this.esameService.rimuovi(esameId);
		return "redirect:/elencoEsami";
	}
	
	//aggiornamento dell'esame tramite form
	@GetMapping("/admin/updateEsame")
    public String updatePiattoForm(@RequestParam Long esameId, Model model) {
        System.out.println("L'id dell'esame: " + esameId);
        model.addAttribute("esame", this.esameService.searchById(esameId));
        model.addAttribute("prenotazioneDisponibile", this.prenotazioneService.findAllPrenotazioni());
        return "esameUpdateForm.html";
    }

	//modifica effettiva dell'esame con il determinato id
	@PostMapping("/esameUpdate/{id}")
    public String updateEsame(@Valid @ModelAttribute("esame") Esame esame, BindingResult bindingResult, Model model) {
        this.esameValidator.validate(esame, bindingResult);
        if(!bindingResult.hasErrors()) {
            this.esameService.inserisci(esame);
            model.addAttribute("esame", esame);
            model.addAttribute("PrenotazioneDisponibili", this.prenotazioneService.findAllPrenotazioni());
            return "esame.html";
        }
        else {
            return "esameUpdateForm.html";
        }
    }
	
}

