package tn.taxi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


import tn.taxi.entities.Taxi;
import tn.taxi.services.TaxiService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/taxi")
public class TaxiController {

	@Autowired
	TaxiService taxiService;
	
	// http://localhost:8083/city/taxi/retrieve-all-taxis
		@GetMapping("/retrieve-all-taxis")
		@ResponseBody
		public List<Taxi> getTaxis(String keyword) {
		List<Taxi> listTaxis = taxiService.search(keyword);
		return listTaxis;
		}
		
		// http://localhost:8083/city/taxi/add-taxi
		@PostMapping("/add-taxi")
		@ResponseBody
		public Taxi addTaxi(@RequestBody Taxi o)
		{
		Taxi taxi = taxiService.addTaxi(o);
		return taxi;
		}
		
		// http://localhost:8083/city/taxi/remove-taxi/{taxi-id}
		@DeleteMapping("/remove-taxi/{taxi-id}")
		@ResponseBody
		public void removeTaxi(@PathVariable("taxi-id") Long taxiId) {
		taxiService.deleteTaxi(taxiId);
		}
		
		@GetMapping("/retrieve-num-taxi")
		public List<Long> getAllNumTaxi(){
			return taxiService.getAllNumTaxi();
		}

}
