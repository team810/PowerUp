/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team810.robot;

import org.usfirst.frc.team810.robot.commands.autonomous.DriveForward;
import org.usfirst.frc.team810.robot.commands.autonomous.ScaleCL;
import org.usfirst.frc.team810.robot.commands.autonomous.ScaleCR;
import org.usfirst.frc.team810.robot.commands.autonomous.ScaleLL;
import org.usfirst.frc.team810.robot.commands.autonomous.ScaleLR;
import org.usfirst.frc.team810.robot.commands.autonomous.ScaleRL;
import org.usfirst.frc.team810.robot.commands.autonomous.ScaleRR;
import org.usfirst.frc.team810.robot.commands.autonomous.SwitchCL;
import org.usfirst.frc.team810.robot.commands.autonomous.SwitchCR;
import org.usfirst.frc.team810.robot.commands.autonomous.SwitchLL;
import org.usfirst.frc.team810.robot.commands.autonomous.SwitchLR;
import org.usfirst.frc.team810.robot.commands.autonomous.SwitchRL;
import org.usfirst.frc.team810.robot.commands.autonomous.SwitchRR;
import org.usfirst.frc.team810.robot.subsystems.Arm;
import org.usfirst.frc.team810.robot.subsystems.Claw;
import org.usfirst.frc.team810.robot.subsystems.Climber;
import org.usfirst.frc.team810.robot.subsystems.DriveTrain;
import org.usfirst.frc.team810.robot.subsystems.Intake;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.properties file in the
 * project.
 */
public class Robot extends TimedRobot {
	
	public static OI oi;
	public static DriveTrain driveTrain;
	public static Arm arm;
	public static Claw claw;
	public static Climber climber;
	public static Intake intake;
		
	public static UsbCamera intakeCam, climberCam;

	Command autonomousCommand;
	SendableChooser<String> targetChooser = new SendableChooser<>();
	SendableChooser<String> startPosChooser = new SendableChooser<>();

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		RobotMap.init();
		
		//Acquire strings to help decide which auto to use
		targetChooser.addDefault("Go Forward", "Forward");
		targetChooser.addObject("Switch", "Switch");
		targetChooser.addObject("Scale", "Scale");
		SmartDashboard.putData("Auto Target", targetChooser);
		
		startPosChooser.addDefault("Left", "Left");
		startPosChooser.addObject("Center", "Center");
		startPosChooser.addObject("Right", "Right");
		SmartDashboard.putData("Auto Start Position", startPosChooser);
		
		//Initialize Subsystems
		driveTrain = new DriveTrain();
		arm = new Arm();
		claw = new Claw();
		climber = new Climber();
		intake = new Intake();
		
		oi = new OI();
		
		intakeCam = CameraServer.getInstance().startAutomaticCapture("Intake", 0);
		climberCam = CameraServer.getInstance().startAutomaticCapture("Climber", 1);
	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	@Override
	public void disabledInit() {

	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString code to get the auto name from the text box below the Gyro
	 *
	 * <p>You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons
	 * to the switch structure below with additional strings & commands.
	 */
	
	String start, target;
	
	@Override
	public void autonomousInit() {
		//Extend intake rollers slightly outside of bumpers
		RobotMap.intakePiston.set(true);
		
		start = startPosChooser.getSelected();
		target = startPosChooser.getSelected();
		
		//Ugly code to decide auto using other methods for cleanliness
		if (target.equals("Forward"))
			autonomousCommand = new DriveForward(start.equals("Center") ? 3 : 10);
		else if (target.equals("Switch"))
			targetSwitch();
		else if (target.equals("Scale"))
			targetScale();
		else
			System.out.println("Error receiving target");
		
		if (autonomousCommand != null) {
			autonomousCommand.start();
		}
	}
	
	private void targetSwitch() {
		char config = DriverStation.getInstance().getGameSpecificMessage().charAt(0);
		
		if (start.equals("Left")) {
			if (config == 'L')
				autonomousCommand = new SwitchLL();
			else if (config == 'R')
				autonomousCommand = new SwitchLR();
			else
				System.out.println("Error: Invalid config \'" + config + "\'");
		}
		else if (start.equals("Center")) {
			if (config == 'L')
				autonomousCommand = new SwitchCL();
			else if (config == 'R')
				autonomousCommand = new SwitchCR();
			else
				System.out.println("Error: Invalid config \'" + config + "\'");
		}
		else if (start.equals("Right")) {
			if (config == 'L')
				autonomousCommand = new SwitchRL();
			else if (config == 'R')
				autonomousCommand = new SwitchRR();
			else
				System.out.println("Error: Invalid config \'" + config + "\'");
		}
	}
	
	private void targetScale() {
		char config = DriverStation.getInstance().getGameSpecificMessage().charAt(1);
		
		if (start.equals("Left")) {
			if (config == 'L')
				autonomousCommand = new ScaleLL();
			else if (config == 'R')
				autonomousCommand = new ScaleLR();
			else
				System.out.println("Error: Invalid config \'" + config + "\'");
		}
		else if (start.equals("Center")) {
			if (config == 'L')
				autonomousCommand = new ScaleCL();
			else if (config == 'R')
				autonomousCommand = new ScaleCR();
			else
				System.out.println("Error: Invalid config \'" + config + "\'");
		}
		else if (start.equals("Right")) {
			if (config == 'L')
				autonomousCommand = new ScaleRL();
			else if (config == 'R')
				autonomousCommand = new ScaleRR();
			else
				System.out.println("Error: Invalid config \'" + config + "\'");
		}
	}

	/**
	 * This function is called periodically during autonomous.
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		if (autonomousCommand != null) {
			autonomousCommand.cancel();
		}
	}

	/**
	 * This function is called periodically during operator control.
	 */
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This function is called periodically during test mode.
	 */
	@Override
	public void testPeriodic() {
	}
}
