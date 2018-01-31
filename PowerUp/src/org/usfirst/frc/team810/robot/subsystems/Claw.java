package org.usfirst.frc.team810.robot.subsystems;

import org.usfirst.frc.team810.robot.RobotMap;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Claw extends Subsystem {
	
	Solenoid clawL, clawR;
	
	public Claw() {
		clawL = RobotMap.clawL;
		clawR = RobotMap.clawR;
	}

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub

	}
	
	public void set(boolean value) {
		clawL.set(value);
		clawR.set(value);
	}
	
	public boolean get() {
		return clawL.get();
	}

}
