package org.usfirst.frc.team810.robot.commands;

import org.usfirst.frc.team810.robot.RobotMap;

import edu.wpi.first.wpilibj.command.InstantCommand;

public class SetSpringPiston extends InstantCommand {
	
	boolean value;
	
	public SetSpringPiston(boolean value) {
		this.value = value;
	}

	@Override
	protected void execute() {
		RobotMap.springPiston.set(value);
	}

}
