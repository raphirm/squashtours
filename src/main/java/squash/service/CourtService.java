package squash.service;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import squash.model.Address;
import squash.model.Court;
import squash.model.User;

public interface CourtService extends CrudRepository<Court, Long> {

    Court findBycourtID(Long courtID);
    
}