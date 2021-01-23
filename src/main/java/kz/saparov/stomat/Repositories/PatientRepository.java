package kz.saparov.stomat.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import kz.saparov.stomat.Models.Patient;

public interface PatientRepository extends CrudRepository<Patient, Long> {
	
	@Query(value = "SELECT p FROM Patient p WHERE p.name LIKE '%' || :keyword || '%'"
			+ " OR p.lastName LIKE '%' || :keyword || '%'"
			+ " OR p.patroNymic LIKE '%' || :keyword || '%'"
			+ " OR p.address LIKE '%' || :keyword || '%'")
	public List<Patient> search(@Param("keyword") String keyword);
	
	
	@Query(value="SELECT * FROM patient ORDER BY id DESC LIMIT 10", nativeQuery = true)
	public List<Patient> getLastTenPatient();
}