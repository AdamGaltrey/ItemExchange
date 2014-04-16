package com.adamki11s.itemexchange.exchange;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.entity.Player;

import com.adamki11s.itemexchange.ItemExchange;

public class ProfileManager {
	
	//maps UUID to player profile
	private static Map<String, PlayerProfile> profiles = new HashMap<String, PlayerProfile>();
	
	public static void initProfiles(List<Entry> entries){
		for(Entry e : entries){
			if(!profiles.containsKey(e.getSellerUUID())){
				profiles.put(e.getSellerUUID(), new PlayerProfile());
			}
			profiles.get(e.getSellerUUID()).addEntry(e);
		}
		ItemExchange.getLog().info(String.format("Loaded %d player profiles from exchange database.", profiles.size()));
	}
	
	public static PlayerProfile getPlayerProfile(Player p){
		String u = p.getUniqueId().toString();
		if(profiles.containsKey(u)){
			return profiles.get(u);
		} else {
			PlayerProfile pp = new PlayerProfile();
			profiles.put(u, pp);
			return pp;
		}
	}

}
