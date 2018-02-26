package org.usfirst.frc.team810.robot.commands.autonomous;

import org.usfirst.frc.team810.robot.commands.ToggleClaw;
import org.usfirst.frc.team810.robot.subsystems.Arm;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class SwitchLR extends CommandGroup {
	
	public SwitchLR() {
		addSequential(new DriveForward(5));
		addSequential(new RotateToAngle(45));
		addSequential(new DriveForward(5));
		addParallel(new AutoMoveArm((Arm.up + Arm.down) / 2));
		addSequential(new ToggleClaw());
		addSequential(new AutoMoveArm(Arm.down));
	}

}
