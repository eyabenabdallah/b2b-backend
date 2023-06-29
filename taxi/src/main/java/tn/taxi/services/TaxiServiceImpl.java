package tn.taxi.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import tn.taxi.entities.Log;
import tn.taxi.entities.Role;
import tn.taxi.entities.Taxi;
import tn.taxi.repositories.LogRepository;
import tn.taxi.repositories.TaxiRepository;

@Service
public class TaxiServiceImpl implements TaxiService {

	@Autowired
	TaxiRepository taxiRepository;
	@Autowired
	LogRepository logRepository;
	BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
	

	@Override
	@Transactional
	public Taxi addTaxi(Taxi t) {
		Log log= saveLog(t);
		t.setLog(log);
		taxiRepository.save(t);
		return t;
	}
	
	private Log saveLog(Taxi t){
		Log log=t.getLog();
		log.setPassword(bCryptPasswordEncoder.encode(t.getLog().getPassword()));
		log.setRole(Role.TAXI);
		logRepository.save(log);
		return log;
	}

	@Override
	public void deleteTaxi(Long id) {
		taxiRepository.deleteById(id);
		
	}


	@Override
	public List<Taxi> search(String keyword) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List<Long> getAllNumTaxi(){
		return taxiRepository.findAllNumTaxi();
	}


}
