package tn.taxi.services;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.taxi.entities.Client;
import tn.taxi.entities.Course;
import tn.taxi.entities.Log;
import tn.taxi.entities.Societe;
import tn.taxi.repositories.ClientRepository;
import tn.taxi.repositories.SocieteRepository;

@Service
public class SocieteServiceImpl implements SocieteService{
	
	@Autowired
	SocieteRepository societeRepository;
	@Autowired
	ClientRepository clientRepository;

	@Override
	public Societe addSociete(Societe societe) {
		societeRepository.save(societe);
		return societe;
	}
	
	@Override
	public List<Societe> retrieveAllSocietes() {
	    return (List<Societe>) societeRepository.findAll();
	}
	@Override
	public void deleteSociete(Long id) {
		societeRepository.deleteById(id);	
	}

	@Override
	public Societe updateSociete(Societe c) {
		Societe existingSociete = societeRepository.findById(c.getIdSociete()).orElse(null);
	    Set<Client> existingClients = existingSociete.getClients();
	    c.setClients(existingClients);
	    Societe updatedSociete = societeRepository.save(c);
	    
	    // Update the association between the course and clients
	    Set<Client> updatedClients = existingSociete.getClients();
	    for (Client client : updatedClients) {
	        client.setSociete(updatedSociete);;
	        clientRepository.save(client);
	    }
	    
		return updatedSociete;
	}

	@Override
	public List<Societe> retrieveSocieteByName(String nom) {
		return societeRepository.retrieveSocieteByName(nom);
	}

}
