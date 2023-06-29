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
public class LogServiceImpl implements LogService {

	@Autowired
	LogRepository logRepository;
	@Autowired
	TaxiRepository taxiRepository;
	BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

	
	@Override
	public Log findLogByLogUsername(String username) {
		return logRepository.findByUsername(username);
	}

	@Override
	public List<Log> retrieveAllAgents() {
		return (List<Log>) logRepository.findAgents();
	}
	
	@Override
	public List<Log> retrieveAllTaxis() {
		return (List<Log>) logRepository.findTaxis();
	}

	@Override
	@Transactional
	public Log addLog(Log l) {
		l.setPassword(bCryptPasswordEncoder.encode(l.getPassword()));
		l.setActive(true);
		if(taxiRepository.findNull()!=null) {
		taxiRepository.deleteById(taxiRepository.findNull());
		}
		if(l.getRole()==Role.TAXI) {
			Taxi taxi= l.getTaxi();
			taxi.setLog(l);
			taxiRepository.save(taxi);
		}
		logRepository.save(l);
		return l;
	}

	@Override
	public void fireAgent(Long id) {
		Log log = logRepository.findById(id).orElse(null);
		if(log.getActive()==true)
			log.setActive(false);
		else
			log.setActive(true);
		logRepository.save(log);
	}

	@Override
	public List<Log> search(String keyword) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Log updateUser(Log user) {
		if(user.getRole()==Role.TAXI) {
			Taxi taxi= user.getTaxi();
			taxi.setLog(user);
			taxiRepository.save(taxi);
		}
		logRepository.save(user);
		return user;
	}

	@Override
	public Log retrieveLogByCourse(Long numCourse) {
		return logRepository.retrieveLogByCourse(numCourse);
	}
}
