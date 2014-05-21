package squash.DTO;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import squash.model.Address;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import squash.service.AddressService;
@Service
public class AddressDTO {
	


	public void update(JSONObject obj, Address address, AddressService addressService) throws JSONException {
		System.out.println(addressService.toString());
		address = addressService.findOne(address.getAddressID());
		if(obj.has("city")){
			address.setCity(obj.getString("city"));
		}
		if(obj.has("street1")){
			address.setStreet1(obj.getString("street1"));
		}
		if(obj.has("street2")){
			address.setStreet2(obj.getString("street2"));
		}
		if(obj.has("zip")){
			address.setZip(obj.getString("zip"));
		}
		
		addressService.save(address);
		
	}
	public void create(Address address, AddressService addressService){
		addressService.save(address);
	}
	
}
