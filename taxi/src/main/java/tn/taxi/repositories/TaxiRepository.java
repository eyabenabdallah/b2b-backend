package tn.taxi.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.taxi.entities.Taxi;

@Repository
public interface TaxiRepository extends CrudRepository<Taxi, Long> {
	
	@Query("SELECT t.numTaxi  FROM Taxi t")
	List<Long> findAllNumTaxi();
	
	@Query("SELECT t.idTaxi  FROM Taxi t where t.log= null")
	Long findNull();

}
