package com.rpdescriptions.plugin.misc;

import com.rpdescriptions.plugin.services.DescriptionService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

@RequiredArgsConstructor
public class PAPIExpansion extends PlaceholderExpansion {

    @NonNull
    private final DescriptionService descriptionService;
    @NonNull
    private final Config             config;

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
            return descriptionService.getDescription(player) == null
                    ? Utils.color(config.getString("General.Player-Click.Messages.No-Description"))
                    : descriptionService.getDescription(player);
        }
        return null;
    }
}
