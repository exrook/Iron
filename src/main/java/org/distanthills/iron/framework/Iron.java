package org.distanthills.iron.framework;

import org.bukkit.plugin.java.*;
import java.net.MalformedURLException;
import java.net.URL;

public class Iron extends JavaPlugin{
	private ComponentLoader loader;
	public void onEnable() {
		
		URL url;
		try {
			url = new URL(this.getDataFolder().getAbsolutePath());
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}
		this.loader = new ClassLoader(url);
		loader.load();
	}
	
	public void onDisable() {
		loader.unload();
	}
}
