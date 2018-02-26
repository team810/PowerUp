package org.usfirst.frc.team810.robot.commands.autonomous;

import org.usfirst.frc.team810.robot.Robot;
import org.usfirst.frc.team810.robot.RobotMap;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.Command;

public class AutoMoveArm extends Command {
	
	AHRS navx = RobotMap.navx;
	double target;
	PIDController pid;
	
	private int counter = 0;
	
	private final double kP = .8;
	private final double kI = 0;
	private final double kD = 0.2;
	
	public AutoMoveArm(double target) {
		requires(Robot.driveTrain);
		this.target = target;
	}

	@Override
	protected void initialize() {
		pid = new PIDController(-kP, -kI, -kD, RobotMap.pot, a -> {});
		pid.setContinuous(false);
		pid.setOutputRange(-.8, .8);
		pid.setAbsoluteTolerance(.5);
		pid.setSetpoint(target);
		pid.enable();		
	}

	@Override
	protected void execute() {
		RobotMap.armMotor.set(pid.get());
		if (pid.onTarget())
			counter++;
		else
			counter = 0;
	}

	@Override
	protected void end() {
		pid.disable();
		pid.free();
	}

	@Override
	protected void interrupted() {
		end();
	}

	@Override
	protected boolean isFinished() {
		return counter >= 10;
	}

}