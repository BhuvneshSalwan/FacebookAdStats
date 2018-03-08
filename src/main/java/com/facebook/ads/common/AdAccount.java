package com.facebook.ads.common;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Calendar;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

public class AdAccount {

	public static final String URL = "https://graph.facebook.com";
	public static final String VERSION = "v2.11";
	public static final String SESSION_TOKEN = "EAAWXmQeQZAmcBAJHkodkpmV1ZBX7S9t9eQDsmbBpr0KVFAoGYhV9vAw00a2sYkoM22lVp0s0RVxi0Uiw2cgxjBJyJ3AZAbwEP4MRZAFU7e0kq6ssOtUMMOsgZA2bYMypkyS8HDtJYHb3Wa8fPknN8Vd7cieifdE8j77qisyyw4wZDZD";
	
	public static ArrayList<String> getAdAccounts(){
		
		try{
			
			String custom_url = URL + "/" + VERSION + "/" + "me/adaccounts?fields=timezone_offset_hours_utc,account_id&limit=1000&access_token=" + SESSION_TOKEN;
			
			HttpClient reqclient = new DefaultHttpClient();
			HttpGet reqget = new HttpGet(custom_url);
			
			System.out.println("Sending GET request : " + custom_url);
			
			HttpResponse response = reqclient.execute(reqget);
			
			System.out.println("Response Code : " + response.getStatusLine().getStatusCode());
			
			StringBuffer buffer = new StringBuffer();
			BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
			String line = "";
			
			while((line = reader.readLine()) != null){
				
				buffer.append(line);
				
			}
			
			System.out.println("Response Content : " + buffer.toString());
			
			if(response.getStatusLine().getStatusCode() >= 200 && response.getStatusLine().getStatusCode() < 300){
				
				JSONObject responseObj = new JSONObject(buffer.toString());
				
				if(responseObj.has("data")){
					
					JSONArray dataObj = responseObj.getJSONArray("data");
					
					if(dataObj.length() > 0){
						
						ArrayList<String> adAccountList = new ArrayList<String>();
						
						for(int arr_i = 0; arr_i < dataObj.length(); arr_i++){
							
							JSONObject adAccountObj = dataObj.getJSONObject(arr_i);
							
							if(adAccountObj.has("account_id") && adAccountObj.has("timezone_offset_hours_utc")){
								
								int offset = (((((int)adAccountObj.getDouble("timezone_offset_hours_utc")) - 8)*(-1)) + 24);
								
								if(Math.abs((offset % 24) - Calendar.getInstance().getTime().getHours()) < 1) {
								
									System.out.println("ACCOUNT ID ADDED : " + adAccountObj.getString("account_id"));
									adAccountList.add(adAccountObj.getString("account_id"));
									
								}
								else {
									
									System.out.println("ACCOUNT ID : " + adAccountObj.getString("account_id") + " NOT ADDED.");
								
								}
							
							}
							else{
								continue;
							}
							
						}
						
						return adAccountList;
						
					}
					else{
						System.out.println("Response Message : The DATA object is there but is empty.");
						return null;						
					}
					
				}
				else{
					System.out.println("Response Message : The DATA object is noty found in the Response. Please check response.");
					return null;
				}
			
			}
			else{
				System.out.println("Response Message : The request failed for fetching AdAccounts.");
				return null;
			}
			
		}
		catch(Exception e){
			System.out.println("Exception : AdAccount - getAdAccounts()");
			System.out.println(e);
			return null;
		}
		
	}
	
}