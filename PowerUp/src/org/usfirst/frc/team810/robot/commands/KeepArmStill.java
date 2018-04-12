package org.usfirst.frc.team810.robot.commands;

import org.usfirst.frc.team810.robot.Robot;
import org.usfirst.frc.team810.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

public class KeepArmStill extends Command {
	
	private double target;
	
	private double kP = .8;
	
	@Override
	protected void initialize() {
		target = RobotMap.pot.get();
	}

	public KeepArmStill() {
		requires(Robot.arm);
	}

	@Override
	protected void execute() {
		double current = RobotMap.pot.get();
		double value = (target - current) * kP;
		
		if (value > .8)
			value = .8;
		if (value < -.8)
			value = -.8;
		
		RobotMap.armMotor.set(value);
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

}
