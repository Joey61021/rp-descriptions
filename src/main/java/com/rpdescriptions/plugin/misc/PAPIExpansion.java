package com.rpdescriptions.plugin.misc;

import com.rpdescriptions.plugin.Main;
import com.rpdescriptions.plugin.services.DescriptionService;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class PAPIExpansion extends PlaceholderExpansion {

    @SuppressWarnings("unused")
	private JavaPlugin plugin;

    @Override
    public boolean persist() {
        return true;
    }

    @Override
    public boolean canRegister() {
        return true;
    }

    @Override
    public String getAuthor(){
        return "Texxyy";
    }

    @Override
    public String getIdentifier(){
        return "rpdescriptions";
    }

    @Override
    public String getVersion(){
        return "0.8.0";
    }

    @Override
    public String onPlaceholderRequest(Player player, String identifier) {
        if (player == null || !player.isOnline()) return null;
        if (identifier.equalsIgnoreCase("desc")) {
            return DescriptionService.getDescription(player) == null
                    ? Utils.color(Main.getConfig.getString("General.Player-Click.Messages.No-Description"))
                    : DescriptionService.getDescription(player);
        }
        return null;
    }
}
