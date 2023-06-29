package tn.taxi.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.taxi.entities.Client;

@Repository
public interface ClientRepository extends CrudRepository<Client, Long> {

	@Query("SELECT CONCAT(c.prenom, ' ', c.nom) FROM Client c")
    List<String> getFullNames();
	
	@Query("SELECT c FROM Client c where c.societe.nom = :nom order by c.prenom")
    List<Client> retrieveClientsBySociete(String nom);
	
	@Query("SELECT c FROM Client c join c.courses course where course.numCourse = :numCourse")
    List<Client> retrieveClientsByCourse(Long numCourse);
}
