package com.dreamcrushed.MQRPG.Command;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.dreamcrushed.MQRPG.Binding;
import com.dreamcrushed.MQRPG.BindingType;
import com.dreamcrushed.MQRPG.MQRPG;

public class UnbindCommand extends PlayerCommand {

	public UnbindCommand() {
		super("unbind", "Remove all bindings from a specific ability");
	}

	@Override
	public void onPlayerCommand(Player sender, Command command, String label,
			String[] args) {
		ItemStack item = sender.getItemInHand();
		
		if ((item != null) && (item.getType() != Material.AIR)) {
			for (int i = 0; i < 3; i++) {
				BindingType type = BindingType.fromInt(i);
				Binding bind = MQRPG.getBindings(sender.getName()).getBinding(type, item.getType());
				
				if (bind != null) {
					MQRPG.getBindings(sender.getName()).unbind(bind);
				}
			}
			sender.sendMessage(ChatColor.GREEN + "" + item.getType() + " is now unbound");
		} else {
			sender.sendMessage(ChatColor.RED + "AIR Cannot have anything bound to it!");
		}
	}

}
