package org.usfirst.frc.team810.robot.commands.autonomous;

import org.usfirst.frc.team810.robot.commands.ToggleClaw;
import org.usfirst.frc.team810.robot.subsystems.Arm;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class JustForward extends CommandGroup {
	
	public JustForward(double dist) {
		addSequential(new ToggleClaw());
		addSequential(new DriveForward(dist));
		addSequential(new Wait(2));
		addSequential(new DriveForward(-60));
		addParallel(new AutoMoveArm(Arm.down + .2));
	}

}
