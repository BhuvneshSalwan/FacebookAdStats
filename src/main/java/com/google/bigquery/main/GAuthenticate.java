package com.google.bigquery.main;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.bigquery.Bigquery;
import com.google.api.services.bigquery.BigqueryScopes;

public class GAuthenticate {

	public static final HttpTransport TRANSPORT = new NetHttpTransport();
	public static final JsonFactory JSON_FACTORY = new JacksonFactory();
	
	public static Bigquery getAuthenticated(){
		
		Bigquery bigquery = null;
		
		if((bigquery = createAuthorizedClient()) != null){
			return bigquery;
		}
		else{
			return null;
		}
	
	}
	
	public static Bigquery createAuthorizedClient(){

		GoogleCredential credential = null;
		
		if((credential = authorize()) != null){
			return new Bigquery.Builder(TRANSPORT, JSON_FACTORY, credential).build();
		}
		else{
			return null;
		}
		
	}
	
	public static GoogleCredential authorize(){
	
		try{

//			GoogleCredential credential = GoogleCredential.fromStream(new FileInputStream("C:\\Program Files\\Java\\projects\\FacebookAdStats\\target\\Cloud Project 1-a5efe977981f.json"))
//				    .createScoped(BigqueryScopes.all());
			
			GoogleCredential credential = GoogleCredential.fromStream(new FileInputStream("/home/bhuvnesh/Desktop/Cloud Project 1-a5efe977981f.json"))
				    .createScoped(BigqueryScopes.all());
			
			return credential;

		}
		catch(Exception e){
			System.out.println("Exception : Authenticate - Authorize Method" );
			System.out.println(e);
			return null;
		}
	
	}
	
//	public static final HttpTransport TRANSPORT = new NetHttpTransport();
//	public static final JsonFactory JSON_FACTORY = new JacksonFactory();
//	public static FileDataStoreFactory dataStoreFactory;
//	static GoogleClientSecrets clientSecrets = loadClientSecrets();
//	private static final java.io.File DATA_STORE_DIR = new java.io.File(System.getProperty("user.home"), ".store/bq_fb_ads_data");
////	private static final String CLIENTSECRETS_LOCATION = "C:\\Program Files\\Java\\projects\\FacebookAdsData\\target\\client_secret_154911048506-ign21bljsc6jmrdvrd84tqt3a6omk5be.apps.googleusercontent.com.json";
//	private static final String CLIENTSECRETS_LOCATION = "/home/bhuvnesh/google_credentials/client_secret_154911048506-ign21bljsc6jmrdvrd84tqt3a6omk5be.apps.googleusercontent.com.json";
//	
//	public static Bigquery getAuthenticated(){
//		
//		Bigquery bigquery = null;
//		
//		if((bigquery = createAuthorizedClient()) != null){
//			return bigquery;
//		}
//		else{
//			return null;
//		}
//	
//	}
//	
//	public static Bigquery createAuthorizedClient(){
//
//		Credential credential = null;
//		
//		if((credential = authorize()) != null){
//			return new Bigquery(TRANSPORT, JSON_FACTORY, credential);
//		}
//		else{
//			return null;
//		}
//		
//	}
//	
//	public static Credential authorize(){
//	
//		try{
//			
//			dataStoreFactory = new FileDataStoreFactory(DATA_STORE_DIR);
//			
//		    GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
//		            TRANSPORT, JSON_FACTORY, clientSecrets, BigqueryScopes.all()).setDataStoreFactory(
//		            dataStoreFactory).build();
//		    
//		    return new AuthorizationCodeInstalledApp(flow, new LocalServerReceiver()).authorize("user");	
//			
////			GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(TRANSPORT, JSON_FACTORY, "154911048506-ign21bljsc6jmrdvrd84tqt3a6omk5be.apps.googleusercontent.com", "xfWoXuMHBFsWLxteKKrf3hVh", BigqueryScopes.all()).build();
////			
////			//GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow(TRANSPORT, JSON_FACTORY, "154911048506-ign21bljsc6jmrdvrd84tqt3a6omk5be.apps.googleusercontent.com", "xfWoXuMHBFsWLxteKKrf3hVh", BigqueryScopes.all());
////			
////			return new AuthorizationCodeInstalledApp(flow, new LocalServerReceiver()).authorize("user");
//
//		}
//		catch(Exception e){
//			System.out.println("Exception : Authenticate - Authorize Method" );
//			System.out.println(e);
//			return null;
//		}
//	
//	}
//	
//	private static GoogleClientSecrets loadClientSecrets() {
//	
//		try {
//			InputStream inputStream = new FileInputStream(CLIENTSECRETS_LOCATION);
//		    Reader reader = new InputStreamReader(inputStream);
//		    GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(new JacksonFactory(),reader);
//		    return clientSecrets;
//		} catch (Exception e) {
//		    System.out.println("Could not load client secrets file " + CLIENTSECRETS_LOCATION);
//		    e.printStackTrace();
//		}
//		
//		return null;
//	
//	}
	
}