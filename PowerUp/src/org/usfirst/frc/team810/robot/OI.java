/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team810.robot;

import org.usfirst.frc.team810.robot.commands.Climb;
import org.usfirst.frc.team810.robot.commands.ExtendHook;
import org.usfirst.frc.team810.robot.commands.Intake;
import org.usfirst.frc.team810.robot.commands.MoveArm;
import org.usfirst.frc.team810.robot.commands.OneSideIntake;
import org.usfirst.frc.team810.robot.commands.OnlyClawToggle;
import org.usfirst.frc.team810.robot.commands.RetractHook;
import org.usfirst.frc.team810.robot.commands.SetSpringPiston;
import org.usfirst.frc.team810.robot.commands.ToggleClaw;
import org.usfirst.frc.team810.robot.commands.autonomous.AutoMoveArm;
import org.usfirst.frc.team810.robot.commands.autonomous.DriveForward;
import org.usfirst.frc.team810.robot.commands.autonomous.RotateToAngle;
import org.usfirst.frc.team810.robot.commands.autonomous.ScaleRR;
import org.usfirst.frc.team810.robot.commands.autonomous.SwitchCL;
import org.usfirst.frc.team810.robot.commands.autonomous.SwitchCR;
import org.usfirst.frc.team810.robot.commands.autonomous.SwitchLL;
import org.usfirst.frc.team810.robot.subsystems.Arm;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	public Joystick leftStick, rightStick, gamepad;
	public JoystickButton intake, oneIntake, output, raiseArm, lowerArm, toggleClaw, toggleOnlyClaw, extendHook, retractHook, climb, climbSafety;
	
	public OI() {
		//Joysticks
		leftStick = new Joystick(PortNumbers.LEFT_JOYSTICK);
		rightStick = new Joystick(PortNumbers.RIGHT_JOYSTICK);
		gamepad = new Joystick(PortNumbers.GAMEPAD);
		
		//Buttons
		intake = new JoystickButton(rightStick, 1);
		intake.whileHeld(new Intake(.8));
		
		oneIntake = new JoystickButton(leftStick, 1);
		oneIntake.whileHeld(new OneSideIntake(.8));
		
		output = new JoystickButton(rightStick, 2);
		output.whileHeld(new Intake(-.8));
		
		raiseArm = new JoystickButton(gamepad, 4);
		raiseArm.whileHeld(new MoveArm(-.9));
		
		lowerArm = new JoystickButton(gamepad, 2);
		lowerArm.whileHeld(new MoveArm(1));
		
		toggleClaw = new JoystickButton(gamepad, 3);
		toggleClaw.whenPressed(new ToggleClaw());
		
		toggleOnlyClaw = new JoystickButton(gamepad, 1);
		toggleOnlyClaw.whenPressed(new OnlyClawToggle());
		
		extendHook = new JoystickButton(gamepad, 6);
		extendHook.whileHeld(new ExtendHook(1));
		
		retractHook = new JoystickButton(gamepad, 8);
		retractHook.whileHeld(new RetractHook(1));
		
		climb = new JoystickButton(gamepad, 5);
		climb.whileHeld(new Climb(1));
		
		climbSafety = new JoystickButton(leftStick, 2);
		
		//SmartDashboard values
		SmartDashboard.putData("Move Arm to Middle", new AutoMoveArm(Arm.middle));
		SmartDashboard.putData("Move Arm to Top", new AutoMoveArm(Arm.up));
		SmartDashboard.putData("Move Arm to Bottom", new AutoMoveArm(Arm.down));
		SmartDashboard.putData("Move Arm a little", new AutoMoveArm(Arm.down + .5));
		
		SmartDashboard.putData("Drive Forward 1 ft", new DriveForward(12));
		SmartDashboard.putData("Drive Forward 3 ft", new DriveForward(36));
		SmartDashboard.putData("Drive Forward 10 ft", new DriveForward(120));
		SmartDashboard.putData("Drive Forward x Distance", new DriveForward(212.735));
		SmartDashboard.putData("Drive Backwards 1 ft", new DriveForward(-12));
		SmartDashboard.putData("Drive Backwards 5 ft", new DriveForward(-5 * 12));
		
		SmartDashboard.putData("Rotate 90", new RotateToAngle(90));
		SmartDashboard.putData("Rotate 45", new RotateToAngle(45));
		SmartDashboard.putData("Rotate -90", new RotateToAngle(-90));
		
		SmartDashboard.putData("Auto Scale Right Right", new ScaleRR());
		SmartDashboard.putData("Auto Switch Right", new SwitchCR());
		SmartDashboard.putData("Auto Switch Left", new SwitchCL());
		SmartDashboard.putData("Auto Switch Left Left", new SwitchLL());
		
		SmartDashboard.putData("Toggle Intake Piston", new SetSpringPiston());
	}
}
