package com.billmanager.dao;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import com.billmanager.beans.Bill;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class BillManagerDao {
	
	public static Map<Integer, Bill> billMap = new HashMap<Integer, Bill>();

	public int createBill(Bill bill) {
		bill.setBillid(generateBillid());
		billMap.put(bill.getBillid(), bill);		
		return bill.getBillid();
	}

	public String getBill(int billid) {
		return calculateBillShare(billid);
	}
	
	public String getAllBill() {
		System.out.println("inside get all bill");
	
		/*Bill[] bills = new Bill[billMap.size()];
		int index = 0;
		for (Map.Entry<Integer, Bill> mapEntry : billMap.entrySet()) {
			bills[index] = mapEntry.getValue();
		    index++;
		}*/
		
		return calculateAllBillShare();
	}
	
	public int updateBill(int billid, Bill bill) {
		deleteBill(billid);
		bill.setBillid(billid);
		billMap.put(billid, bill);
		return bill.getBillid();
	}
	
	public int deleteBill(int billid) {
		Bill bill = billMap.remove(billid);
		return bill.getBillid();
	}
	
	public String calculateBillShare(int billid) {
		Bill bill = billMap.get(billid);
		
		double billAmount = bill.getBillAmount();
		String [] sharedBy = bill.getSharedBy();
		int noOfShare = (sharedBy.length + 1);
		double shareAmount = billAmount/noOfShare;
				
		JsonObject billJson = new JsonObject();
		
		billJson.addProperty("billid", bill.getBillid());
		billJson.addProperty("billAmount", bill.getBillAmount());
		billJson.addProperty("paidBy", bill.getPaidBy());
		
		JsonArray shares = new JsonArray();
		JsonObject share = new JsonObject();		
		share.addProperty(bill.getPaidBy(), shareAmount);

		for(String a: sharedBy) {
			share.addProperty(a, shareAmount);	
		}
		
		shares.add(share);
		billJson.add("sharedBy", shares);
		
		Gson gson = new GsonBuilder().create();
		return gson.toJson(billJson);	
	}
	
	public String calculateAllBillShare() {
		
		JsonArray bJson = new JsonArray();
		
		for (Map.Entry<Integer, Bill> mapEntry : billMap.entrySet()) {
			Bill bill = mapEntry.getValue();
			
			double billAmount = bill.getBillAmount();
			String [] sharedBy = bill.getSharedBy();
			int noOfShare = (sharedBy.length + 1);
			double shareAmount = billAmount/noOfShare;
					
			JsonObject billJson = new JsonObject();			
			billJson.addProperty("billid", bill.getBillid());
			billJson.addProperty("billAmount", bill.getBillAmount());
			billJson.addProperty("paidBy", bill.getPaidBy());
			
			JsonArray shares = new JsonArray();
			JsonObject share = new JsonObject();			
			share.addProperty(bill.getPaidBy(), shareAmount);

			for(String a: sharedBy) {
				share.addProperty(a, shareAmount);	
			}
			
			shares.add(share);
			billJson.add("sharedBy", shares);
			
			bJson.add(billJson);
		}		
		Gson gson = new GsonBuilder().create();
		return gson.toJson(bJson);	
	}
	
	public int generateBillid( ) {
		Random random = new Random(System.nanoTime() % 100000);
		int billid = random.nextInt(1000000000);		
		return billid;
	}
}