package com.adamki11s.itemexchange.exchange;

import org.bukkit.Material;

public class BuyEntry {
	
	private final String buyerUUID;
	private final Material item;
	private final int quantity, maxCPU;
	private final long timeSubmitted;
	private int quantityBought;
	
	public BuyEntry(String buyerUUID, Material item, int quantity, int maxCPU, int quantityBought, long timeSubmitted) {
		this.buyerUUID = buyerUUID;
		this.item = item;
		this.quantity = quantity;
		this.maxCPU = maxCPU;
		this.quantityBought = quantityBought;
		this.timeSubmitted = timeSubmitted;
	}
	
	public long getTimeSubmitted(){
		return timeSubmitted;
	}

	public String getBuyerUUID() {
		return buyerUUID;
	}

	public Material getItem() {
		return item;
	}

	public int getQuantity() {
		return quantity;
	}

	public int getMaxCPU() {
		return maxCPU;
	}

	public int getQuantityBought() {
		return quantityBought;
	}
	
	public void itemsBought(int quantity){
		//increment the entry by the amount given and update sql database
	}

}
