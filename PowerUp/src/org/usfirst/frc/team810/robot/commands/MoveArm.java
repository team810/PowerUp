package org.usfirst.frc.team810.robot.commands;

import org.usfirst.frc.team810.robot.Robot;
import org.usfirst.frc.team810.robot.RobotMap;
import org.usfirst.frc.team810.robot.subsystems.Arm;

import edu.wpi.first.wpilibj.AnalogPotentiometer;
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
		if ((speed < 0 && pot.get() <= Arm.DOWN) || (speed > 0 && pot.get() >= Arm.UP))
			speed = 0;
		else if ((speed < 0 && pot.get() <= Arm.DOWN_AREA) || (speed > 0 && pot.get() >= Arm.UP_AREA))
			speed *= .25;
		
		RobotMap.armMotor.set(speed);
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
