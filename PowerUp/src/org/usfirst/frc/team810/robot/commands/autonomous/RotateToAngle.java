package org.usfirst.frc.team810.robot.commands.autonomous;

import org.usfirst.frc.team810.robot.Robot;
import org.usfirst.frc.team810.robot.RobotMap;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.Command;

public class RotateToAngle extends Command {
	
	AHRS navx = RobotMap.navx;
	double target;
	PIDController pid;
	
	private final double kP = .09;
	private final double kI = .002;
	private final double kD = .12;
	
	private int counter = 0;
	
	public RotateToAngle(double target) {
		requires(Robot.driveTrain);
		this.target = target;
	}

	@Override
	protected void initialize() {
		navx.reset();
		pid = new PIDController(kP, kI, kD, RobotMap.navx, a->{});
		pid.setContinuous(true);
		pid.setInputRange(-180, 180);
		pid.setOutputRange(-.67, .67);
		pid.setAbsoluteTolerance(5);
		pid.setSetpoint(target);
		pid.enable();
	}

	@Override
	protected void execute() {
		Robot.driveTrain.arcadeDrive(0, pid.get());
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
		return counter >= 5;
	}

}