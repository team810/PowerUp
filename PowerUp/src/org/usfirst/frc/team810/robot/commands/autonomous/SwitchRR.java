package org.usfirst.frc.team810.robot.commands.autonomous;

import org.usfirst.frc.team810.robot.commands.ToggleClaw;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class SwitchRR extends CommandGroup {
	
	public SwitchRR() {
		addSequential(new DriveForward(5));
		//addParallel(set arm height to up);
		addSequential(new ToggleClaw());
	}

}
