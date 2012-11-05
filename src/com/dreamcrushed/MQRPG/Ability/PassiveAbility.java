package com.dreamcrushed.MQRPG.Ability;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockDamageEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import com.dreamcrushed.MQRPG.BindingType;
import com.dreamcrushed.MQRPG.CostEvent;
import com.dreamcrushed.MQRPG.MQRPG;

public abstract class PassiveAbility extends Ability {

	private float foodCost;
	private int ticksPerCost;
	private Map<String, Boolean> isActive;

	public PassiveAbility(String name, String desc, float foodCost, int ticksPerCost) {
		super(name, desc);
		this.foodCost = foodCost;
		this.ticksPerCost = ticksPerCost;
		this.isActive = new HashMap<String, Boolean>();
	}

	@Override
	public boolean canBind(BindingType type) {
		if (type != BindingType.ATTACK) return true;
		return false;
	}

	@Override
	public void activate(Player player, PlayerInteractEvent event) {
		if (getActive(player)) {
			deactivate(player);
		} else {
			activate(player);
		}
	}

	@Override
	public void activate(Player player, BlockDamageEvent event) {
		if (getActive(player)) {
			deactivate(player);
		} else {
			activate(player);
		}
	}

	@Override
	public void activate(Player player, EntityDamageByEntityEvent event) {
		if (getActive(player)) {
			deactivate(player);
		} else {
			activate(player);
		}
	}

	private boolean getActive(Player player) {
		if (isActive.get(player.getName()) == null) isActive.put(player.getName(), false);
		
		return isActive.get(player.getName());
	}

	@Override
	public void activate(Player player) {
		MQRPG.costManager.addCostEvent(new CostEvent(player, foodCost, ticksPerCost, this));
		isActive.put(player.getName(), true);
		passiveStart(player);
	}

	@Override
	public void deactivate(Player player) {
		MQRPG.costManager.remCostEvent(player, this);
		isActive.put(player.getName(), false);
		passiveStop(player);
	}

	public void deactivate(Player player, String string) {
		isActive.put(player.getName(), false);
		passiveStop(player);
	}

	public abstract void passiveStart(Player player);

	public abstract void passiveStop(Player player);

}
