package tn.taxi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.taxi.entities.Client;
import tn.taxi.entities.Course;
import tn.taxi.entities.Taxi;
import tn.taxi.services.ClientService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/client")
public class ClientController {

	@Autowired
	ClientService clientService;
	
	@GetMapping("/retrieve-all-clients")
    public List<Client> getTodaysCourses() {
        return clientService.retrieveAllClients();
    }
	
	@PostMapping("/add-client")
	@ResponseBody
	public Client addClient(@RequestBody Client c)
	{
	Client client = clientService.addClient(c);
	return client;
	}
	
	@GetMapping("/names")
    public List<String> getAllClientNames() {
        return clientService.getAllClientNames();
    }
	
	@GetMapping("/retrieve-clients/{nomSociete}")
	public ResponseEntity<List<Client>> retrieveClientsBySociete(@PathVariable String nomSociete) {
	    List<Client> clients = clientService.retrieveClientsBySociete(nomSociete);
	    return ResponseEntity.ok().body(clients);
	}
	
	@DeleteMapping("/remove-client/{client-id}")
	@ResponseBody
	public void removeClient(@PathVariable("client-id") Long clientId) {
	clientService.deleteClient(clientId);
	}
	
	@GetMapping("/get-clients/{numCourse}")
	public ResponseEntity<List<Client>> retrieveClientsBySociete(@PathVariable Long numCourse) {
	    List<Client> clients = clientService.retrieveClientsByCourse(numCourse);
	    return ResponseEntity.ok().body(clients);
	}
	
}
	

