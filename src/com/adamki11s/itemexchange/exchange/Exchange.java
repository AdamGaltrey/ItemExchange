package com.adamki11s.itemexchange.exchange;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.adamki11s.itemexchange.ItemExchange;

public class Exchange {
	
	private static List<SellEntry> buyableEntries;
	private static List<SellEntry> soldEntries;
	
	public static void initExchange(List<SellEntry> loaded){
		buyableEntries = Collections.synchronizedList(new ArrayList<SellEntry>());
		soldEntries = Collections.synchronizedList(new ArrayList<SellEntry>());
		
		for(SellEntry e : loaded){
			if(e.isPurchasable()){
				buyableEntries.add(e);
			} else {
				soldEntries.add(e);
			}
		}
		
		ItemExchange.getLog().info(String.format("Loaded a total of %d exchange entries.", loaded.size()));
	}
	
	public static void addEntryAsync(SellEntry e){
		buyableEntries.add(e);
	}

}
