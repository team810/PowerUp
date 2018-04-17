package org.usfirst.frc.team810.robot.commands.autonomous;

import org.usfirst.frc.team810.robot.Robot;
import org.usfirst.frc.team810.robot.RobotMap;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.command.Command;

public class AutoMoveArm extends Command {
	
	AHRS navx = RobotMap.navx;
	double target;
	//PIDController pid;
	
	private int counter = 0;
	
	private final double kP = .8;
	
	public AutoMoveArm(double target) {
		requires(Robot.arm);
		this.target = target;
	}

	@Override
	protected void initialize() {
		/*pid = new PIDController(-kP, -kI, -kD, RobotMap.pot, a -> {});
		pid.setContinuous(false);
		pid.setOutputRange(-.8, .8);
		pid.setAbsoluteTolerance(.5);
		pid.setSetpoint(target);
		pid.enable();*/		
	}

	@Override
	protected void execute() {
		double current = RobotMap.pot.get();
		double value = (current - target) * kP;
		
		if (value > .8)
			value = .8;
		if (value < -.8)
			value = -.8;
		
		RobotMap.armMotor.set(value);
		//DriverStation.reportWarning("PID Get: " + value, false);
		
		if (Math.abs(target - current) <= .5)
			counter++;
		else
			counter = 0;
	}

	@Override
	protected void end() {
		/*pid.disable();
		pid.free();*/
		RobotMap.armMotor.set(0);
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