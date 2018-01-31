package org.usfirst.frc.team810.robot.commands.autonomous;

import org.usfirst.frc.team810.robot.Robot;
import org.usfirst.frc.team810.robot.RobotMap;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class RotateToAngle extends Command {
	
	AHRS navx = RobotMap.navx;
	double target;
	PIDController pid;
	
	private int counter = 0;
	
	public RotateToAngle(double target) {
		requires(Robot.driveTrain);
		this.target = target;
	}

	@Override
	protected void initialize() {
		pid = new PIDController(SmartDashboard.getNumber("kP", 0), SmartDashboard.getNumber("kI", 0), SmartDashboard.getNumber("kD", 0), new AngleSource(), a->{});
		pid.setContinuous(true);
		pid.setOutputRange(-.5, .5);
		pid.setAbsoluteTolerance(2);
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
		return counter >= 10;
	}

}

class AngleSource implements PIDSource {
	
	AngleSource() {
		RobotMap.navx.reset();
		RobotMap.navx.zeroYaw();
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
		return RobotMap.navx.getYaw();
	}
	
}
