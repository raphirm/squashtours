package squash.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import squash.model.Address;
import squash.model.Court;
import squash.model.Dates;
import squash.model.JSONObj;
import squash.model.User;
import squash.service.AddressService;
import squash.service.CourtService;
import squash.service.DatesService;
import squash.service.UserService;
import squash.util.JSONTools;
@Controller
public class APIController {
	@Resource
	private UserService userService;
	@Resource
	private AddressService addressService;
	@Resource
	private CourtService courtService;
	@Resource
	private DatesService datesService;
	
	@RequestMapping(value="/api")
	public String overview(Model model){
		JSONObj obj = new JSONObj() {
			
			@Override
			public JSONObject getJSONObjSave() throws JSONException {
				return null;
			}
			
			@Override
			public JSONObject getJSONObj() throws JSONException {
				return null;
			}
			
			@Override
			public String getJSON() throws JSONException {
				JSONArray obj = new JSONArray();
				obj.put("address");
				obj.put("court");
				obj.put("dates");
				obj.put("group");
				obj.put("league");
				obj.put("ranking");
				obj.put("satz");
				obj.put("spiel");
				obj.put("user");
				return obj.toString();
			}
		};
		model.addAttribute("api", obj);
		return "api/api";
	}
	
	@RequestMapping(value="/api/{path:[a-z-]+}/{id}")
	public String variableAPI(Model model, @PathVariable("path") String path, @PathVariable("id") long id){
		JSONObj obj = null;
		switch (path) {
		case "address":
			obj = addressService.findOne(id);
			break;
		case "court":
			obj = courtService.findOne(id);
			break;
		case "user":
			obj = userService.findOne(id);
			break;
		case "dates":
			obj = datesService.findOne(id);
			break;
		default:
			break;
		}
		
		if(obj!=null){
			model.addAttribute("api", obj);

		}else{
			return "api/null";
		}
		return "api/api";
		
	}
	@RequestMapping(value="/api/{path:[a-z-]+}")
	public String variableAPIO(Model model, @PathVariable("path") String path) throws JSONException{
		JSONArray arr = new JSONArray();
		
		switch (path) {
		case "address":
			Iterable<Address> obj =  addressService.findAll();
			arr = JSONTools.getJSONArray(obj);
			break;
		case "court":
			Iterable<Court> cobj =  courtService.findAll();
			arr = JSONTools.getJSONArray(cobj);

			break;
		case "user":
			Iterable<User> uobj =  userService.findAll();
			arr = JSONTools.getJSONArray(uobj);
			break;
		case "dates":
			Iterable<Dates> dobj =  datesService.findAll();
			arr = JSONTools.getJSONArray(dobj);
			break;
		default:
			break;
		}
		
		if(arr.length()>0){
			model.addAttribute("api", arr);
			return "api/apilist";
		}
		else{
			return "api/null";
		}
		
		
	}
		

}
