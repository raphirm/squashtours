package squash.DTO;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import squash.model.Address;
import squash.model.Court;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import squash.service.AddressService;
import squash.service.CourtService;
@Service
public class CourtDTO {
	


	public void update(JSONObject obj, Court court, CourtService courtService, AddressService addressService) throws JSONException {
		System.out.println(courtService.toString());
		court = courtService.findOne(court.getId());
		if(obj.has("courtName")){
			court.setCourtName(obj.getString("courtName"));
		}
		if(obj.has("telephoneNumber")){
			court.setTelephoneNumber(obj.getString("telephoneNumber"));
		}
		if(obj.has("address")){
			court.setAddress(addressService.findOne(obj.getJSONObject(("address")).getLong("id")));
		}
		
		
		courtService.save(court);
		
	}
	public void create(Court court, CourtService courtService, AddressService addressService){
		if(court.getAddress()!=null){
			court.setAddress(addressService.findOne(court.getAddress().getId()));
		}
		courtService.save(court);
	}
	
}
