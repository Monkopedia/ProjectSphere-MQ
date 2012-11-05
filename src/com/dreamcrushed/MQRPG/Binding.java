package com.dreamcrushed.MQRPG;

import org.bukkit.Material;

public class Binding {
	public BindingType type;
	public Material material;
	public AbilityStatistic ability;
	
	public Binding(int matId, int bindType, AbilityStatistic ability) {
		for (BindingType t : BindingType.values()) {
			if (bindType == t.index) {
				this.type = t;
			}
		}
		material = Material.getMaterial(matId);
		this.ability = ability;
	}
	
	public Binding(AbilityStatistic ability) {
		material = Material.AIR;
		type = BindingType.NOBIND;
		this.ability = ability;
	}
	
	public int getMatId() {
		return material.getId();
	}

	public int getBindIndex() {
		return type.index;
	}
}
