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

import com.example.demo.controller.validator.CertificatoValidator;
import com.example.demo.model.Certificato;
import com.example.demo.model.Esame;
import com.example.demo.service.CertificatoService;
import com.example.demo.service.EsameService;


@Controller
public class CertificatoController {
	
	@Autowired
	CertificatoService certificatoService;
	@Autowired
	CertificatoValidator certificatoValidator;
	@Autowired
	EsameService esameService;
	
	//aggiunta del certificato nel modello
	@PostMapping("/admin/certificato")
	public String addIngrediente(@Valid @ModelAttribute("certificato") Certificato c, BindingResult bindingResult, @RequestParam(name = "EsameScelto") Long id, Model model) {
		
		this.certificatoValidator.validate(c, bindingResult);
		
		if (!bindingResult.hasErrors()) {
			Esame e = this.esameService.searchById(id);
			c.setEsame(e);
			e.getCertificati().add(c);
			this.esameService.inserisci(e);
			model.addAttribute("certificato", c);
			return "certificato.html";

		} 
		model.addAttribute("certificato", c);
		return "certificatoForm.html";
		
	}

	// Richiede tutti i certificati
	@GetMapping("/elencoCertificati")
	public String getAllCertificati(Model model) {
		List<Certificato> elencoc = this.certificatoService.findAllCertificati();
		model.addAttribute("elencoCertificati", elencoc);
		return "elencoCertificati.html";
	}

	//creazione di un nuovo ingrediente e ritorno del form
	@GetMapping("/admin/certificatoForm")
	public String getcertificatoForm(Model model) {
		model.addAttribute("certificato", new Certificato());
		model.addAttribute("esamiDisponibili", this.esameService.findAllEsami());
		return "certificatoForm.html";
	}

	//torna la pagina dedicata al certificato con il determinato id
	@GetMapping("/certificato/{id}")
	public String getCertificato(@PathVariable("id") Long id, Model model){
		Certificato certificato = this.certificatoService.searchById(id);
		model.addAttribute("certificato", certificato);
		return "certificato.html";
	}

	//cancellazione certificato
	@GetMapping("/deleteCertificato")
	public String deleteCertificato(@RequestParam Long certiId) {
		this.certificatoService.rimuovi(certiId);
		return "redirect:/elencoCertificati";
	}
	
	//aggiornamento del certificato tramite form
	@GetMapping("/admin/updateCertificato")
    public String updateIngredienteForm(@RequestParam Long certiId, Model model) {
        System.out.println("L'id del certificato: " + certiId);
        model.addAttribute("certificato", this.certificatoService.searchById(certiId));
        return "certificatoUpdateForm.html";
    }

	//modifica effettiva del certificato con il determinato id
	@PostMapping("/certificatoUpdate/{id}")
    public String updateCertificato(@Valid @ModelAttribute("certificato") Certificato certificato, BindingResult bindingResult, Model model) {
        this.certificatoValidator.validate(certificato, bindingResult);
        if(!bindingResult.hasErrors()) {
            this.certificatoService.inserisci(certificato);
            model.addAttribute("certificato", certificato);
            return "certificato.html";
        }
        else {
            return "certificatoUpdateForm.html";
        }
    }

}