package com.google.bigquery.main;

import java.util.ArrayList;

import com.google.api.services.bigquery.Bigquery;
import com.google.api.services.bigquery.Bigquery.Tabledata.List;
import com.google.api.services.bigquery.model.Dataset;
import com.google.api.services.bigquery.model.DatasetList;
import com.google.api.services.bigquery.model.DatasetList.Datasets;
import com.google.api.services.bigquery.model.DatasetReference;
import com.google.api.services.bigquery.model.Table;
import com.google.api.services.bigquery.model.TableDataInsertAllRequest;
import com.google.api.services.bigquery.model.TableDataInsertAllRequest.Rows;
import com.google.api.services.bigquery.model.TableDataInsertAllResponse;
import com.google.api.services.bigquery.model.TableFieldSchema;
import com.google.api.services.bigquery.model.TableList;
import com.google.api.services.bigquery.model.TableList.Tables;
import com.google.api.services.bigquery.model.TableReference;
import com.google.api.services.bigquery.model.TableSchema;

public class BQOperations {
	
	private static final String PROJECT_ID = "stellar-display-145814";
	private static final String DATASET_ID = "facebook_raw";
	private static final String TABLE_ID = "fb_ads_daily_stats_timezone";
	
	public static Boolean StructureValidate(Bigquery bigquery){
		
		try {
			
//			if(createDataset(bigquery)){
//				
//				if(createTable(bigquery)){
//					
//					return true;
//					
//				}
//				else{
//					System.out.println("Response Message : Couldn't create Table. Returning Back with FALSE status.");
//					return false;
//				}
//				
//			}
//			else{
//				System.out.println("Response Message : Couldn't find Dataset. Returning Back with FALSE status.");
//				return false;
//			}
			
			return true;
		
		} catch (Exception e) {
		
			System.out.println(e);
			return false;	
		
		}
		
	}
	
	public static Boolean createDataset(Bigquery bigquery){

		try{
		
			DatasetList datasets = bigquery.datasets().list(PROJECT_ID).execute();
			
			Boolean datasetCreate = true;
			
			for(Datasets dataset : datasets.getDatasets()){
				
				if(dataset.getId().equalsIgnoreCase(PROJECT_ID + ":" + DATASET_ID)){
					
					datasetCreate = false;
					
				}
				
			}
			
			if(datasetCreate){
				Dataset datasetresponse = bigquery.datasets().insert(PROJECT_ID, new Dataset().setId(DATASET_ID).setKind("bigquery#dataset").setDatasetReference(new DatasetReference().setProjectId(PROJECT_ID).setDatasetId(DATASET_ID))).execute();
				System.out.println(datasetresponse);
				return true;
			}
			else{
				System.out.println("Response Message : Dataset is already created in Big Query.");
				return true;
			}
		
		}catch(Exception e){
			System.out.println(e);
			return false;
		}
		
	}

