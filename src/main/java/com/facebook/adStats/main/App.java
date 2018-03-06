package com.facebook.adStats.main;

import java.util.ArrayList;

import com.facebook.ads.common.AdAccount;
import com.facebook.ads.common.AdsFetcher;
import com.google.api.services.bigquery.Bigquery;
import com.google.api.services.bigquery.model.TableDataInsertAllRequest.Rows;
import com.google.bigquery.main.BQOperations;
import com.google.bigquery.main.GAuthenticate;

public class App {
	
	public static ArrayList<Rows> datachunk = new ArrayList<Rows>();

	public static void main(String[] args){
		
		ArrayList<String> listAdAccounts = AdAccount.getAdAccounts();

		System.out.println("SIZE AD ACCOUNTS : " + listAdAccounts.size());
		
		Bigquery bigquery = GAuthenticate.getAuthenticated();
		
		System.out.println(bigquery);
		
		if(null != bigquery){
			
			if(BQOperations.StructureValidate(bigquery)){
	    	
				System.out.println("Response Message : Validated the Structure of the Big Query.");
				
			}
					
			else{
					
				System.out.println("Response Message : Couldn't validate the Structure of the Big Query.");
				System.exit(0);
				
			}
	    			
		}
		
		else{
					
			System.out.println("Response Message : Couldn't Establish connection with Big Query.");
			System.exit(0);
			
		}
		
		if(null != listAdAccounts && listAdAccounts.size() > 0){
	
			for(String adAccount : listAdAccounts){
					
				int status = AdsFetcher.getAdsData(adAccount);
					
				if(status == 200) {
					System.out.println("Response Message : Data added successfully.");
				}
				else if(status == 500) {
					status = AdsFetcher.getAdsData(adAccount);
					continue;
				}
				else {
					continue;
				}

				System.out.println(datachunk.size());
				
				if(datachunk.size() > 0){
						
					Boolean result = BQOperations.insertDataRows(bigquery, datachunk);
						
					if(result){
								
						System.out.println("Response Message : Data Added successfully to Big Query.");
	
					}
					else{
								
						System.out.println("Response Message : Error while adding data to Big Query.");
						
					}
					
					datachunk.clear();
							
				}
	
				else{
					
					System.out.println("Response Message : DataChunk size is too small to be entered to BQ.");
					
				}

			}
		
		}
		
		else{
			
			System.out.println("Response Message : The Ad Account List is either empty or null.");
			
		}
  
	}

}