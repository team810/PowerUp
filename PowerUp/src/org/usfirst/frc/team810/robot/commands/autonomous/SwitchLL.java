package org.usfirst.frc.team810.robot.commands.autonomous;

import org.usfirst.frc.team810.robot.commands.ToggleClaw;
import org.usfirst.frc.team810.robot.subsystems.Arm;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class SwitchLL extends CommandGroup {
	
	public SwitchLL() {
		addSequential(new ToggleClaw());
		addSequential(new DriveForward(144));
		addSequential(new RotateToAngle(90));
		addSequential(new AutoMoveArm(Arm.middle));
		addSequential(new DriveForward(24));
		addSequential(new ToggleClaw());
		addSequential(new Wait(.25));
		addSequential(new DriveForward(-24));
		addSequential(new AutoMoveArm(Arm.down));
	}

}
