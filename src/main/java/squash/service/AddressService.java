package squash.service;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import squash.model.Address;
import squash.model.User;

public interface AddressService extends CrudRepository<Address, Long> {
	

}