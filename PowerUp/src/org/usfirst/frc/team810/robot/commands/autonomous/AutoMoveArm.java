package org.usfirst.frc.team810.robot.commands.autonomous;

import org.usfirst.frc.team810.robot.Robot;
import org.usfirst.frc.team810.robot.RobotMap;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class AutoMoveArm extends Command {
	
	AHRS navx = RobotMap.navx;
	double target;
	PIDController pid;
	
	private int counter = 0;
	
	public AutoMoveArm(double target) {
		requires(Robot.driveTrain);
		this.target = target;
	}

	@Override
	protected void initialize() {
		pid = new PIDController(SmartDashboard.getNumber("kP_Arm", 0), SmartDashboard.getNumber("kI_Arm", 0), SmartDashboard.getNumber("kD_Arm", 0), RobotMap.pot, RobotMap.armMotor);
		pid.setContinuous(false);
		pid.setOutputRange(-.5, .5);
		pid.setAbsoluteTolerance(2);
		pid.setSetpoint(target);
		pid.enable();
	}

	@Override
	protected void execute() {
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