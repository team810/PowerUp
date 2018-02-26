package org.usfirst.frc.team810.robot.subsystems;

import org.usfirst.frc.team810.robot.RobotMap;
import org.usfirst.frc.team810.robot.commands.KeepArmStill;

import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Arm extends Subsystem {
	
	public static double up;
	public static double down;
	public static double up_area;
	public static double down_area;
	public static double middle;

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		setDefaultCommand(new KeepArmStill());
	}
	
	public void set(double speed) {
		AnalogPotentiometer pot = RobotMap.pot;
		
		RobotMap.armMotor.setInverted(speed < 0);
		
		SmartDashboard.putNumber("Move Arm Initial Speed", speed);
		
		if ((speed > 0 && pot.get() <= Arm.down) || (speed < 0 && pot.get() >= Arm.up))
			speed = 0;
		else if ((speed > 0 && pot.get() <= Arm.down_area) || (speed < 0 && pot.get() >= Arm.up_area))
			speed *= .5;
		
		SmartDashboard.putNumber("Move Arm Final Speed", speed);
		
		RobotMap.armMotor.set(Math.abs(speed));
	}
	
	public static void initConstants() {
		down = RobotMap.pot.get();
		down_area = down + 1.5;
		middle = down + 4;
		up = down + 10.2;
		up_area = up - 1.5;
		
		SmartDashboard.putNumber("Arm Up", up);
		SmartDashboard.putNumber("Arm Up Range", up_area);
		SmartDashboard.putNumber("Arm Down", down);
		SmartDashboard.putNumber("Arm Down Range", down_area);
	}
	
	public boolean isUp() {
		return RobotMap.pot.get() > Arm.middle;
	}

}
