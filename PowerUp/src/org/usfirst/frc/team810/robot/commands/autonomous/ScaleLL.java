package org.usfirst.frc.team810.robot.commands.autonomous;

import org.usfirst.frc.team810.robot.commands.ToggleClaw;
import org.usfirst.frc.team810.robot.subsystems.Arm;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class ScaleLL extends CommandGroup {

	public ScaleLL() {
		addSequential(new ToggleClaw());
		addSequential(new DriveForward(237.735));
		/*addSequential(new RotateToAngle(30));
		addSequential(new AutoMoveArm((Arm.up + Arm.middle) / 2));
		addSequential(new DriveForward(40));
		*/
		addSequential(new RotateToAngle(-150));
		addSequential(new AutoMoveArm(Arm.up));
		addSequential(new DriveForward(-40));
		addSequential(new ToggleClaw());
		addSequential(new Wait(.25));
		//addSequential(new DriveForward(-32));
		addSequential(new DriveForward(32));
		addSequential(new AutoMoveArm(Arm.down));
	}
	
}
