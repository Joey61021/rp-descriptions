package com.rpdescriptions.plugin.services;

import com.rpdescriptions.plugin.misc.Config;
import com.rpdescriptions.plugin.misc.Utils;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.bukkit.entity.Player;

@RequiredArgsConstructor
public class DescriptionService {

    @NonNull
    private final Config databaseConfig;

    public String getDescription(Player player) {
        if (databaseConfig.get("Descriptions." + player.getUniqueId()) == null) return null;
        String description = databaseConfig.getString("Descriptions." + player.getUniqueId());
        return databaseConfig.getBoolean("General.Colored-Descriptions") ? Utils.color(description) : description;
    }
}
