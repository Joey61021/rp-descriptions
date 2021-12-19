package com.rpdescriptions.plugin.services;

import com.rpdescriptions.plugin.Main;
import com.rpdescriptions.plugin.misc.Utils;
import org.bukkit.entity.Player;

public class DescriptionService {

    public static String getDescription(Player player) {
        if (Main.getDatabase.get("Descriptions." + player.getUniqueId()) == null) return null;
        if (!Main.getConfig.getBoolean("General.Colored-Descriptions")) {
            return Main.getDatabase.getString("Descriptions." + player.getUniqueId());
        }
        return Utils.color(Main.getDatabase.getString("Descriptions." + player.getUniqueId()));
    }
}
