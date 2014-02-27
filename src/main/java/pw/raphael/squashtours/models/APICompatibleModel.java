/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pw.raphael.squashtours.models;

import org.json.JSONException;

/**
 *
 * @author raphi
 */
public interface APICompatibleModel {
    public String getJSON() throws JSONException;
}
