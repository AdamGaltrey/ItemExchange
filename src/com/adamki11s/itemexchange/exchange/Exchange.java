package com.adamki11s.itemexchange.exchange;

import java.util.ArrayList;
import java.util.List;

import com.adamki11s.itemexchange.ItemExchange;

public class Exchange {
	
	private static List<Entry> buyableEntries;
	private static List<Entry> soldEntries;
	
	public static void initExchange(List<Entry> loaded){
		buyableEntries = new ArrayList<Entry>();
		soldEntries = new ArrayList<Entry>();
		
		for(Entry e : loaded){
			if(e.isPurchasable()){
				buyableEntries.add(e);
			} else {
				soldEntries.add(e);
			}
		}
		
		ItemExchange.getLog().info(String.format("Loaded a total of %d entries.", loaded.size()));
	}

}
