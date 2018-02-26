package org.usfirst.frc.team810.robot.subsystems;

import org.usfirst.frc.team810.robot.RobotMap;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Intake extends Subsystem {
	
	Spark left, right;
	
	public Intake() {
		left = RobotMap.intakeL;
		right = RobotMap.intakeR;
	}

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub

	}
	
	//Allows only 1 parameter if both motors are the same speed for maximum programmer laziness
	public void set(double speed) {
		set(speed, speed);
	}

	public void set(double speedL, double speedR)  {
		left.setInverted(speedL < 0);
		right.setInverted(speedR < 0);
		
		if (speedL == 0)
			left.disable();
		else
			left.set(Math.abs(speedL));
		
		if (speedR == 0)
			right.disable();
		else
			right.set(Math.abs(speedR));
	}
	
}
