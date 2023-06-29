package tn.taxi.services;

import java.util.List;

import org.springframework.stereotype.Service;

import tn.taxi.entities.Societe;

@Service
public interface SocieteService {
	
	List<Societe> retrieveAllSocietes();
	Societe addSociete (Societe s);
	void deleteSociete (Long id);
	Societe updateSociete (Societe s);
	List<Societe> retrieveSocieteByName(String nom);

}
