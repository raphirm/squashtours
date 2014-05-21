package squash.util;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import squash.model.JSONObj;
import squash.model.User;

public class JSONTools {
	public  static <T extends JSONObj> JSONArray getJSONArray(List<T> list) throws JSONException{
		JSONArray arr = new JSONArray();
		for (T t : list) {
			arr.put(t.getJSONObjSave());
		}
		return arr;
		
	}
	public  static <T extends JSONObj> JSONArray getJSONArray(Iterable<T> list) throws JSONException{
		JSONArray arr = new JSONArray();
		for (T t : list) {
			arr.put(t.getJSONObj());
		}
		return arr;
		
	}
	public  static Iterable<Long> getJSONArray(JSONArray arr) throws JSONException{
		ArrayList<Long> iter = new ArrayList<Long>();
		for (int i = 0; i < arr.length(); i++) {
			iter.add(new Long(((JSONObject) arr.get(i)).getString("id")));
		}
		return iter;
		
	}
	public static String generateSuccessReply() throws JSONException{
		JSONObject returnValues = new JSONObject();
		
			
			returnValues.put("ReturnCode", "200");
			returnValues.put("Description", "Success");
		
		return returnValues.toString();
		
	}
	public static String generateNotFoundReply() throws JSONException{
	JSONObject returnValues = new JSONObject();
	
		

		returnValues.put("ReturnCode", "404");
		returnValues.put("Description", "Object not found");

	
	return returnValues.toString();
	
}
	public static String generateErrorReply() throws JSONException {
		JSONObject returnValues = new JSONObject();

		returnValues.put("ReturnCode", "510");
		returnValues.put("Description", "JSON incorrect");
		return returnValues.toString();
	}
	public static String generateSuccessReply(Long long1) throws JSONException{
		JSONObject returnValues = new JSONObject();
		
			
			returnValues.put("ReturnCode", "200");
			returnValues.put("ObjectID", long1);
			returnValues.put("Description", "Success");
		
		return returnValues.toString();
		
	}

}
