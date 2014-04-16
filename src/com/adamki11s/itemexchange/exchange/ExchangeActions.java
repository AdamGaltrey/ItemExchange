package com.adamki11s.itemexchange.exchange;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.adamki11s.itemexchange.database.Database;

public class ExchangeActions {
	
	public static void addEntry(Player p, int cpu){
		PlayerProfile pp = ProfileManager.getPlayerProfile(p);
		if(pp.hasMaxEntries()){
			p.sendMessage(ChatColor.RED + "You do not have any free slots!");
		} else {
			ItemStack is = p.getItemInHand();
			if(is == null){
				p.sendMessage(ChatColor.RED + "You are not holding an item!");
			} else {
				//item is not null
				if(is.hasItemMeta() && is.getItemMeta().hasEnchants()){
					p.sendMessage(ChatColor.RED + "Enchanted items cannot be sold!");
				} else {
					//item is not enchanted
					Entry e = new Entry(p.getUniqueId().toString(), is.getType(), is.getAmount(), cpu, 0, System.currentTimeMillis());
					//add to player profile
					pp.addEntry(e);
					//add to sql database, once added to sql the data will be pushed to the live list
					Database.addEntryAsync(e, p);
				}
			}
		}
	}

}
