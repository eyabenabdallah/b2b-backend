package tn.taxi.services;

import java.util.List;

import org.springframework.stereotype.Service;

import tn.taxi.entities.Client;
import tn.taxi.entities.Client;

public interface ClientService {
	
	List<Client> retrieveAllClients();
	Client addClient (Client c);
	void deleteClient (Long id);
	Client updateClient (Client c);
	List<String> getAllClientNames();
	List<Client> retrieveClientsBySociete(String nomSociete);
	List<Client> retrieveClientsByCourse(Long numCourse);

}
