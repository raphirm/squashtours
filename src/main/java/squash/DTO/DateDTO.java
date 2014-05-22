package squash.DTO;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import squash.model.Address;
import squash.model.Dates;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import squash.service.AddressService;
import squash.service.DatesService;
import squash.service.SpielService;
import squash.service.UserService;
import squash.util.DateStatus;
@Service
public class DateDTO {
	


	public void update(JSONObject obj, Dates date, DatesService datesService, UserService userService, SpielService spielService) throws JSONException, ParseException {
		
		date = datesService.findOne(date.getId());
		if(obj.has("date")){
			DateFormat df = new SimpleDateFormat();
			date.setDate(df.parse(obj.getString("date")) );
		}
		if(obj.has("origin")){
			date.setOrigin(userService.findOne(obj.getJSONObject("origin").getLong("id")));
		}
		if(obj.has("match")){
			date.setSpiel(spielService.findOne(obj.getJSONObject("match").getLong("id")));
		}
		if(obj.has("status")){
			date.setStatus(DateStatus.valueOf(obj.getString("status")));
		}
		
		datesService.save(date);
		
	}
	public void create(Dates date, DatesService dateService, UserService userService, SpielService spielService){
		if(date.getOrigin()!=null){
			date.setOrigin(userService.findOne(date.getOrigin().getId()));
		}
		if(date.getSpiel()!=null){
			date.setSpiel(spielService.findOne(date.getSpiel().getMatchID()));
		}
		
	
	}
	
}
