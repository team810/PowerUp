/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team810.robot;

import org.usfirst.frc.team810.robot.subsystems.Arm;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.CounterBase;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	
	public static Spark rearL, rearR, intakeL, intakeR, armMotor, hookMotor, climbMotor;
	public static Solenoid intakePiston;
	public static DoubleSolenoid springPiston, claw;
	public static DifferentialDrive robotDrive;
	public static WPI_TalonSRX frontL, frontR;
	
	public static AHRS navx;
	public static AnalogPotentiometer pot;
	public static Encoder leftEnc, rightEnc;
	
	private static final double wheelDiameter = 6; //in inches
	private static final double pulsesPerRev = 360;
	
	public static void init() {
		//Drive Train
		frontL = new WPI_TalonSRX(PortNumbers.DRIVE_FRONT_LEFT);
		frontR = new WPI_TalonSRX(PortNumbers.DRIVE_FRONT_RIGHT);
		rearR = new Spark(PortNumbers.DRIVE_REAR_RIGHT);
		rearL = new Spark(PortNumbers.DRIVE_REAR_LEFT);
		
		SpeedControllerGroup leftGroup = new SpeedControllerGroup(frontL, rearL);
		SpeedControllerGroup rightGroup = new SpeedControllerGroup(frontR, rearR);
		robotDrive = new DifferentialDrive(leftGroup, rightGroup);
		robotDrive.setSafetyEnabled(false);
		
		//CIM Motors
		intakeL = new Spark(PortNumbers.INTAKE_LEFT);
		intakeR = new Spark(PortNumbers.INTAKE_RIGHT);
		armMotor = new Spark(PortNumbers.ARM_MOTOR);
		hookMotor = new Spark(PortNumbers.HOOK_MOTOR);
		climbMotor = new Spark(PortNumbers.CLIMB_MOTOR);
		
		//Solenoids
		intakePiston = new Solenoid(PortNumbers.INTAKE_PISTON);
		claw = new DoubleSolenoid(PortNumbers.CLAW_A, PortNumbers.CLAW_B);
		claw.set(DoubleSolenoid.Value.kForward);
		springPiston = new DoubleSolenoid(PortNumbers.SPRING_PISTON_A, PortNumbers.SPRING_PISTON_B);
		springPiston.set(DoubleSolenoid.Value.kReverse);
		
		//Sensors
		navx = new AHRS(I2C.Port.kMXP);
		AutoPutData.addNumber("Navx angle", navx::getAngle);
		pot = new AnalogPotentiometer(PortNumbers.POT, 25, 0); //25" string pot
		Arm.initConstants();
		
		leftEnc = new Encoder(PortNumbers.ENCODER_LEFT_A, PortNumbers.ENCODER_LEFT_B, true, CounterBase.EncodingType.k4X);
		rightEnc = new Encoder(PortNumbers.ENCODER_RIGHT_A, PortNumbers.ENCODER_RIGHT_B, false, CounterBase.EncodingType.k4X);
		
		leftEnc.setDistancePerPulse((Math.PI * wheelDiameter) / pulsesPerRev);
		rightEnc.setDistancePerPulse((Math.PI * wheelDiameter) / pulsesPerRev);
		
		AutoPutData.addNumber("Potentiometer", pot::get);
		AutoPutData.addNumber("Left Encoder", leftEnc::getDistance);
		AutoPutData.addNumber("Right Encoder", rightEnc::getDistance);
	}
}
