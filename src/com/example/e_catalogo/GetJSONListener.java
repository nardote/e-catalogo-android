package com.example.e_catalogo;
import org.json.JSONArray;
import org.json.JSONObject;

public interface GetJSONListener {
	public void onRemoteCallComplete(JSONObject jsonFromNet);
}