package org.usfirst.frc.team810.robot.commands;

import org.usfirst.frc.team810.robot.Robot;

import edu.wpi.first.wpilibj.command.InstantCommand;

public class ToggleClaw extends InstantCommand {
	
	public ToggleClaw() {
		requires(Robot.claw);
	}

	@Override
	protected void execute() {
		Robot.claw.set(!Robot.claw.get());
	}

}
