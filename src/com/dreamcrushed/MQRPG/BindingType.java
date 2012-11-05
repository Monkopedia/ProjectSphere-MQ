package com.dreamcrushed.MQRPG;

public enum BindingType {
	ATTACK(0, "Activate after attacking (Required for abilities with target)"),
	LEFT(1, "Activate when left clicking"),
	RIGHT(2, "Activate when right clicking"),
	NOBIND(-1, "Not Bound to any input");
	
	final public int index;
	final public String name;
	final public String desc;

	private BindingType(int index, String desc) {
		this.index = index;
		this.name = this + "";
		this.desc = desc;
	}
	
	public static BindingType fromType(String name) {
		for (BindingType type : values()) {
			if (type.name.equalsIgnoreCase(name)) {
				return type;
			}
		}
		return null;
	}

	public static BindingType fromInt(int i) {
		for (BindingType type : values()) {
			if (type.index == i) {
				return type;
			}
		}
		return null;
	}
}
