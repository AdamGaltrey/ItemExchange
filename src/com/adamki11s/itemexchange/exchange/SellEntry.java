package com.adamki11s.itemexchange.exchange;

import org.bukkit.Material;

public class SellEntry {
	
	/*
	 * An exchange entry to the system
	 */
	
	private final String sellerUUID;
	private final int quantity, costPerUnit;
	private final Material item;
	private final long time;
	private int sold;
	
	public SellEntry(String sellerUUID, Material item, int quantity, int costPerUnit, int sold, long time) {
		this.sellerUUID = sellerUUID;
		this.item = item;
		this.quantity = quantity;
		this.costPerUnit = costPerUnit;
		this.sold = sold;
		this.time = time;
	}

	public String getSellerUUID() {
		return sellerUUID;
	}

	public Material getItem() {
		return item;
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