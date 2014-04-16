package com.adamki11s.itemexchange.exchange;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PlayerProfile {

	private List<Entry> entries = new ArrayList<Entry>();

	public void addEntry(Entry e) {
		this.entries.add(e);
	}

	public boolean removeEntry(Entry e) {
		Iterator<Entry> it = entries.iterator();
		while (it.hasNext()) {
			Entry e1 = it.next();
			if (e1.getTimeListed() == e.getTimeListed()) {
				it.remove();
				return true;
			}
		}
		return false;
	}
	
	public int getEntries(){
		return entries.size();
	}
	
	public boolean hasMaxEntries(){
		return entries.size() >= 9;
	}

}
