package squash.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import squash.DTO.AddressDTO;
import squash.DTO.CourtDTO;
import squash.DTO.DateDTO;
import squash.DTO.GroupDTO;
import squash.DTO.LeagueDTO;
import squash.DTO.RankingDTO;
import squash.DTO.SatzDTO;
import squash.DTO.SpielDTO;
import squash.DTO.UserDTO;
import squash.model.Address;
import squash.model.Court;
import squash.model.Dates;
import squash.model.Group;
import squash.model.JSONObj;
import squash.model.League;
import squash.model.Ranking;
import squash.model.Satz;
import squash.model.Spiel;
import squash.model.User;
import squash.service.AddressService;
import squash.service.CourtService;
import squash.service.DatesService;
import squash.service.GroupService;
import squash.service.LeagueService;
import squash.service.RankingService;
import squash.service.SatzService;
import squash.service.SpielService;
import squash.service.UserService;
import squash.util.JSONTools;
import squash.util.RankingManager;
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
	@Resource
	private GroupService groupService;
	@Resource
	private LeagueService leagueService;
	@Resource
	private RankingService rankingService;
	@Resource
	private SatzService satzService;
	@Resource
	private SpielService spielService;
	
	
	
	@RequestMapping(method={RequestMethod.GET}, value="/api")
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
	
	@RequestMapping(method={RequestMethod.GET}, value="/api/{path:[a-z-]+}/{id}")
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
		case "group":
			obj = groupService.findOne(id);
			break;	
		case "league":
			obj = leagueService.findOne(id);
			break;
		case "ranking":
			obj = rankingService.findOne(id);
			break;
		case "satz":
			obj = satzService.findOne(id);
			break;
		case "spiel":
			Iterable<Spiel> spielarr = spielService.findAll();
			obj = new Spiel();
			for (Spiel spl : spielarr) {
				if(spl.getId()==id){
					obj = spl;
					break;
				}
			}
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
	@RequestMapping(method={RequestMethod.GET},value="/api/{path:[a-z-]+}")
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
		case "group":
			Iterable<Group> gobj =  groupService.findAll();
			arr = JSONTools.getJSONArray(gobj);
			break;
		case "league":
			Iterable<League> lobj =  leagueService.findAll();
			arr = JSONTools.getJSONArray(lobj);
			break;	
		case "ranking":
			Iterable<Ranking> robj =  rankingService.findAll();
			arr = JSONTools.getJSONArray(robj);
			break;
		case "satz":
			Iterable<Satz> saobj =  satzService.findAll();
			arr = JSONTools.getJSONArray(saobj);
			break;	
		case "spiel":
			Iterable<Spiel> spobj =  spielService.findAll();
			arr = JSONTools.getJSONArray(spobj);
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

	
	
	@RequestMapping(method={RequestMethod.POST, RequestMethod.PUT}, value="/api/{path:[a-z-]+}/{id}", produces="application/json", consumes="application/json")
	public @ResponseBody String update( @RequestBody String str, @PathVariable("id") long id, @PathVariable("path") String path, BindingResult result) throws Exception{
		JSONObject obj;
		if(str.startsWith("\'")||str.startsWith("\"")){
			str = str.substring(1, str.length()-1);
		}
		try{
			 obj = new JSONObject(str);
		}catch(Exception e){
			return JSONTools.generateErrorReply();
		}
		
		boolean found = false;
		switch (path) {
		case "user":
			User user  = userService.findOne(id);
			UserDTO userDTO = new UserDTO();
			if(user!=null){
				found=true;
				userDTO.update(obj, user, userService, groupService, leagueService, rankingService, addressService);
				return JSONTools.generateSuccessReply(user.getId());
			}
			break;
		case "address":
			Address address = addressService.findOne(id);
			AddressDTO addressDTO = new AddressDTO();
			if(address!=null){
				found=true;
				addressDTO.update(obj, address, addressService);
				return JSONTools.generateSuccessReply(address.getId());
			}
			break;
		case "court":
			Court court = courtService.findOne(id);
			CourtDTO courtDTO = new CourtDTO();
			if(court!=null){
				found=true;
				courtDTO.update(obj, court, courtService, addressService);
				return JSONTools.generateSuccessReply(court.getId());
			}
			break;	
		case "dates":
			Dates date = datesService.findOne(id);
			DateDTO datetDTO = new DateDTO();
			if(date!=null){
				found=true;
				datetDTO.update(obj, date, datesService, userService, spielService);
				return JSONTools.generateSuccessReply(date.getId());
			}
			break;	
		case "group":
			Group group = groupService.findOne(id);
			GroupDTO groupDTO = new GroupDTO();
			if(group!=null){
				found=true;
				groupDTO.update(obj, group, groupService, userService);
				return JSONTools.generateSuccessReply(group.getId());
			}
			break;	
		case "league":
			League league = leagueService.findOne(id);
			LeagueDTO leagueDTO = new LeagueDTO();
			if(league!=null){
				found=true;
				leagueDTO.update(obj, league, leagueService, userService, rankingService, spielService);
				return JSONTools.generateSuccessReply(league.getId());
			}
			break;	
		case "ranking":
			Ranking ranking = rankingService.findOne(id);
			RankingDTO rankingDTO = new RankingDTO();
			if(ranking!=null){
				found=true;
				rankingDTO.update(obj, ranking, rankingService, userService, leagueService);
				return JSONTools.generateSuccessReply(ranking.getId());
			}
			break;	
		case "satz":
			Satz satz = satzService.findOne(id);
			SatzDTO satzDTO = new SatzDTO();
			if(satz!=null){
				found=true;
				satzDTO.update(obj, satz, satzService,spielService );
				return JSONTools.generateSuccessReply(satz.getId());
			}
			break;
		case "spiel":
			Spiel spiel = spielService.findOne(id);
			SpielDTO spielDTO = new SpielDTO();
			if(spiel!=null){
				found=true;
				spielDTO.update(obj, spiel, spielService, userService, datesService, satzService , rankingService);
				return JSONTools.generateSuccessReply(spiel.getId());
			}
			break;
		default:
			break;
		}
		if(!found){
			return JSONTools.generateNotFoundReply();
		}
		return JSONTools.generateNotFoundReply();
		
	}
	@RequestMapping(method={RequestMethod.POST, RequestMethod.PUT}, value="/api/user", produces="application/json", consumes="application/json")
	public @ResponseBody String createUser(  @Valid  @RequestBody User user,  BindingResult result) throws Exception{
		JSONObject obj = new JSONObject();
		System.out.println(user.getUsername() + " " + user.getPassword());
		if(result.hasErrors()){
			obj.put("ReturnCode", "501");
			obj.put("Errors", result.getAllErrors().toString());
			
			return obj.toString();
		}
		else{
			UserDTO userDTO = new UserDTO();
			userDTO.create(user, userService, groupService, leagueService, rankingService, addressService);
			return JSONTools.generateSuccessReply(user.getId());
		}
		
	}
	@RequestMapping(method={RequestMethod.POST, RequestMethod.PUT}, value="/api/address", produces="application/json", consumes="application/json")
	public @ResponseBody String createAddress(  @Valid  @RequestBody Address address,  BindingResult result) throws Exception{
		JSONObject obj = new JSONObject();
		if(result.hasErrors()){
			obj.put("ReturnCode", "501");
			obj.put("Errors", result.getAllErrors().toString());
			
			return obj.toString();
		}
		else{
			AddressDTO addressDTO = new AddressDTO();
			addressDTO.create(address,  addressService);
			return JSONTools.generateSuccessReply(address.getId());
		}
		
	}
	@RequestMapping(method={RequestMethod.POST, RequestMethod.PUT}, value="/api/court", produces="application/json", consumes="application/json")
	public @ResponseBody String createCourt(  @Valid  @RequestBody Court court,  BindingResult result) throws Exception{
		JSONObject obj = new JSONObject();
		if(result.hasErrors()){
			obj.put("ReturnCode", "501");
			obj.put("Errors", result.getAllErrors().toString());
			
			return obj.toString();
		}
		else{
			CourtDTO courtDTO = new CourtDTO();
			courtDTO.create(court, courtService, addressService);
			return JSONTools.generateSuccessReply(court.getId());
		}
		
	}
	@RequestMapping(method={RequestMethod.POST, RequestMethod.PUT}, value="/api/dates", produces="application/json", consumes="application/json")
	public @ResponseBody String createCourt(  @Valid  @RequestBody Dates date,  BindingResult result) throws Exception{
		JSONObject obj = new JSONObject();
		if(result.hasErrors()){
			obj.put("ReturnCode", "501");
			obj.put("Errors", result.getAllErrors().toString());
			
			return obj.toString();
		}
		else{
			DateDTO dateDTO = new DateDTO();
			date.setOrigin(userService.findByUsername(((User)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername()));
			dateDTO.create(date, datesService, userService, spielService);
			return JSONTools.generateSuccessReply(date.getId());
		}
		
	}
	@RequestMapping(method={RequestMethod.POST, RequestMethod.PUT}, value="/api/group", produces="application/json", consumes="application/json")
	public @ResponseBody String createGroup(  @Valid  @RequestBody Group group,  BindingResult result) throws Exception{
		JSONObject obj = new JSONObject();
		if(result.hasErrors()){
			obj.put("ReturnCode", "501");
			obj.put("Errors", result.getAllErrors().toString());
			
			return obj.toString();
		}
		else{
			GroupDTO groupDTO = new GroupDTO();
			groupDTO.create(group, userService, groupService);
			return JSONTools.generateSuccessReply(group.getId());
		}
		
	}
	@RequestMapping(method={RequestMethod.POST, RequestMethod.PUT}, value="/api/league", produces="application/json", consumes="application/json")
	public @ResponseBody String createLeague(  @Valid  @RequestBody League league,  BindingResult result) throws Exception{
		JSONObject obj = new JSONObject();
		if(result.hasErrors()){
			obj.put("ReturnCode", "501");
			obj.put("Errors", result.getAllErrors().toString());
			
			return obj.toString();
		}
		else{
			LeagueDTO leagueDTO = new LeagueDTO();
			leagueDTO.create(league, leagueService, userService, rankingService, spielService);
			return JSONTools.generateSuccessReply(league.getId());
		}
		
	}
	@RequestMapping(method={RequestMethod.POST, RequestMethod.PUT}, value="/api/ranking", produces="application/json", consumes="application/json")
	public @ResponseBody String createRanking(  @Valid  @RequestBody Ranking ranking,  BindingResult result) throws Exception{
		JSONObject obj = new JSONObject();
		if(result.hasErrors()){
			obj.put("ReturnCode", "501");
			obj.put("Errors", result.getAllErrors().toString());
			
			return obj.toString();
		}
		else{
			RankingDTO rankingDTO = new RankingDTO();
			rankingDTO.create(ranking, rankingService, userService, leagueService);
			return JSONTools.generateSuccessReply(ranking.getId());
		}
		
	}
	@RequestMapping(method={RequestMethod.POST, RequestMethod.PUT}, value="/api/satz", produces="application/json", consumes="application/json")
	public @ResponseBody String createSatz(  @Valid  @RequestBody Satz set,  BindingResult result) throws Exception{
		JSONObject obj = new JSONObject();
		if(result.hasErrors()){
			obj.put("ReturnCode", "501");
			obj.put("Errors", result.getAllErrors().toString());
			
			return obj.toString();
		}
		else{
			SatzDTO setDto = new SatzDTO();
			setDto.create(set, satzService, spielService);
			return JSONTools.generateSuccessReply(set.getId());
		}
	}
		@RequestMapping(method={RequestMethod.POST, RequestMethod.PUT}, value="/api/spiel", produces="application/json", consumes="application/json")
		public @ResponseBody String createSpiel(  @Valid  @RequestBody Spiel spiel,  BindingResult result) throws Exception{
			JSONObject obj = new JSONObject();
			if(result.hasErrors()){
				obj.put("ReturnCode", "501");
				obj.put("Errors", result.getAllErrors().toString());
				
				return obj.toString();
			}
			else{
				SpielDTO spielDTO = new SpielDTO();
				spielDTO.create(spiel, spielService, userService, datesService, satzService, leagueService);
				return JSONTools.generateSuccessReply(spiel.getId());
			}
		
	}
		@RequestMapping(method={RequestMethod.POST, RequestMethod.PUT}, value="/api/register", produces="application/json", consumes="application/json")
		public @ResponseBody String register(  @Valid  @RequestBody User user,  BindingResult result) throws Exception{
			JSONObject obj = new JSONObject();
			System.out.println(user.getUsername() + " " + user.getPassword());
			if(result.hasErrors()){
				obj.put("ReturnCode", "501");
				obj.put("Errors", result.getAllErrors().toString());
				
				return obj.toString();
			}
			else{
				UserDTO userDTO = new UserDTO();
				user.setGroups(null);
				userDTO.create(user, userService, groupService, leagueService, rankingService, addressService);
				return JSONTools.generateSuccessReply(user.getId());
			}
			
		}
		@RequestMapping(value="/api/_login", produces="application/json")
		public @ResponseBody String login() throws Exception{
			Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			User user;
			if (principal instanceof User) {
			   user  = ((User)principal);
			} else {
			  user = new User();
			  user.setId(new Long(0));
			}
			return JSONTools.generateSuccessReply(user.getId());
			
		}
		@RequestMapping(method={RequestMethod.POST, RequestMethod.PUT}, value="/api/spiel/{id}/addSet", produces="application/json", consumes="application/json")
		public @ResponseBody String addSet( @RequestBody String obj, @PathVariable("id") long id,  BindingResult result) throws Exception{
			JSONObject json = new JSONObject(obj);
			Iterable<Spiel> spielarr = spielService.findAll();
			Spiel spiel = new Spiel();
			for (Spiel spl : spielarr) {
				if(spl.getId()==id){
					spiel = spl;
					break;
				}
			}
			Satz satz = satzService.findOne(json.getLong("id"));
			List<Satz> sets = spiel.getSets();
			sets.add(satz);
			spielService.save(spiel);
			return JSONTools.generateSuccessReply(spiel.getId());
			
			
			
		}
		@RequestMapping(method={RequestMethod.POST, RequestMethod.PUT}, value="/api/league/join", produces="application/json", consumes="application/json")
		public @ResponseBody String register(  @Valid  @RequestBody String json) throws Exception{
			JSONObject obj = new JSONObject(json);
			User user = userService.findOne(obj.getLong("uid"));
			League league = leagueService.findOne(obj.getLong("lid"));
			if(league.getUser()!=null){
				league.getUser().add(user);
			}else{
				List<User> users = new ArrayList<User>();
				users.add(user);
				league.setUser(users);
			}
			RankingManager.createRanking(user, league, userService, leagueService, rankingService);
			leagueService.save(league);
			
			return JSONTools.generateSuccessReply();
		}

}
