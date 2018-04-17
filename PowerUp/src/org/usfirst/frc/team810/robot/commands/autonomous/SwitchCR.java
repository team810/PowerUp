package org.usfirst.frc.team810.robot.commands.autonomous;

import org.usfirst.frc.team810.robot.commands.ToggleClaw;
import org.usfirst.frc.team810.robot.subsystems.Arm;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class SwitchCR extends CommandGroup {
	
	public SwitchCR() {
		addSequential(new ToggleClaw());
		addSequential(new DriveForward(24));
		addSequential(new RotateToAngle(32));
		addSequential(new AutoMoveArm(Arm.middle));
		addSequential(new DriveForward(80));
		addSequential(new RotateToAngle(-13));
		addSequential(new DriveForward(2));
		addSequential(new Wait(.25));
		addSequential(new ToggleClaw());
		addSequential(new Wait(.25));
		addSequential(new DriveForward(-35));
		addSequential(new AutoMoveArm(Arm.down));
	}

}
