package com.dreamcrushed.MQRPG;

import org.bukkit.entity.Player;

import com.dreamcrushed.MQRPG.Ability.PassiveAbility;

public class CostEvent {
	public Player player;
	private float foodCost;
	private int ticksPerCost;
	private int tickCount;
	public PassiveAbility ability;

	public CostEvent(Player player, float foodCost, int ticksPerCost, PassiveAbility ability) {
		this.player = player;
		this.foodCost = foodCost;
		this.ticksPerCost = ticksPerCost;
		this.ability = ability;
		this.tickCount = 0;
	}

	public boolean tick() {
		tickCount++;
		if (tickCount == ticksPerCost) {
			if (player.getExhaustion() >= foodCost) {
				player.setExhaustion(player.getExhaustion() - foodCost);
			} else {
				deactivate();
				return true;
			}
			tickCount = 0;
		}
		return false;
	}

	public void deactivate() {
		ability.deactivate(player, "Ran out of Exhaustion");
		MQRPG.costManager.remCostEvent(this);
	}

}
