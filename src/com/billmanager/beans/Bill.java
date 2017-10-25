package com.billmanager.beans;

public class Bill {
	private int billid;
	private double billAmount;
	private String paidBy;
	private String [] sharedBy;
	
	public int getBillid() {
		return billid;
	}
	public void setBillid(int billid) {
		this.billid = billid;
	}
	public double getBillAmount() {
		return billAmount;
	}
	public void setBillAmount(double billAmount) {
		this.billAmount = billAmount;
	}
	public String getPaidBy() {
		return paidBy;
	}
	public void setPaidBy(String paidBy) {
		this.paidBy = paidBy;
	}
	public String[] getSharedBy() {
		return sharedBy;
	}
	public void setSharedBy(String[] sharedBy) {
		this.sharedBy = sharedBy;
	}
}