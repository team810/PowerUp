package org.usfirst.frc.team810.robot.commands.autonomous;

import org.usfirst.frc.team810.robot.commands.ToggleClaw;
import org.usfirst.frc.team810.robot.subsystems.Arm;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class SwitchCR extends CommandGroup {
	
	public SwitchCR() {
		addSequential(new ToggleClaw());
		addSequential(new DriveForward(24));
		addSequential(new RotateToAngle(29));
		addSequential(new AutoMoveArm(Arm.middle));
		addSequential(new DriveForward(70));
		addSequential(new ToggleClaw());
		addSequential(new Wait(.25));
		addSequential(new DriveForward(-70));
		addSequential(new AutoMoveArm(Arm.down));
	}

}
