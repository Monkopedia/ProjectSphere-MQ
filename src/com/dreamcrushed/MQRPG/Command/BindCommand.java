package com.dreamcrushed.MQRPG.Command;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.dreamcrushed.MQRPG.AbilityStatistic;
import com.dreamcrushed.MQRPG.AbilityStatistic.Status;
import com.dreamcrushed.MQRPG.BindingType;
import com.dreamcrushed.MQRPG.MQRPG;

public class BindCommand extends PlayerCommand {

	public BindCommand() {
		super("bind", "Bind an ability activation to the use of an item");
	}

	@Override
	public void onPlayerCommand(Player sender, Command command, String label,
			String[] args) {
		if (args.length == 2) {
			AbilityStatistic abil = MQRPG.abilityManager.getAbility(sender.getName(), args[0]);
			
			if (abil == null) {
				sender.sendMessage(ChatColor.RED + "You have no ability " + args[0]);
			} else if (abil.getStatus() == Status.AVAILABLE) {
				sender.sendMessage(ChatColor.RED + "You have not purchased the ability " + args[0]);
				sender.sendMessage(ChatColor.RED + "You must first purchase it using experience levels");
			} else if (abil.getStatus() == Status.GAINED) {
				BindingType type = BindingType.fromType(args[1]);
				
				if (type != null) { 
					ItemStack item = sender.getItemInHand();
					
					if ((item != null) && (item.getType() != Material.AIR)) {
						abil.bind(type, item.getType());
						sender.sendMessage(ChatColor.GREEN + abil.getAbilityName() + " is now bound to " + item.getType() + " " + type);
					} else {
						sender.sendMessage(ChatColor.RED + "AIR Cannot be bound to!");
					}
				} else {
					sender.sendMessage(ChatColor.RED + args[1] + "is not a valid BINDTYPEs");
					sender.sendMessage("Valid BINDTYPEs:");
					for (int i = 0; i < 3; i++) {
						BindingType t = BindingType.fromInt(i);
						sender.sendMessage(t.name + " - " + t.desc);
					}
				}
			} else {
				sender.sendMessage(ChatColor.RED + "You have no ability " + args[0]);
			}
			return;
		}
		sender.sendMessage("Usage: /ability bind ABILITY BINDTYPE");
		sender.sendMessage("Valid BINDTYPEs:");
		for (int i = 0; i < 3; i++) {
			BindingType type = BindingType.fromInt(i);
			sender.sendMessage(type.name + " - " + type.desc);
		}
	}

}
