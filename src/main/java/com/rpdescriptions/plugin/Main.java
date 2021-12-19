package com.rpdescriptions.plugin;

import com.rpdescriptions.plugin.commands.ForceDescCmd;
import com.rpdescriptions.plugin.commands.SetDescCmd;
import com.rpdescriptions.plugin.commands.ViewDescCmd;
import com.rpdescriptions.plugin.listeners.InteractAtEntityListener;
import com.rpdescriptions.plugin.misc.Config;
import com.rpdescriptions.plugin.misc.PAPIExpansion;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

	public static Config getConfig;
	public static Config getDatabase;

	public void onEnable() {
		loadFiles();
		registerEvents();
		registerCommands();

		if (getServer().getPluginManager().getPlugin("PlaceholderAPI") != null)
			new PAPIExpansion().register();
	}
	
	private void loadFiles() {
		getConfig = new Config(this, getDataFolder(), "config", "config.yml");
		getDatabase = new Config(this, getDataFolder(), "database");
	}
	
	private void registerEvents() {
		getServer().getPluginManager().registerEvents(new InteractAtEntityListener(), this);
	}
	
	private void registerCommands() {
		registerCommandExecutor("forcedesc", new ForceDescCmd());
		registerCommandExecutor("setdesc", new SetDescCmd());
		registerCommandExecutor("viewdesc", new ViewDescCmd());
	}

	private void registerCommandExecutor(String name, CommandExecutor executor) {
		PluginCommand command = getCommand(name);
		if (command == null)
			getLogger().warning("Attempted to register command \"" + name + "\" but none was found in the plugin.yml");
		else
			command.setExecutor(executor);
	}
}
