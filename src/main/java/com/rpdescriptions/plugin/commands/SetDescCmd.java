package com.rpdescriptions.plugin.commands;

import com.rpdescriptions.plugin.Main;
import com.rpdescriptions.plugin.misc.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetDescCmd implements CommandExecutor {
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) {
			Utils.noConsole();
			return false;
		}
		Player player = (Player) sender;
		if (args.length == 0) {
			if (Main.getDatabase.get("Descriptions." + player.getUniqueId()) == null) {
				player.sendMessage(Utils.color(Main.getConfig.getString("Commands.SetDesc.Enter-Description")));
				return false;
			}
			Main.getDatabase.set("Descriptions." + player.getUniqueId(), null);
			Main.getDatabase.save();
			player.sendMessage(Utils.color(Main.getConfig.getString("Commands.SetDesc.Description-Reset")));
			return false;
		}
		StringBuilder sb = new StringBuilder();
		for (String arg : args) sb.append(arg).append(" ");
		Main.getDatabase.set("Descriptions." + player.getUniqueId(), sb.toString());
		Main.getDatabase.save();
		player.sendMessage(Utils.color(Main.getConfig.getString("Commands.SetDesc.Description-Set")));
		return false;
	}
}
