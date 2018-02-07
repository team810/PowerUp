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
import org.usfirst.frc.team810.robot.commands.RetractHook;
import org.usfirst.frc.team810.robot.commands.ToggleClaw;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	public Joystick leftStick, rightStick, gamepad;
	public JoystickButton intake, oneIntake, output, raiseArm, lowerArm, toggleClaw, extendHook, retractHook, climb;
	
	public OI() {
		//Joysticks
		leftStick = new Joystick(PortNumbers.LEFT_JOYSTICK);
		rightStick = new Joystick(PortNumbers.RIGHT_JOYSTICK);
		gamepad = new Joystick(PortNumbers.GAMEPAD);
		
		//Buttons
		intake = new JoystickButton(rightStick, 1);
		intake.whileHeld(new Intake(.4));
		
		oneIntake = new JoystickButton(leftStick, 1);
		oneIntake.whileHeld(new OneSideIntake(.4));
		
		output = new JoystickButton(rightStick, 2);
		output.whileHeld(new Intake(-.4));
		
		raiseArm = new JoystickButton(gamepad, 4);
		raiseArm.whileHeld(new MoveArm(.4));
		
		lowerArm = new JoystickButton(gamepad, 2);
		lowerArm.whileHeld(new MoveArm(-.4));
		
		toggleClaw = new JoystickButton(gamepad, 3);
		toggleClaw.whenPressed(new ToggleClaw());
		
		extendHook = new JoystickButton(gamepad, 6);
		extendHook.whenPressed(new ExtendHook(2));
		
		retractHook = new JoystickButton(gamepad, 8);
		retractHook.whenPressed(new RetractHook(2));
		
		climb = new JoystickButton(gamepad, 5);
		climb.whileHeld(new Climb(1));
		
		//SmartDashboard values
		SmartDashboard.putNumber("kP", 0);
		SmartDashboard.putNumber("kI", 0);
		SmartDashboard.putNumber("kD", 0);
		
		SmartDashboard.putNumber("kP_Arm", 0);
		SmartDashboard.putNumber("kI_Arm", 0);
		SmartDashboard.putNumber("kD_Arm", 0);
	}
}
