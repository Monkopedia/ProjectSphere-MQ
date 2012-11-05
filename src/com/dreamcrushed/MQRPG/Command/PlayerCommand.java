package com.dreamcrushed.MQRPG.Command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public abstract class PlayerCommand implements CommandExecutor {

	final public String name;
	final public String desc;
	
	public PlayerCommand(String name, String description) {
		this.name = name;
		this.desc = description;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label,
			String[] args) {
		if (sender instanceof Player) {
			if (name.equals(command.getName())) {
				onPlayerCommand((Player)sender, command, label, args);
				return true;
			}
		}
		
		return false;
	}

	public abstract void onPlayerCommand(Player sender, Command command, String label,
			String[] args);
}
