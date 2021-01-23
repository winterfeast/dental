package kz.saparov.stomat.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kz.saparov.stomat.Models.Dent;
import kz.saparov.stomat.Repositories.DentRepository;

@Service
@Transactional
public class DentService{
	@Autowired DentRepository dentRepository;
	
	public void save(Dent dent){
		dentRepository.save(dent);
	}	
	
	public Dent get(Long id){
		return dentRepository.findById(id).get();
	}
}
