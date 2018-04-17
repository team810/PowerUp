package org.usfirst.frc.team810.robot.commands;

import org.usfirst.frc.team810.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class Drive extends Command {
	
	private double lastL = 0, lastR = 0;
	
	public Drive() {
		requires(Robot.driveTrain);
	}

	@Override
	protected void execute() {
		double left = Robot.oi.leftStick.getRawAxis(1);
		double right = Robot.oi.rightStick.getRawAxis(1);
		
		//Add dead zone to joysticks
		if (Math.abs(left) < .2)
			left = 0;
		if (Math.abs(right) < .2)
			right = 0;
		
		//Limit speed going backwards to prevent tipping
		if (left > .8)
			left = .8;
		if (right > .8)
			right = .8;
		
		//Limit acceleration via joystick values
		double maxChange = .02;
		if ((Math.signum(left) == Math.signum(right) && Math.signum(lastL) == Math.signum(lastR))) {
			if (left > lastL + maxChange)
				left = lastL + maxChange;
			if (left < lastL - maxChange)
				left = lastL - maxChange;
			if (right > lastR + maxChange)
				right = lastR + maxChange;
			if (right < lastR - maxChange)
				right = lastR - maxChange;
		}
		lastL = left;
		lastR = right;
		
		Robot.driveTrain.tankDrive(right, left);
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

}
