package com.dreamcrushed.MQRPG.Command;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;

public class CompoundCommand extends PlayerCommand {
	
	public List<PlayerCommand> commands;

	public CompoundCommand(String name, String description) {
		super(name, description);
		commands = new ArrayList<PlayerCommand>();
	}

	@Override
	public void onPlayerCommand(Player sender, Command command, String label,
			String[] args) {
		String subcommand = args[0];
		String[] nargs = new String[args.length-1];
		for (int i = 1; i < args.length; i++) {
			nargs[i-1] = args[i];
		}
		args = nargs;
		for (PlayerCommand c : commands) {
			if (c.name.equals(subcommand)) {
				c.onPlayerCommand(sender, command, label, args);
				return;
			}
		}
		sender.sendMessage(ChatColor.RED + "Unknown Command /" + name + " " + subcommand);
	}

}
