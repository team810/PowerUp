package org.usfirst.frc.team810.robot.subsystems;

import org.usfirst.frc.team810.robot.RobotMap;
import org.usfirst.frc.team810.robot.commands.Drive;

import edu.wpi.first.wpilibj.command.Subsystem;

public class DriveTrain extends Subsystem {

	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new Drive());
	}
	
	public void tankDrive(double left, double right) {
		RobotMap.robotDrive.tankDrive(left, right);
	}
	
	public void arcadeDrive(double forward, double r) {
		RobotMap.robotDrive.arcadeDrive(forward, r);
	}

}
