package org.usfirst.frc.team810.robot.commands;

import org.usfirst.frc.team810.robot.Robot;
import org.usfirst.frc.team810.robot.RobotMap;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.InstantCommand;

public class ToggleClaw extends InstantCommand {
	
	public ToggleClaw() {
		requires(Robot.claw);
	}

	@Override
	protected void execute() {
		RobotMap.claw.set((RobotMap.claw.get() == DoubleSolenoid.Value.kForward) ? DoubleSolenoid.Value.kReverse : DoubleSolenoid.Value.kForward);
		
		RobotMap.springPiston.set((RobotMap.claw.get() == DoubleSolenoid.Value.kForward) ? DoubleSolenoid.Value.kForward : DoubleSolenoid.Value.kReverse);
	}

}
