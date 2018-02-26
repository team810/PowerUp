package org.usfirst.frc.team810.robot.commands;

import org.usfirst.frc.team810.robot.Robot;
import org.usfirst.frc.team810.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

public class KeepHookStill extends Command{

	public KeepHookStill() {
		requires(Robot.climber);
	}

	@Override
	protected void execute() {
		RobotMap.hookMotor.set(0);
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}
}
