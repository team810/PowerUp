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
	
	public void set(double speed) {
		if (speed == 0) {
			left.disable();
			right.disable();
		}
		else {
			left.set(speed);
			right.set(speed);
		}
	}

	public void set(double speedL, double speedR)  {
		if (speedL == 0)
			left.disable();
		else
			left.set(speedL);
		if (speedR == 0)
			right.disable();
		else
			right.set(speedR);
	}
	
}
