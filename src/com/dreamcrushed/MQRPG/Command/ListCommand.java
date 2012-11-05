package com.dreamcrushed.MQRPG.Command;

import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.entity.Player;

import com.dreamcrushed.MQRPG.AbilityStatistic;
import com.dreamcrushed.MQRPG.MQRPG;

public class ListCommand extends PlayerCommand {
	
	public final static int pageLength = 10;

	public ListCommand() {
		super("list", "List Current Abilities");
	}

	@Override
	public void onPlayerCommand(Player sender, Command command, String label,
			String[] args) {
		List<AbilityStatistic> abils = MQRPG.abilityManager.getGainedAbils(sender.getName());
		int page = 0;
		int pages = (abils.size() + pageLength - 1) / pageLength;
		if (args.length > 0) {
			page = Integer.parseInt(args[0]) - 1;
		}
		sender.sendMessage("Ability List (Page " + (page+1) + "/" + pages + ")");
		for (int i = page * pageLength; (i < ((page + 1)* pageLength)) && (i < abils.size()); i++) {
			sender.sendMessage("  " + abils.get(i).getAbility().name.replaceAll(" ", "") + " - " + abils.get(i).getAbility().desc);
		}
	}

}
