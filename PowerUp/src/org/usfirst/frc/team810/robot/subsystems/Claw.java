package org.usfirst.frc.team810.robot.subsystems;

import org.usfirst.frc.team810.robot.RobotMap;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Claw extends Subsystem {
	
	Solenoid claw;
	
	public Claw() {
		claw = RobotMap.claw;
	}

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub

	}
	
	public void set(boolean value) {
		claw.set(value);
	}
	
	public boolean get() {
		return claw.get();
	}

}
