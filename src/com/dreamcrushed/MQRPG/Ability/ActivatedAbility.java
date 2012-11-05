package com.dreamcrushed.MQRPG.Ability;

import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockDamageEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import com.dreamcrushed.MQRPG.BindingType;

public abstract class ActivatedAbility extends Ability {

	private float foodCost;

	public ActivatedAbility(String name, String desc, float foodCost) {
		super(name, desc);
		this.foodCost = foodCost;
	}
	
	@Override
	public boolean canBind(BindingType type) {
		return true;
	}

	@Override
	public void activate(Player player, PlayerInteractEvent event) {
		activate(player);
	}

	@Override
	public void activate(Player player, EntityDamageByEntityEvent event) {
		activate(player);
	}

	@Override
	public void activate(Player player, BlockDamageEvent event) {
		activate(player);
	}
	
	@Override
	public void activate(Player player) {
		if (player.getExhaustion() >= foodCost) {
			player.setExhaustion(player.getExhaustion() - foodCost);
			activatePaid(player);
		}
	}

	public abstract void activatePaid(Player player);

	@Override
	public void deactivate(Player sender) {
		sender.sendMessage(name + " is an activated ability and cannot be deactivated");
	}

}
