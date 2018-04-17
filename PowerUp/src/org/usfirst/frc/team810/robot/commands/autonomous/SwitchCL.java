package org.usfirst.frc.team810.robot.commands.autonomous;

import org.usfirst.frc.team810.robot.commands.ToggleClaw;
import org.usfirst.frc.team810.robot.subsystems.Arm;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class SwitchCL extends CommandGroup {
	
	public SwitchCL() {
		addSequential(new ToggleClaw());
		addSequential(new DriveForward(17.5));
		addSequential(new RotateToAngle(-33));
		addSequential(new AutoMoveArm(Arm.middle));
		addSequential(new DriveForward(92));
		addSequential(new RotateToAngle(26));
		//addSequential(new DriveForward(2));
		addSequential(new Wait(.25));
		addSequential(new ToggleClaw());
		addSequential(new Wait(.25));
		addSequential(new DriveForward(-35));
		addSequential(new AutoMoveArm(Arm.down));
	}

}
