package org.usfirst.frc.team810.robot.commands;

import org.usfirst.frc.team810.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class Intake extends Command {
	
	double speed;
	
	public Intake(double speed) {
		requires(Robot.intake);
		this.speed = speed;
	}

	@Override
	protected void execute() {
		Robot.intake.set(speed);
	}

	@Override
	protected void end() {
		Robot.intake.set(0);
	}

	@Override
	protected void interrupted() {
		end();
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

}
