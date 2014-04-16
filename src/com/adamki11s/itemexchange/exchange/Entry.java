package com.adamki11s.itemexchange.exchange;

public class Entry {
	
	/*
	 * An exchange entry to the system
	 */
	
	private final String sellerUUID;
	private final int itemID, quantity, costPerUnit;
	private final long time;
	private int sold;
	
	public Entry(String sellerUUID, int itemID, int quantity, int costPerUnit, int sold, long time) {
		this.sellerUUID = sellerUUID;
		this.itemID = itemID;
		this.quantity = quantity;
		this.costPerUnit = costPerUnit;
		this.sold = sold;
		this.time = time;
	}

	public String getSellerUUID() {
		return sellerUUID;
	}

	public int getItemID() {
		return itemID;
	}

	public int getListedQuantity() {
		return quantity;
	}

	public int getCostPerUnit() {
		return costPerUnit;
	}

	public long getTimeListed() {
		return time;
	}

	public int getQuantitySold() {
		return sold;
	}
	
	/*
	 * Used to stop searching through entries which have already been sold out
	 */
	public boolean isPurchasable(){
		return sold < quantity;
	}
	
	public void itemsSold(int quantity){
		//increment the entry by the amount given and update sql database
	}
}
