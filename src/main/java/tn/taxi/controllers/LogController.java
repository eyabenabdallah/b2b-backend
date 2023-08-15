package tn.taxi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.taxi.entities.Log;
import tn.taxi.repositories.LogRepository;
import tn.taxi.services.LogService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/log")
public class LogController {
	
	@Autowired
	LogService logService;
	@Autowired
	LogRepository logRepository;
	
		// http://localhost:8083/city/log/retrieve-all-taxis
		@GetMapping("/retrieve-all-taxis")
		@ResponseBody
		public List<Log> getTaxis() {
		List<Log> listTaxis = logService.retrieveAllTaxis();
		return listTaxis;
		}
		
		// http://localhost:8083/city/log/retrieve-all-taxis
		@GetMapping("/retrieve-all-agents")
		@ResponseBody
		public List<Log> getAgents() {
		List<Log> listAgents = logService.retrieveAllAgents();
		return listAgents;
		}

		
		// http://localhost:8083/city/log/add-log
		@PostMapping("/add-log")
		@ResponseBody
		public Log addLog(@RequestBody Log l)
		{
		Log log = logService.addLog(l);
		return log;
		}
		
		// http://localhost:8083/city/log/fire-agent/{agent-id}
		@PutMapping("/fire-agent/{agent-id}")
		@ResponseBody
		public void fireAgent(@PathVariable("agent-id") Long agentId) {
		logService.fireAgent(agentId);
		}

		// http://localhost:8083/city/log/retrieve-all-logs
		@GetMapping("/retrieve-all-logs")
		@ResponseBody
		public List<Log> getLogs(String keyword) {
		List<Log> listLogs = logService.search(keyword);
		return listLogs;
		}
		
		@GetMapping("/retrieve-user/{login}")
		  public   ResponseEntity<Log> getUserByLogin(@PathVariable String login) {
			  Log User = logRepository.findByUsername(login);
			   return ResponseEntity.ok().body(User);
		  }
		
		@PutMapping("/modify-user")
		@ResponseBody
		public Log modifyUser(@RequestBody Log user) {
		return logService.updateUser(user);
		}

		@GetMapping("/get-user/{numCourse}")
		  public   ResponseEntity<Log> getUserByCourse(@PathVariable Long numCourse) {
			  Log User = logService.retrieveLogByCourse(numCourse);
			   return ResponseEntity.ok().body(User);
		  }
		
}
