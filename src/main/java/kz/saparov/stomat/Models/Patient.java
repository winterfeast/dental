package kz.saparov.stomat.Models;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "patient")
public class Patient {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String lastName;
	private String patroNymic;
	private String addDate;
	private String phone;
	private String address;
	
	@OneToMany(mappedBy = "patient", cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
	private List<Dent> teeth;
	
	@OneToMany(mappedBy = "patient", cascade = {CascadeType.ALL}, fetch = FetchType.LAZY) 
	@JsonManagedReference       // тестовая
	private List<Record> records;
	
	public Patient() {}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPatroNymic() {
		return patroNymic;
	}

	public void setPatroNymic(String patroNymic) {
		this.patroNymic = patroNymic;
	}

	public String getAddDate() {
		return addDate;
	}

	public void setAddDate(String addDate) {
		this.addDate = addDate;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	public List<Record> getRecords() {
		return records;
	}

	public void setRecords(List<Record> records) {
		this.records = records;
	}
	
	public List<Dent> getTeeth() {
		return teeth;
	}
	
	public void setTeeth(List<Dent> teeth) {
		this.teeth.clear();
		if(teeth != null){
			this.teeth.addAll(teeth);
		}
	}

	public void addDent(Dent dent){ 					  
		int i = dent.getToothNumber();
		for(Dent d : teeth){
			if(d.getToothNumber() == i){						// обновляем данные о зубе
				if(dent.getDentCondition() == 5) {
					LocalDateTime curDate = LocalDateTime.now();
					DateTimeFormatter myFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd 00:00");
					d.setHealingDate(myFormat.format(curDate));
				}
				d.setDentCondition(dent.getDentCondition());
				d.setComment(dent.getComment());
				i = 0;
				break; 								      
			}
		}
		if(i != 0){
			dent.setPatient(this);
			teeth.add(dent);
		}
	}
	
	public void removeDent(Dent dent){
		teeth.remove(dent);
	}
	
	public void addRecord(Record info){
		boolean mode = true;
		for(Record patientRecord : records) {
			if(info.getId() == patientRecord.getId()){
				patientRecord.setComment(info.getComment());
				patientRecord.setDateTime(info.getDateTime());
				patientRecord.setDuration(info.getDuration());
				patientRecord.setStatus(info.getStatus());
				mode = false;
				System.out.println("Попали в условную конструкцию");
				break;
			}
		}
		if(mode){
			System.out.println("Не попали в условную конструкцию");
			info.setPatient(this);
			records.add(info);
		}
	}
	
	public void removeRecord(Record record){
		records.remove(record);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((teeth == null) ? 0 : teeth.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Patient other = (Patient) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (teeth == null) {
			if (other.teeth != null)
				return false;
		} else if (!teeth.equals(other.teeth))
			return false;
		return true;
	}

	
	
}