package com.rpdescriptions.plugin.cooldown;

import java.util.HashMap;
import java.util.Map;

public class CooldownManager {

    private static Map<String, Cooldown> cooldowns = new HashMap<>();

    public static boolean registerCooldown(String label, Cooldown cooldown) {
        if (cooldowns.containsKey(label)) {
            cooldowns.put(label, cooldown);
            return false;
        }
        cooldowns.put(label, cooldown);
        return true;
    }

    public static boolean checkCooldown(String label) {
        if (!cooldowns.containsKey(label)) return true;
        Cooldown cooldown = cooldowns.get(label);
        return System.currentTimeMillis() - cooldown.timestamp >= cooldown.timeSpan.timeUnit.toMillis(cooldown.timeSpan.amount);
    }

    public static void setNow(String label, TimeSpan timeSpan) {
        if (cooldowns.containsKey(label)) {
            cooldowns.get(label).timestamp = System.currentTimeMillis();
            return;
        }
        registerCooldown(label, new Cooldown(System.currentTimeMillis(), timeSpan));
    }
}
