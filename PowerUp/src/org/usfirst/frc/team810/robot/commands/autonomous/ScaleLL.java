package org.usfirst.frc.team810.robot.commands.autonomous;

import org.usfirst.frc.team810.robot.commands.ToggleClaw;
import org.usfirst.frc.team810.robot.subsystems.Arm;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class ScaleLL extends CommandGroup {

	public ScaleLL() {
		addSequential(new DriveForward(10));
		addParallel(new AutoMoveArm(Arm.up));
		addSequential(new ToggleClaw());
		addSequential(new AutoMoveArm(Arm.down));
	}
	
}
