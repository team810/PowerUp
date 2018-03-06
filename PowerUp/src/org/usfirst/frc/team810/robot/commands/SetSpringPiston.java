package org.usfirst.frc.team810.robot.commands;

import org.usfirst.frc.team810.robot.RobotMap;

import edu.wpi.first.wpilibj.command.InstantCommand;

public class SetSpringPiston extends InstantCommand {

	@Override
	protected void execute() {
		//RobotMap.springPiston.set(value);
		RobotMap.intakePiston.set(!RobotMap.intakePiston.get());
	}

}
