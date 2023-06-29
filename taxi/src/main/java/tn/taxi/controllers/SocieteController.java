package tn.taxi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.taxi.entities.Course;
import tn.taxi.entities.Societe;
import tn.taxi.services.SocieteService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/societe")
public class SocieteController {
	
	@Autowired
	SocieteService societeService;
	
	@GetMapping("/retrieve-all-societes")
    public List<Societe> getTodaysCourses() {
        return societeService.retrieveAllSocietes();
    }
	
	@PostMapping("/add-societe")
	@ResponseBody
	public Societe addSociete(@RequestBody Societe c)
	{
	Societe societe = societeService.addSociete(c);
	return societe;
	}
	
	@GetMapping("/retrieve-societes/{nom}")
    public List<Societe> retrieveSocieteByName(@RequestParam String nom) {
        return societeService.retrieveSocieteByName(nom);
    }

	@DeleteMapping("/remove-societe/{societe-id}")
	@ResponseBody
	public void removeSociete(@PathVariable("societe-id") Long societeId) {
	societeService.deleteSociete(societeId);
	}
	
	@PutMapping("/modify-societe")
	@ResponseBody
	public Societe modifySociete(@RequestBody Societe societe) {
	return societeService.updateSociete(societe);
	}
}
