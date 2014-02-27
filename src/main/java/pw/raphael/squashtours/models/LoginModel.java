/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pw.raphael.squashtours.models;

import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author raphi
 */
public class LoginModel implements APICompatibleModel{
    public int bla;

    public int getBla() {
        return bla;
    }

    public void setBla(int bla) {
        this.bla = bla;
    }
    public String getJSON() throws JSONException{
        JSONObject obj = new JSONObject();
        obj.put("bla", bla);
        return obj.toString();
    }
    
    
}
