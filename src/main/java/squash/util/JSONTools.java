package squash.util;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;

import squash.model.JSONObj;

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

}
