package org.usfirst.frc.team810.robot.subsystems;

import org.usfirst.frc.team810.robot.RobotMap;
import org.usfirst.frc.team810.robot.commands.Drive;

import edu.wpi.first.wpilibj.command.Subsystem;

public class DriveTrain extends Subsystem {

	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new Drive());
	}
	
	//Tank drive is used during teleop
	public void tankDrive(double left, double right) {
		RobotMap.robotDrive.tankDrive(left, right);
	}
	
	//Arcade drive is used during auto to make PID control easier
	public void arcadeDrive(double forward, double r) {
		RobotMap.robotDrive.arcadeDrive(forward, r);
	}

}
