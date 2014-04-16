package com.adamki11s.itemexchange;

import java.util.List;
import java.util.logging.Logger;

import net.milkbowl.vault.economy.Economy;

import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import com.adamki11s.itemexchange.database.Database;
import com.adamki11s.itemexchange.exchange.Entry;
import com.adamki11s.itemexchange.exchange.Exchange;
import com.adamki11s.itemexchange.exchange.ProfileManager;

public class ItemExchange extends JavaPlugin {

	private static Plugin p;
	private static Logger l;

	public static Economy economy;

	@Override
	public void onEnable() {
		p = this;
		l = getLogger();
		Config.init();

		List<Entry> entries = Database.loadEntries();
		Exchange.initExchange(entries);
		ProfileManager.initProfiles(entries);
		
		setupEconomy();
	}

	private boolean setupEconomy() {
		RegisteredServiceProvider<Economy> economyProvider = getServer().getServicesManager().getRegistration(
				net.milkbowl.vault.economy.Economy.class);
		if (economyProvider != null) {
			economy = economyProvider.getProvider();
		}

		return (economy != null);
	}

	public static Plugin getPlugin() {
		return p;
	}

	public static Logger getLog() {
		return l;
	}

}
