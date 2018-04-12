package org.usfirst.frc.team810.robot.commands.autonomous;

import org.usfirst.frc.team810.robot.Robot;
import org.usfirst.frc.team810.robot.RobotMap;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.command.Command;

public class DriveForward extends Command {

	double dist, heading;
	PIDController pid;
	
	private final double kP = .3;
	private final double kI = .023;
	private final double kD = 1.5;
	
	private int counter = 0;
	
	//takes distance in inches
	public DriveForward(double dist) {
		requires(Robot.driveTrain);
		this.dist = dist;
	}
	
	@Override
	protected void initialize() {
		pid = new PIDController(kP, kI, kD, new DistanceSource(), a->{});
		pid.setContinuous(false);
		double fullSpeed = (dist > 0) ? .7 : .5;
		pid.setOutputRange(-fullSpeed, fullSpeed);
		pid.setAbsoluteTolerance(1);
		pid.setSetpoint(dist);
		pid.enable();

		heading = RobotMap.navx.getAngle();
	}

	@Override
	protected void execute() {		
		double rot = (heading - RobotMap.navx.getAngle()) * kP;
		Robot.driveTrain.arcadeDrive(-pid.get(), rot);
		
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

class DistanceSource implements PIDSource {
	
	Encoder leftEnc = RobotMap.leftEnc;
	Encoder rightEnc = RobotMap.rightEnc;
	
	DistanceSource() {
		leftEnc.reset();
		rightEnc.reset();
	}

	@Override
	public void setPIDSourceType(PIDSourceType pidSource) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public PIDSourceType getPIDSourceType() {
		// TODO Auto-generated method stub
		return PIDSourceType.kDisplacement;
	}

	@Override
	public double pidGet() {
		return (leftEnc.getDistance() + rightEnc.getDistance()) / 2;
	}
	
}
