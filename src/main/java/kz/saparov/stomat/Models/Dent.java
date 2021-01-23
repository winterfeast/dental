package kz.saparov.stomat.Models;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "dent")
public class Dent {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private int toothNumber;
	private String healingDate;
	private Integer dentCondition;           
	private String comment;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id")
	private Patient patient;
	
	public Dent() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public int getToothNumber() {
		return toothNumber;
	}

	public void setToothNumber(int tooth_number) {
		this.toothNumber = tooth_number;
	}

	public String getHealingDate() {
		return healingDate;
	}

	public void setHealingDate(String healingDate) {
		this.healingDate = healingDate;
	}

	public Integer getDentCondition() {
		return dentCondition;
	}

	public void setDentCondition(Integer dent_condition) {
		this.dentCondition = dent_condition;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}
	
	public String getToothCode(){
		String[] toothCode = {"18","17","16","15","14","13","12","11",
							  "21","22","23","24","25","26","27","28",
							  "48","47","46","45","44","43","42","41",
							  "31","32","33","34","35","36","37","38",
							  "V", "IV", "III", "II", "I", 
							  "I", "II", "III", "IV", "V",
							  "V", "IV", "III", "II", "I", 
							  "I", "II", "III", "IV", "V"};
		return toothCode[toothNumber-1];
	}

	

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + toothNumber;
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
		Dent other = (Dent) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (toothNumber != other.toothNumber)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Dent [id=" + id + ", toothNumber=" + toothNumber + ", healingDate=" + healingDate + ", dentCondition="
				+ dentCondition + ", comment=" + comment + ", patient=" + patient.getId() + "]";
	}
	
	
}
