package com.dreamcrushed.MQRPG;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.Player;

import com.dreamcrushed.MQRPG.Ability.PassiveAbility;

public class ExhaustionCostManager {
	
	private ExhaustionCostThread thread;
	private List<CostEvent> costs;

	public ExhaustionCostManager(int tickDelay) {
		costs = new ArrayList<CostEvent>();
		
		this.thread = new ExhaustionCostThread(tickDelay);
		thread.start();
	}
	
	public void addCostEvent(CostEvent event) {
		synchronized (costs) {
			costs.add(event);
		}
	}
	
	public void remCostEvent(CostEvent event) {
		synchronized (costs) {
			costs.remove(event);
		}
	}

	public void remCostEvent(Player player, PassiveAbility passiveAbility) {
		synchronized (costs) {
			for (int i = 0; i < costs.size(); i++) {
				CostEvent cost = costs.get(i);
				if (player.equals(cost.player) && passiveAbility.equals(cost.ability)) {
					costs.remove(i);
					i--;
				}
			}
		}
	}

	public void costTick() {
		synchronized (costs) {
			for (int i = 0; i < costs.size(); i++) {
				if (costs.get(i).tick()) {
					costs.remove(i--);
				}
			}
		}
	}
	
	public void stop() {
		thread.mRun = false;
	}

	private class ExhaustionCostThread extends Thread {
		private int tickDelay;
		public boolean mRun;

		public ExhaustionCostThread(int tickDelay) {
			this.tickDelay = tickDelay;
			mRun = true;
		}
		
		@Override
		public void run() {
			while (mRun) {
				costTick();
				try {
					sleep(tickDelay);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
