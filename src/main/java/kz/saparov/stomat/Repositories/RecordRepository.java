package kz.saparov.stomat.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import kz.saparov.stomat.Models.Record;

public interface RecordRepository extends CrudRepository<Record, Long>{
	@Query(value="SELECT d FROM Record d WHERE "
			+ "d.dateTime >= :beginDate "
			+ "AND d.dateTime <= :endDate "
			+ "AND d.status = :status "
			+ "ORDER BY d.dateTime")
	public List<Record> getRecordsForPeriod(@Param("beginDate") String beginDate,@Param("endDate") String endDate, @Param("status") int status);
	
}
