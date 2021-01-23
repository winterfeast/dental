package kz.saparov.stomat.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kz.saparov.stomat.Models.Patient;
import kz.saparov.stomat.Repositories.PatientRepository;

@Service
@Transactional
public class PatientService {
	@Autowired 
	PatientRepository repo;
	
	public void save(Patient patient) {
		repo.save(patient);
	}
	
	public List<Patient> listAll() {
		return (List<Patient>) repo.findAll();
	}
	
	public Patient get(Long id) {
		return repo.findById(id).get();
	}
	
	public void delete(Long id) {
		repo.deleteById(id);
	}
	
	public List<Patient> search(String keyword) {
		return repo.search(keyword);
	}
	
	public List<Patient> getLastTen(){
		return repo.getLastTenPatient();
	}
}

