package tn.taxi.services;


import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.taxi.entities.Client;
import tn.taxi.entities.Societe;
import tn.taxi.entities.Taxi;
import tn.taxi.entities.Client;
import tn.taxi.repositories.ClientRepository;
import tn.taxi.repositories.SocieteRepository;

@Service
public class ClientServiceImpl implements ClientService {

	@Autowired
	ClientRepository clientRepository;
	@Autowired
	SocieteRepository societeRepository;
	

	@Override
	public Client addClient(Client client) {
		Long societeId = client.getSociete().getIdSociete();
		  Societe existingSociete = societeRepository.findById(societeId).orElse(null);
		  client.setSociete(existingSociete);
		  clientRepository.save(client);
		  return client;
	}
	
	@Override
	public List<Client> retrieveAllClients() {
	    return (List<Client>) clientRepository.findAll();
	}
	@Override
	public void deleteClient(Long id) {
		clientRepository.deleteById(id);	
	}

	@Override
	public Client updateClient(Client c) {
		clientRepository.save(c);
		return c;
	}
	
	@Override
	public List<String> getAllClientNames() {
        return clientRepository.getFullNames();
    }

	@Override
	public List<Client> retrieveClientsBySociete(String nomSociete) {
		return clientRepository.retrieveClientsBySociete(nomSociete);
	}

	@Override
	public List<Client> retrieveClientsByCourse(Long numCourse) {
		List<Client> clients =clientRepository.retrieveClientsByCourse(numCourse);
		return clients;
	}
	
	
}
