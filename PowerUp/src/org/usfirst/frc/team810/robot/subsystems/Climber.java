package org.usfirst.frc.team810.robot.subsystems;

import org.usfirst.frc.team810.robot.commands.KeepHookStill;

import edu.wpi.first.wpilibj.command.Subsystem;

public class Climber extends Subsystem {

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		setDefaultCommand(new KeepHookStill());
	}

}
