package com.dreamcrushed.MQRPG;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.dreamcrushed.MQRPG.AbilityStatistic.Status;
import com.dreamcrushed.MQRPG.Ability.Ability;
import com.dreamcrushed.MQRPG.Ability.ChillWeapon;
import com.dreamcrushed.MQRPG.Ability.Dodge;
import com.dreamcrushed.MQRPG.Ability.Durability;
import com.dreamcrushed.MQRPG.Ability.EmberWeapon;
import com.dreamcrushed.MQRPG.Ability.LesserHeal;
import com.dreamcrushed.MQRPG.Ability.PlantGenetics;
import com.dreamcrushed.MQRPG.Ability.SparkWeapon;
import com.dreamcrushed.MQRPG.Ability.SpawnPig;
import com.theminequest.MineQuest.API.Managers;

public class AbilityManager {
	
	public Map<String, List<AbilityStatistic>> statistics;
	
	public Ability[] abilities;
	public Ability[] baseAbilities;
	public Map<String, Ability> abilNames;
	
	public AbilityManager() {
		statistics = new HashMap<String, List<AbilityStatistic>>();
		abilities = new Ability[] {
			new ChillWeapon(),
			new Dodge(),
			new Durability(),
			new LesserHeal(),
			new EmberWeapon(),
			new PlantGenetics(),
			new SparkWeapon(),
			new SpawnPig()
		};
		baseAbilities = abilities.clone();
		
		abilNames = new HashMap<String, Ability>();
		for (Ability ability : abilities) {
			abilNames.put(ability.name.replaceAll(" ", ""), ability);
		}
	}
	
	private List<AbilityStatistic> getBaseAbilities(String name) {
		List<AbilityStatistic> ret = new ArrayList<AbilityStatistic>();
		
		for (Ability ability : baseAbilities) {
			AbilityStatistic abilStat = (AbilityStatistic)Managers.getStatisticManager().createStatistic(name, AbilityStatistic.class);
			abilStat.setup(ability);
			abilStat.notifyUnbind();
			abilStat.setStatus(Status.GAINED);
			ret.add(abilStat);
		}
		
		return ret;
	}
	
	public void login(String name) {
		List<AbilityStatistic> abilList = Managers.getStatisticManager().getAllStatistics(name, AbilityStatistic.class);
		
		if ((abilList == null) || (abilList.size() == 0)) {
			abilList = getBaseAbilities(name);
		}
		System.out.println(abilList.size() + " Abilities");
		
		statistics.put(name, abilList);
	}

	public void logout(String name) {
		for (AbilityStatistic statistic : getAbils(name)) {
			Managers.getStatisticManager().saveStatistic(statistic, AbilityStatistic.class);
		}
	}
	
	public List<AbilityStatistic> getAbils(String name) {
		return statistics.get(name);
	}

	public Ability getAbility(String ability) {
		return abilNames.get(ability.replaceAll(" ", ""));
	}

	public List<AbilityStatistic> getGainedAbils(String name) {
		List<AbilityStatistic> ret = new ArrayList<AbilityStatistic>();
		
		for (AbilityStatistic abil : getAbils(name)) {
			if (abil.getStatus() == Status.GAINED) {
				ret.add(abil);
			}
		}
		
		return ret;
	}

	public AbilityStatistic getAbility(String name, String string) {
		for (AbilityStatistic ability : getAbils(name)) {
			if (ability.getAbilityName().equalsIgnoreCase(string)) {
				return ability;
			}
		}
		return null;
	}

}
