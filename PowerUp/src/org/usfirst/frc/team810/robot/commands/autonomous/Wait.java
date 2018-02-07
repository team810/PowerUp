package org.usfirst.frc.team810.robot.commands.autonomous;

import org.usfirst.frc.team810.robot.Robot;

import edu.wpi.first.wpilibj.command.TimedCommand;

public class Wait extends TimedCommand {

	public Wait(double timeout) {
		super(timeout);
		requires(Robot.driveTrain);
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return super.isFinished();
	}

}
