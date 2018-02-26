package org.usfirst.frc.team810.robot.commands.autonomous;

import org.usfirst.frc.team810.robot.commands.ToggleClaw;
import org.usfirst.frc.team810.robot.subsystems.Arm;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class ScaleCR extends CommandGroup {
	
	public ScaleCR() {
		addSequential(new RotateToAngle(45));
		addSequential(new DriveForward(5));
		addSequential(new RotateToAngle(-45));
		addParallel(new AutoMoveArm(Arm.up));
		addSequential(new DriveForward(5));
		addSequential(new ToggleClaw());
		addSequential(new AutoMoveArm(Arm.down));
	}

}
