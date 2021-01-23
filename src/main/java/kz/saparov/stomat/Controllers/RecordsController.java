package kz.saparov.stomat.Controllers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import kz.saparov.stomat.Models.Patient;
import kz.saparov.stomat.Models.Record;
import kz.saparov.stomat.Services.PatientService;
import kz.saparov.stomat.Services.RecordService;

@Controller
@RequestMapping("/records")
public class RecordsController {
	private final RecordService recordService;
	private final PatientService patientService;
	
	@Autowired
	public RecordsController(RecordService recordService, PatientService patientService){
		this.recordService = recordService;
		this.patientService = patientService;
	}
	
	@GetMapping("/{day}")
	public String getRecordsForDay(Model model, @PathVariable("day") int day) {
		LocalDateTime recordsDate = LocalDateTime.now();
		DateTimeFormatter myFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		List<Record> recorsds = recordService.getRecordsForDay(myFormat.format(recordsDate.plusDays(day))); // Получаем лист на дату
		model.addAttribute("record",recorsds);
		return "records/records";
	}

	@GetMapping("/new/{id}")
	public String newRecordById(@ModelAttribute("record") Record record,  Model model, @PathVariable("id") Long id) {
		model.addAttribute("patient", patientService.get(id));
		return "records/new";
	}
	
	@GetMapping("/{id}/allRecords")
	public String showPatientRecords(Model model, @PathVariable("id") Long id) {
		model.addAttribute("patient", patientService.get(id));
		return "records/patientAllRecords";
	}

	@PostMapping("/{id}")
	public String createRecord(@ModelAttribute("record") Record record, @PathVariable("id") Long id){
		Patient patient = patientService.get(id);
		patient.addRecord(record);
		patientService.save(patient);
		return "redirect:/records";
	}
	
	@GetMapping("/{id}/edit")
	public String editRecords(Model model, @PathVariable("id") Long id) {
		model.addAttribute("record", recordService.get(id));
		return "records/recordEdit";
	}
	
	
	@PatchMapping("/{id}")
	public String updateRecord(@ModelAttribute("record") Record record, @PathVariable("id") Long id){
		Patient patient = patientService.get(id);
		patient.addRecord(record);
		patientService.save(patient);
		return "redirect:/records/0";
	}
	
	@RequestMapping(value = "/freeRecords", method = RequestMethod.POST)
	public @ResponseBody List<Record> ajaxSearch(HttpServletRequest req, HttpServletResponse res) {
		String date = req.getParameter("date");
		List<Record> recordsList = recordService.getRecordsForDay(date);
		return recordsList;
	}
	
	@RequestMapping(value = "/saveWithAjax", method = RequestMethod.POST)
	public @ResponseBody String ajaxSave(HttpServletRequest req, HttpServletResponse res) {
		Long id = Long. parseLong(req.getParameter("patien_id"));
		Patient patient = patientService.get(id);
		Record record = new Record();
		record.setComment(req.getParameter("comment"));
		record.setDuration(Integer.parseInt(req.getParameter("proced_dur")));
		record.setDateTime(req.getParameter("date"));
		patient.addRecord(record);
		patientService.save(patient);
		return "ok";
	}
}
