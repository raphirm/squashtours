package squash.model;

import org.json.JSONException;
import org.json.JSONObject;

public interface JSONObj {
	public String getJSON() throws JSONException;
	public JSONObject getJSONObj() throws JSONException;
	public JSONObject getJSONObjSave() throws JSONException;
	public void update(JSONObj obj);
}
