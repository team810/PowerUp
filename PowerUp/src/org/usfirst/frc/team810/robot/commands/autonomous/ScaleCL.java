package org.usfirst.frc.team810.robot.commands.autonomous;

import org.usfirst.frc.team810.robot.commands.ToggleClaw;
import org.usfirst.frc.team810.robot.subsystems.Arm;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class ScaleCL extends CommandGroup {
	
	public ScaleCL() {
		addSequential(new ToggleClaw());
		addSequential(new DriveForward(48));
		addSequential(new RotateToAngle(-90));
		addSequential(new DriveForward(36));
		addSequential(new RotateToAngle(90));
		addSequential(new AutoMoveArm(Arm.up));
		addParallel(new DriveForward(36));
		addSequential(new ToggleClaw());
	}

}
