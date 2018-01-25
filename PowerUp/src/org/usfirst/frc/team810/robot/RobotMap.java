/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team810.robot;

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
	
	public static Spark frontL, frontR, rearL, rearR;
	public static DifferentialDrive robotDrive;
	
	public static void init() {
		frontL = new Spark(PortNumbers.DRIVE_FRONT_LEFT);
		frontR = new Spark(PortNumbers.DRIVE_FRONT_RIGHT);
		rearR = new Spark(PortNumbers.DRIVE_REAR_RIGHT);
		rearL = new Spark(PortNumbers.DRIVE_REAR_LEFT);
		
		SpeedControllerGroup leftGroup = new SpeedControllerGroup(frontL, rearL);
		SpeedControllerGroup rightGroup = new SpeedControllerGroup(frontR, rearR);
		robotDrive = new DifferentialDrive(leftGroup, rightGroup);
	}
}
