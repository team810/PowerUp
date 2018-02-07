package org.usfirst.frc.team810.robot.commands.autonomous;

import org.usfirst.frc.team810.robot.commands.ToggleClaw;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class SwitchLR extends CommandGroup {
	
	public SwitchLR() {
		addSequential(new DriveForward(5));
		addSequential(new RotateToAngle(45));
		addSequential(new DriveForward(5));
		//addParallel(set arm height to up);
		addSequential(new ToggleClaw());
	}

}
