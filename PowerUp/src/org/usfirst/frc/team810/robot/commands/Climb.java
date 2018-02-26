package org.usfirst.frc.team810.robot.commands;

import org.usfirst.frc.team810.robot.Robot;
import org.usfirst.frc.team810.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

public class Climb extends Command {
	
	double speed;
	
	public Climb(double speed) {
		requires(Robot.climber);
		this.speed = speed;
	}

	@Override
	protected void execute() {
		RobotMap.climbMotor.setInverted(speed < 0);
		RobotMap.climbMotor.set(Math.abs(speed));
	}

	@Override
	protected void end() {
		RobotMap.climbMotor.disable();
	}

	@Override
	protected void interrupted() {
		end();
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return !(Robot.oi.climb.get() && Robot.oi.climbSafety.get());
	}

}
