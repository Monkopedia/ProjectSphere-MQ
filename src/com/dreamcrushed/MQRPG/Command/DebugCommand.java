package com.dreamcrushed.MQRPG.Command;

import org.bukkit.command.Command;
import org.bukkit.entity.Player;

public class DebugCommand extends PlayerCommand {

	public DebugCommand() {
		super("debug", "Used for debugging");
	}

	@Override
	public void onPlayerCommand(Player sender, Command command, String label,
			String[] args) {
		for (int i = 0; i < args.length; i++) {
			sender.sendMessage(i + ": " + args[i]);
		}
	}

}
