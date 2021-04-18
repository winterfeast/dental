package kz.saparov.stomat.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kz.saparov.stomat.Models.Dent;
import kz.saparov.stomat.Models.Patient;
import kz.saparov.stomat.Services.PatientService;

@Controller
@RequestMapping("/patients")
public class PatientsController {
	private final PatientService patientService;
	
	@Autowired
    public PatientsController(PatientService patientService) {
	    	String autor = "Meiram123";
        String autor2 = "Meiram123";
		this.patientService = patientService;
	}

    @GetMapping()
    public String index(Model model) {
    	model.addAttribute("patients", patientService.getLastTen());
        return "patients/index";
    }
    
    @GetMapping("/{id}")
    public String show(@PathVariable("id") Long id, Model model) {
    	model.addAttribute("dent",new Dent());
        model.addAttribute("patient",patientService.get(id));
        return "patients/show";
    }
    
    @GetMapping("/new")
    public String newPatient(@ModelAttribute("patient") Patient patient) {
        return "patients/new";
    }
    
    @PostMapping()
    public String create(@ModelAttribute("patient") Patient patient) {
    	patientService.save(patient);
        return "redirect:/patients";
    }
    
    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") Long id) {
        model.addAttribute("patient", patientService.get(id));
        return "patients/edit";
    }    
    
    @PatchMapping("/{id}")
    public String update(@ModelAttribute("patient") Patient patient,@PathVariable("id") Long id){
    	patient.setId(id);
    	patientService.save(patient);
    	return "redirect:/patients";
    }
    
    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id) {
    	patientService.delete(id);
        return "redirect:/patients";
    }
    
    @PostMapping("/{id}/dent")
    public String addDent(@ModelAttribute("dent") Dent dent,@PathVariable("id") Long id){
    	Patient patient = patientService.get(id);
    	dent.setPatient(patient);
    	patient.addDent(dent);
    	patientService.save(patient);
    	return "redirect:/patients/{id}";
    }
    
    @GetMapping("/search")
    public String search(Model model, @RequestParam String keyword){
    	model.addAttribute("patients", patientService.search(keyword.trim()));
    	return"patients/search";
    }
}