	public static Boolean createTable(Bigquery bigquery){
		
		try{
			
			TableList tables = bigquery.tables().list(PROJECT_ID, DATASET_ID).execute();
			
			System.out.println(tables);
			
			if(null != tables && null != tables.getTables()){
			
				System.out.println("Inside IF.");

				Boolean deleteTable = false;
				
				for(Tables table : tables.getTables()){
					
					if(table.getId().equalsIgnoreCase(PROJECT_ID + ":" + DATASET_ID + "." + TABLE_ID)){
						deleteTable = true;
					}
					
				}
			
				if(deleteTable){
					bigquery.tables().delete(PROJECT_ID, DATASET_ID, TABLE_ID).execute();
				}
				
			}	
			
			ArrayList<TableFieldSchema> fields = new ArrayList<TableFieldSchema>();
			
			fields.add(new TableFieldSchema().setName("ad_id").setType("STRING"));
			fields.add(new TableFieldSchema().setName("ad_name").setType("STRING"));
			fields.add(new TableFieldSchema().setName("adset_id").setType("STRING"));
			fields.add(new TableFieldSchema().setName("adset_name").setType("STRING"));
			fields.add(new TableFieldSchema().setName("campaign_id").setType("STRING"));
			fields.add(new TableFieldSchema().setName("campaign_name").setType("STRING"));
			fields.add(new TableFieldSchema().setName("account_id").setType("STRING"));
			fields.add(new TableFieldSchema().setName("account_name").setType("STRING"));
			fields.add(new TableFieldSchema().setName("start_date").setType("STRING"));
			fields.add(new TableFieldSchema().setName("end_date").setType("STRING"));
			fields.add(new TableFieldSchema().setName("impressions").setType("INTEGER"));
			fields.add(new TableFieldSchema().setName("clicks").setType("INTEGER"));
			fields.add(new TableFieldSchema().setName("inline_link_clicks").setType("INTEGER"));
			fields.add(new TableFieldSchema().setName("spend").setType("FLOAT"));
			fields.add(new TableFieldSchema().setName("reach").setType("INTEGER"));
			fields.add(new TableFieldSchema().setName("oc_fb_pixel_purchase_priceValue").setType("FLOAT"));
			fields.add(new TableFieldSchema().setName("fb_pixel_purchase_28d_click_value").setType("FLOAT"));
			fields.add(new TableFieldSchema().setName("fb_pixel_purchase_28d_view_value").setType("FLOAT"));
			fields.add(new TableFieldSchema().setName("oc_fb_pixel_lead").setType("INTEGER"));
			fields.add(new TableFieldSchema().setName("oc_fb_pixel_lead_28d_click").setType("INTEGER"));
			fields.add(new TableFieldSchema().setName("oc_fb_pixel_lead_28d_view").setType("INTEGER"));
			fields.add(new TableFieldSchema().setName("leadgenOther").setType("INTEGER"));
			fields.add(new TableFieldSchema().setName("leadgenOther_28d_view").setType("INTEGER"));
			fields.add(new TableFieldSchema().setName("leadgenOther_28d_click").setType("INTEGER"));
			fields.add(new TableFieldSchema().setName("oc_fb_pixel_purchase_value").setType("INTEGER"));
			fields.add(new TableFieldSchema().setName("oc_fb_pixel_purchase_28d_click").setType("INTEGER"));
			fields.add(new TableFieldSchema().setName("oc_fb_pixel_purchase_28d_view").setType("INTEGER"));
			fields.add(new TableFieldSchema().setName("post_like_value").setType("INTEGER"));
			fields.add(new TableFieldSchema().setName("post_engagement").setType("INTEGER"));
			fields.add(new TableFieldSchema().setName("page_engagement").setType("INTEGER"));
			fields.add(new TableFieldSchema().setName("fb_pixel_view_content").setType("INTEGER"));
			fields.add(new TableFieldSchema().setName("fb_pixel_view_content_28d_click").setType("INTEGER"));
			fields.add(new TableFieldSchema().setName("fb_pixel_view_content_28d_view").setType("INTEGER"));
			fields.add(new TableFieldSchema().setName("landing_page_view").setType("INTEGER"));
			fields.add(new TableFieldSchema().setName("landing_page_view_28d_click").setType("INTEGER"));
			fields.add(new TableFieldSchema().setName("landing_page_view_28d_view").setType("INTEGER"));
			fields.add(new TableFieldSchema().setName("fb_pixel_add_payment_info").setType("INTEGER"));
			fields.add(new TableFieldSchema().setName("fb_pixel_add_payment_info_28d_click").setType("INTEGER"));
			fields.add(new TableFieldSchema().setName("fb_pixel_add_payment_info_28d_view").setType("INTEGER"));
			fields.add(new TableFieldSchema().setName("fb_pixel_initiate_checkout").setType("INTEGER"));
			fields.add(new TableFieldSchema().setName("fb_pixel_initiate_checkout_28d_click").setType("INTEGER"));
			fields.add(new TableFieldSchema().setName("fb_pixel_initiate_checkout_28d_view").setType("INTEGER"));
			fields.add(new TableFieldSchema().setName("fb_pixel_add_to_cart").setType("INTEGER"));
			fields.add(new TableFieldSchema().setName("fb_pixel_add_to_cart_28d_click").setType("INTEGER"));
			fields.add(new TableFieldSchema().setName("fb_pixel_add_to_cart_28d_view").setType("INTEGER"));
			fields.add(new TableFieldSchema().setName("account_currency").setType("STRING"));
			fields.add(new TableFieldSchema().setName("frequency").setType("FLOAT"));
			fields.add(new TableFieldSchema().setName("created_at").setType("STRING"));
			
			Table content = new Table();
			content.setSchema(new TableSchema().setFields(fields));
			content.setId(TABLE_ID);
			content.setKind("bigquery#table");
			content.setTableReference(new TableReference().setProjectId(PROJECT_ID).setDatasetId(DATASET_ID).setTableId(TABLE_ID));
			
			Table table = bigquery.tables().insert(PROJECT_ID, DATASET_ID, content).execute();
			System.out.println(table);
			return true;
			
		}
		catch(Exception e){
			System.out.println(e);
			return false;
		}
		
	}
	
	public static Boolean insertDataRows(Bigquery bigquery, ArrayList<Rows> datachunk) {
	
		try {
			
			for(Rows row : datachunk){
				System.out.println(row.getJson().toString());
			}
			
			System.out.println(bigquery);

			TableDataInsertAllRequest content = new TableDataInsertAllRequest();
			content.setKind("bigquery#tableDataInsertAllRequest");
			content.setRows(datachunk);
			
			TableDataInsertAllResponse response = bigquery.tabledata().insertAll(PROJECT_ID, DATASET_ID, TABLE_ID, content).execute();
		
			System.out.println(response.toPrettyString());
			
			return true;
		
		} catch (Exception e) {
		
			System.out.println(e);
		
			return false;
			
		}
	
	}
	
}