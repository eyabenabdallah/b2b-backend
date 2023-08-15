package tn.taxi.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.taxi.entities.Client;
import tn.taxi.entities.Log;

@Repository
public interface LogRepository extends CrudRepository<Log, Long> {

	@Query("SELECT l FROM Log l WHERE l.username= :username")
	Log findByUsername(String username);
	
	@Query("SELECT l FROM Log l WHERE l.role= AGENT")
	List<Log> findAgents();
	
	@Query("SELECT l FROM Log l WHERE l.role= TAXI")
	List<Log> findTaxis();
	
	@Query("SELECT l FROM Log l WHERE l.taxi.numTaxi= :idTaxi")
	Log findLogByTaxi(Long idTaxi);
	
	@Query("SELECT l FROM Log l join l.courses c where c.numCourse = :numCourse")
    Log retrieveLogByCourse(Long numCourse);
}
