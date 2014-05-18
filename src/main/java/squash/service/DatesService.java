package squash.service;

import org.springframework.data.repository.CrudRepository;

import squash.model.Court;
import squash.model.Dates;

public interface DatesService extends CrudRepository<Dates, Long> {

	  
	}