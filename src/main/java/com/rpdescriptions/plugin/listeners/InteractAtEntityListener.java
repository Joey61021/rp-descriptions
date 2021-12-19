package com.rpdescriptions.plugin.listeners;

import com.rpdescriptions.plugin.Main;
import com.rpdescriptions.plugin.cooldown.CooldownManager;
import com.rpdescriptions.plugin.cooldown.TimeSpan;
import com.rpdescriptions.plugin.misc.Utils;
import com.rpdescriptions.plugin.services.DescriptionService;
import org.bukkit.Sound;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;

import java.util.concurrent.TimeUnit;

public class InteractAtEntityListener implements Listener {

    @EventHandler
    private void onInteractAtEntity(PlayerInteractAtEntityEvent event) {
        if (!(event.getRightClicked().getType().equals(EntityType.PLAYER) && Main.getConfig.getBoolean("General.Player-Click.Enabled"))) return;
        Player player = event.getPlayer();
        for (String worlds : Main.getConfig.getStringList("General.Player-Click.Disabled-Worlds"))
            if (event.getRightClicked().getWorld().getName().equalsIgnoreCase(worlds)) return;
        if (CooldownManager.checkCooldown("click_" + player.getUniqueId())) {
            if (Main.getConfig.getBoolean("General.Player-Click.Sound.Enabled"))
                player.playSound(player.getLocation(), Sound.valueOf(Main.getConfig.getString("General.Player-Click.Sound.Type").toUpperCase()), 1, 1);
            Player target = (Player) event.getRightClicked();
            for (String text : Main.getConfig.getStringList("General.Player-Click.Messages.Player-Clicked"))
                player.sendMessage(Utils.color(text)
                        .replace("%username%", target.getName())
                        .replace("%description%", DescriptionService.getDescription(target) == null ?
                                Utils.color(Main.getConfig.getString("General.Player-Click.Messages.No-Description")) : DescriptionService.getDescription(target)));
            CooldownManager.setNow("click_" + player.getUniqueId(), new TimeSpan(Main.getConfig.getInt("General.Player-Click.Cooldown-Ticks") * 50L, TimeUnit.MILLISECONDS));
        }
    }
}
