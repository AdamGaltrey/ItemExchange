package com.adamki11s.itemexchange;

import java.util.logging.Logger;

import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class ItemExchange extends JavaPlugin {
	
	private static Plugin p;
	private static Logger l;
	
	@Override
	public void onEnable(){
		p = this;
		l = getLogger();
		Config.init();
	}
	
	public static Plugin getPlugin(){
		return p;
	}
	
	public static Logger getLog(){
		return l;
	}

}