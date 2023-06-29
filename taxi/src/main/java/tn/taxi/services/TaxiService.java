package tn.taxi.services;

import java.util.List;

import tn.taxi.entities.Taxi;


public interface TaxiService {
	
	Taxi addTaxi (Taxi t);
	void deleteTaxi (Long id);
	public List<Taxi> search(String keyword);
	List<Long> getAllNumTaxi();

}
