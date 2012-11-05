package com.dreamcrushed.MQRPG.Ability;

import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockDamageEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import com.dreamcrushed.MQRPG.BindingType;

public abstract class TargetActiveAbility extends Ability {

	private float foodCost;

	public TargetActiveAbility(String name, String desc, float foodCost) {
		super(name, desc);
		this.foodCost = foodCost;
	}

	@Override
	public boolean canBind(BindingType type) {
		if (type == BindingType.ATTACK) return true;
		return false;
	}

	@Override
	public void activate(Player player, EntityDamageByEntityEvent event) {
		if (player.getExhaustion() >= foodCost) {
			player.setExhaustion(player.getExhaustion() - foodCost);
			activatePaid(player, event);
		}
	}

	public abstract void activatePaid(Player player, EntityDamageByEntityEvent event);

	@Override
	public void activate(Player player, PlayerInteractEvent event) 	{	}

	@Override
	public void activate(Player player, BlockDamageEvent event) 	{	}

	@Override
	public void activate(Player player) {
		player.sendMessage(name + " is an activated ability and cannot be activated");
	}

	@Override
	public void deactivate(Player player) {
		player.sendMessage(name + " is an activated ability and cannot be deactivated");
	}

}
