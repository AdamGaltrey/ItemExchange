package com.adamki11s.itemexchange;

import java.io.File;

public class Files {
	
	//directories
	public static final File ROOT = new File("plugins" + File.separator + "ItemExchange");
	
	//files
	public static final File CONFIG = new File(ROOT + File.separator + "config.yml");
	
	public static void init(){
		ROOT.mkdir();
	}

}
