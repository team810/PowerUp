package org.usfirst.frc.team810.robot.commands;

import org.usfirst.frc.team810.robot.Robot;
import org.usfirst.frc.team810.robot.RobotMap;

import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;

public class MoveArm extends Command {
	
	double speed;
	AnalogPotentiometer pot = RobotMap.pot;
	
	public MoveArm(double speed) {
		requires(Robot.arm);
		this.speed = speed;
	}

	@Override
	protected void interrupted() {
		end();
	}

	@Override
	protected void execute() {
		Robot.arm.set(speed);
		DriverStation.reportWarning("Pot value: " + pot.get(), false);
	}

	@Override
	protected void end() {
		RobotMap.armMotor.disable();
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

}
