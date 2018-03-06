package org.usfirst.frc.team810.robot.commands.autonomous;

import org.usfirst.frc.team810.robot.commands.ToggleClaw;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class JustForward extends CommandGroup {
	
	public JustForward(double dist) {
		addSequential(new ToggleClaw());
		addSequential(new DriveForward(dist));
	}

}
