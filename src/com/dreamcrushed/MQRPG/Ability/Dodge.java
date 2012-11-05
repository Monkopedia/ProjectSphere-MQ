package com.dreamcrushed.MQRPG.Ability;

import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockDamageEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class Dodge extends Ability {

	public Dodge() {
		super("Dodge", "Aura with chance to dodge any attack");
	}

	@Override
	public void activate(Player player, PlayerInteractEvent event) {
		// TODO Auto-generated method stub

	}

	@Override
	public void activate(Player player, EntityDamageByEntityEvent event) {
		// TODO Auto-generated method stub

	}

	@Override
	public void activate(Player player, BlockDamageEvent event) {
		// TODO Auto-generated method stub

	}

}
