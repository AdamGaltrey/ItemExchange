package com.adamki11s.itemexchange.exchange;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PlayerProfile {

	private List<SellEntry> sellEntries = new ArrayList<SellEntry>();

	public void addEntry(SellEntry e) {
		this.sellEntries.add(e);
	}

	public boolean removeEntry(SellEntry e) {
		Iterator<SellEntry> it = sellEntries.iterator();
		while (it.hasNext()) {
			SellEntry e1 = it.next();
			if (e1.getTimeListed() == e.getTimeListed()) {
				it.remove();
				return true;
			}
		}
		return false;
	}
	
	public int getEntries(){
		return sellEntries.size();
	}
	
	public boolean hasMaxEntries(){
		return sellEntries.size() >= 9;
	}

}
