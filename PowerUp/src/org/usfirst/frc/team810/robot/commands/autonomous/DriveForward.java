package org.usfirst.frc.team810.robot.commands.autonomous;

import org.usfirst.frc.team810.robot.Robot;
import org.usfirst.frc.team810.robot.RobotMap;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DriveForward extends Command {

	double dist, heading;
	PIDController pid;
	
	private int counter = 0;
	
	//takes distance in feet
	public DriveForward(double dist) {
		requires(Robot.driveTrain);
		this.dist = dist * 12; //convert feet to inches
		heading = RobotMap.navx.getAngle();
	}

	@Override
	protected void initialize() {
		pid = new PIDController(SmartDashboard.getNumber("kP", 0), SmartDashboard.getNumber("kI", 0), SmartDashboard.getNumber("kD", 0), new DistanceSource(), a->{});
		pid.setContinuous(false);
		pid.setOutputRange(-1, 1);
		pid.setAbsoluteTolerance(2);
		pid.setSetpoint(dist);
		pid.enable();
	}

	@Override
	protected void execute() {
		double rot = (heading - RobotMap.navx.getAngle()) * SmartDashboard.getNumber("kP", 0);
		Robot.driveTrain.arcadeDrive(pid.get(), rot);
		
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
