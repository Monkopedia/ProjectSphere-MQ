package com.dreamcrushed.MQRPG.Command;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;

import com.dreamcrushed.MQRPG.AbilityStatistic;
import com.dreamcrushed.MQRPG.AbilityStatistic.Status;
import com.dreamcrushed.MQRPG.MQRPG;

public class ActivateCommand extends PlayerCommand {

	public ActivateCommand() {
		super("activate", "Activate a given ability");
	}

	@Override
	public void onPlayerCommand(Player sender, Command command, String label,
			String[] args) {
		if (args.length == 1) {
			AbilityStatistic abil = MQRPG.abilityManager.getAbility(sender.getName(), args[0]);
			
			if (abil == null) {
				sender.sendMessage(ChatColor.RED + "You have no ability " + args[0]);
			} else if (abil.getStatus() == Status.AVAILABLE) {
				sender.sendMessage(ChatColor.RED + "You have not purchased the ability " + args[0]);
				sender.sendMessage(ChatColor.RED + "You must first purchase it using experience levels");
			} else if (abil.getStatus() == Status.GAINED) {
				abil.getAbility().activate(sender);
			} else {
				sender.sendMessage(ChatColor.RED + "You have no ability " + args[0]);
			}
			return;
		}
		sender.sendMessage("Usage: /ability bind ABILITY");
	}

}
