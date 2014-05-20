package squash.service;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import squash.model.Address;
import squash.model.Satz;
import squash.model.User;

public interface SatzService extends CrudRepository<Satz, Long> {

    
}