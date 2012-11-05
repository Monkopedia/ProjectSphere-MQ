package com.dreamcrushed.MQRPG.Command;

public class AbilityCommand extends CompoundCommand {

	public AbilityCommand() {
		super("ability", "Compound Command for Ability Interface");
		commands.add(new ListCommand());
		commands.add(new BindCommand());
		commands.add(new UnbindCommand());
	}

}
