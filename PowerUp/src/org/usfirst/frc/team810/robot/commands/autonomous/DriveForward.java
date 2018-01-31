package org.usfirst.frc.team810.robot.commands.autonomous;

import org.usfirst.frc.team810.robot.Robot;
import org.usfirst.frc.team810.robot.RobotMap;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DriveForward extends Command {
	
	AHRS navx = RobotMap.navx;
	double dist;
	PIDController pid;
	
	private int counter = 0;
	
	public DriveForward(double dist) {
		requires(Robot.driveTrain);
		this.dist = dist;
	}

	@Override
	protected void initialize() {
		pid = new PIDController(SmartDashboard.getNumber("kP", 0), SmartDashboard.getNumber("kI", 0), SmartDashboard.getNumber("kD", 0), new DistanceSource(), a->{});
		pid.setContinuous(false);
		pid.setOutputRange(-1, 1);
		pid.setAbsoluteTolerance(0.0762); //.25 ft
		pid.setSetpoint(dist);
		pid.enable();
	}

	@Override
	protected void execute() {
		Robot.driveTrain.arcadeDrive(pid.get(), 0);
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
	
	DistanceSource() {
		RobotMap.navx.reset();
		RobotMap.navx.resetDisplacement();
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
		return RobotMap.navx.getDisplacementX();
	}
	
}
