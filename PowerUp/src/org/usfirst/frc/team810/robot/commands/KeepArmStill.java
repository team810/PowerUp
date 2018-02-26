package org.usfirst.frc.team810.robot.commands;

import org.usfirst.frc.team810.robot.Robot;
import org.usfirst.frc.team810.robot.RobotMap;
import org.usfirst.frc.team810.robot.subsystems.Arm;

import edu.wpi.first.wpilibj.command.Command;

public class KeepArmStill extends Command {
	
	public KeepArmStill() {
		requires(Robot.arm);
	}

	@Override
	protected void execute() {
		if (RobotMap.pot.get() > Arm.down + .2)
			RobotMap.armMotor.set(0);
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

}
