package com.dreamcrushed.MQRPG;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Material;

public class BindManager {
	private Map<Material, Binding>[] bindings;
	
	@SuppressWarnings("unchecked")
	public BindManager() {
		bindings = new Map[3];
		for (int i = 0; i < 3; i++) {
			bindings[i] = new HashMap<Material, Binding>();
		}
	}
	
	public void bind(Binding binding) {
		if (binding.type != BindingType.NOBIND) {
			if (bindings[binding.type.index].get(binding.material) != null) {
				unbind(bindings[binding.type.index].get(binding.material));
			}
			bindings[binding.type.index].put(binding.material, binding);
		}
	}

	public void unbind(Binding binding) {
		if (binding.type != BindingType.NOBIND) {
			if (binding.ability != null) {
				binding.ability.notifyUnbind();
			}
			bindings[binding.type.index].remove(binding.material);
		}
	}

	public Binding getBinding(BindingType type, Material mat) {
		return bindings[type.index].get(mat);
	}
}
