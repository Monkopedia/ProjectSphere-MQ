package com.dreamcrushed.MQRPG;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.plugin.java.JavaPlugin;

import com.dreamcrushed.MQRPG.Command.AbilityCommand;
import com.dreamcrushed.MQRPG.Command.DebugCommand;
import com.dreamcrushed.MQRPG.Command.PlayerCommand;
import com.theminequest.MineQuest.API.Managers;

public class MQRPG extends JavaPlugin {
	public List<PlayerCommand> commands;
	
	public static Statistics statistics;

	public static AbilityManager abilityManager;
	
	public static ExhaustionCostManager costManager;
	
	public static Map<String, BindManager> binding;
	
	public static BindManager getBindings(String player) {
		return binding.get(player);
	}
	
	@Override
	public void onEnable() {
		if (!getServer().getPluginManager().isPluginEnabled("MineQuest")) {
			getServer().getLogger().severe("============= MineQuest-RPG2 ]===============");
			getServer().getLogger().severe("MineQuest is required for MineQuest-RPG2!");
			getServer().getLogger().severe("Please install MineQuest first!");
			getServer().getLogger().severe("You can find the latest version here:");
			getServer().getLogger().severe("http://dev.bukkit.org/server-mods/minequest/");
			getServer().getLogger().severe("==============================================");
			setEnabled(false);
			return;
		}
		
		binding = new HashMap<String, BindManager>();
		abilityManager = new AbilityManager();
		costManager = new ExhaustionCostManager(500);
		
		getServer().getPluginManager().registerEvents(new BindingListener(), this);
		
		Managers.getStatisticManager().registerStatistic(AbilityStatistic.class);
		commands = new ArrayList<PlayerCommand>();
		commands.add(new DebugCommand());
		commands.add(new AbilityCommand());
		
		for (PlayerCommand c : commands) {
			getCommand(c.name).setExecutor(c);
		}
	}
	
	@Override
	public void onDisable() {
		costManager.stop();
	}
}
