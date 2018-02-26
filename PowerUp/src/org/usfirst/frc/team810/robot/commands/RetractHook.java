package org.usfirst.frc.team810.robot.commands;

import org.usfirst.frc.team810.robot.Robot;
import org.usfirst.frc.team810.robot.RobotMap;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.TimedCommand;

public class RetractHook extends TimedCommand {

	public RetractHook(double timeout) {
		super(timeout);
		requires(Robot.climber);
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected void execute() {
		RobotMap.hookMotor.setInverted(false);
		if (DriverStation.getInstance().getMatchTime() <= 30)
			RobotMap.hookMotor.set(.35);
	}

	@Override
	protected void end() {
		RobotMap.hookMotor.set(0);
	}

	@Override
	protected void interrupted() {
		end();
	}

}
