package kz.saparov.stomat.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kz.saparov.stomat.Models.Record;
import kz.saparov.stomat.Repositories.RecordRepository;

@Service
@Transactional
public class RecordService {
	@Autowired 
	RecordRepository repo;
	
	public List<Record> getRecordsForPeriod(String beginDate, String endDate) {
		int status = 0;
		List<Record> records = repo.getRecordsForPeriod(beginDate, endDate,status);
		return records;
	}
	
	public List<Record> getRecordsForDay(String date) {
		String begin = date.substring(0,10) + " 00:00:00";
		String end = date.substring(0,10) + " 23:59:00";
		List<Record> records = getRecordsForPeriod(begin,end);
		return records;
	}
	
	public void save(Record record){
		repo.save(record);
	}
	
	public Record get(Long id) {
		return repo.findById(id).get();
	}
}
