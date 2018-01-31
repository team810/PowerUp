package org.usfirst.frc.team810.robot.commands;

import org.usfirst.frc.team810.robot.Robot;
import org.usfirst.frc.team810.robot.RobotMap;

import edu.wpi.first.wpilibj.command.TimedCommand;

public class RetractHook extends TimedCommand {

	public RetractHook(double timeout) {
		super(timeout);
		requires(Robot.climber);
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return super.isFinished();
	}

	@Override
	protected void execute() {
		RobotMap.hookMotor.set(-.35);
	}

	@Override
	protected void end() {
		RobotMap.hookMotor.disable();
	}

	@Override
	protected void interrupted() {
		end();
	}

}
