package com.dreamcrushed.MQRPG.Ability;

import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockDamageEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import com.dreamcrushed.MQRPG.BindingType;

public abstract class Ability {
	
	public String name;
	public String desc;
	
	public Ability(String name, String desc) {
		this.name = name;
		this.desc = desc;
	}

	public abstract boolean canBind(BindingType type);

	public abstract void activate(Player player, PlayerInteractEvent event);

	public abstract void activate(Player player, EntityDamageByEntityEvent event);

	public abstract void activate(Player player, BlockDamageEvent event);

	public abstract void activate(Player player);

	public abstract void deactivate(Player player);

}
