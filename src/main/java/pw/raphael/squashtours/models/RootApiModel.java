/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pw.raphael.squashtours.models;

import org.json.*;
import java.util.ArrayList;

/**
 *
 * @author raphi
 */
public class RootApiModel implements APICompatibleModel{
    
    public ArrayList<String> types = new ArrayList<String>();
    public void addType(String type){
        types.add(type);
    }
    public String getJSON() throws JSONException{
        JSONObject obj = new JSONObject();
        JSONObject obj2 = new JSONObject();
           obj2.put("Obj2", "2323");
           obj2.put("obje", "3434");
           obj.put("Types", types);
           obj.put("Variable2", obj2);
           obj.put("INT1", 0);
           
        return obj.toString();
    }


    
    
}
