package com.dreamcrushed.MQRPG;

import org.bukkit.Material;

import com.alta189.simplesave.Field;
import com.alta189.simplesave.Id;
import com.alta189.simplesave.Table;
import com.dreamcrushed.MQRPG.Ability.Ability;
import com.theminequest.MineQuest.API.Tracker.Statistic;

@Table("mq_abilities")
public class AbilityStatistic extends Statistic {
	public enum Status {
		NOT_AVAILABLE,
		AVAILABLE,
		GAINED;
	}

	@Id
	private long abilityId;
	
	@Field
	private String ability;
	
	@Field 
	private int status;
	
	@Field
	private int bindType;
	
	@Field
	private int bindMat;

	@Override
	public void setup() {
		MQRPG.getBindings(getPlayerName()).bind(new Binding(bindMat, bindType, this));
	}
	
	public Ability getAbility() {
		return MQRPG.abilityManager.getAbility(ability);
	}
	
	public Status getStatus() {
		switch (status) {
		case 1:
			return Status.AVAILABLE;
		case 2:
			return Status.GAINED;
		}
		return Status.NOT_AVAILABLE;
	}
	
	public void setStatus(Status status) {
		switch (status) {
		case AVAILABLE:
			this.status = 1;
			break;
		case GAINED:
			this.status = 2;
			break;
		default:
			this.status = 0;
			break;
		}
	}

	public void notifyUnbind() {
		bindType = -1;
		bindMat = 0;
	}

	public void setup(Ability ability) {
		this.ability = ability.name;
	}

	public String getAbilityName() {
		return ability;
	}

	public void bind(BindingType type, Material mat) {
		this.bindMat = mat.getId();
		this.bindType = type.index;
		MQRPG.getBindings(getPlayerName()).bind(new Binding(bindMat, bindType, this));
	}

	public void unbind() {
		MQRPG.getBindings(getPlayerName()).unbind(new Binding(bindMat, bindType, this));
	}

}
