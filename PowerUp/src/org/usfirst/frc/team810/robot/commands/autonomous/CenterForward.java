package org.usfirst.frc.team810.robot.commands.autonomous;

import org.usfirst.frc.team810.robot.commands.ToggleClaw;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class CenterForward extends CommandGroup {
	
	public CenterForward() {
		addSequential(new ToggleClaw());
		addSequential(new DriveForward(24));
		addSequential(new RotateToAngle(27));
		addSequential(new DriveForward(77));
		addSequential(new Wait(.5));
		addSequential(new DriveForward(-77));
	}

}
