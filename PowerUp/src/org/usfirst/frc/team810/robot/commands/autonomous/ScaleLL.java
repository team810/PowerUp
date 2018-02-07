package org.usfirst.frc.team810.robot.commands.autonomous;

import org.usfirst.frc.team810.robot.commands.ToggleClaw;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class ScaleLL extends CommandGroup {

	public ScaleLL() {
		addSequential(new DriveForward(10));
		//addParallel(set arm height to up);
		addSequential(new ToggleClaw());
	}
	
}
