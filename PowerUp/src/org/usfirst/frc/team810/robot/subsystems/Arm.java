package org.usfirst.frc.team810.robot.subsystems;

import org.usfirst.frc.team810.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Subsystem;

public class Arm extends Subsystem {
	
	public static double up; //TODO Find actual value for these constants
	public static double down;
	public static double up_area;
	public static double down_area;

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub

	}
	
	public static void initConstants() {
		down = RobotMap.pot.get();
		down_area = down + 1;
		up = down + 11.5;
		up_area = up - 1;
	}

}
