package tn.taxi.services;

import java.util.List;

import tn.taxi.entities.Log;
import tn.taxi.entities.Taxi;

public interface LogService {

	Log findLogByLogUsername(String username);
	List<Log> retrieveAllAgents();
	List<Log> retrieveAllTaxis();
	Log addLog (Log l);
	void fireAgent (Long id);
	public List<Log> search(String keyword);
	Log updateUser(Log user);
	Log retrieveLogByCourse(Long numCourse);

}
