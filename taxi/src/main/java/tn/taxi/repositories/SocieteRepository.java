package tn.taxi.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.taxi.entities.Societe;

@Repository
public interface SocieteRepository extends CrudRepository<Societe, Long>{

	@Query("select s from Societe s where s.nom= :nom")
	List<Societe> retrieveSocieteByName(String nom);
}
