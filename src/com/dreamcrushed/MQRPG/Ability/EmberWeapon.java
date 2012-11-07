package com.dreamcrushed.MQRPG.Ability;

import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockDamageEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import com.dreamcrushed.MQRPG.BindingType;

public class EmberWeapon extends Ability {

	public EmberWeapon() {
		super("Ember Weapon", "Chance to add some fire damage");
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

	@Override
	public boolean canBind(BindingType type) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void activate(Player player) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deactivate(Player player) {
		// TODO Auto-generated method stub
		
	}

}
