package org.usfirst.frc.team810.robot.commands;

import org.usfirst.frc.team810.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class Drive extends Command {
	
	public Drive() {
		requires(Robot.driveTrain);
	}

	@Override
	protected void execute() {
		double left = -Robot.oi.leftStick.getRawAxis(1);
		double right = -Robot.oi.rightStick.getRawAxis(1);
		
		if (Math.abs(left) < .2)
			left = 0;
		if (Math.abs(right) < .2)
			right = 0;
		
		Robot.driveTrain.tankDrive(left, right);
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

}
