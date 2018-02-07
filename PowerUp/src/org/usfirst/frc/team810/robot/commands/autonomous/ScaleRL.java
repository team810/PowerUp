package org.usfirst.frc.team810.robot.commands.autonomous;

import org.usfirst.frc.team810.robot.commands.ToggleClaw;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class ScaleRL extends CommandGroup {
	
	public ScaleRL() {
		addSequential(new DriveForward(5));
		addSequential(new RotateToAngle(-90));
		addSequential(new DriveForward(5));
		addSequential(new RotateToAngle(90));
		//addParallel(set arm height to up);
		addSequential(new DriveForward(5));
		addSequential(new ToggleClaw());
	}

}
