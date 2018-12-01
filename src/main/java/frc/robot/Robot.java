/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.swerve.SwerveDrive;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends IterativeRobot {
  private static final String kDefaultAuton = "Default";
  private static final String kCustomAuton1 = "My Auto";
  private String m_autonSelected;
  private final SendableChooser<String> m_chooser = new SendableChooser<>();

  private final SwerveDrive drivetrain = RobotMap.drive;
  private final Joystick joystickLeft = RobotMap.joystickLeft;
  // private final Joystick joystickRight = RobotMap.joystickRight;

  private final AutonManager autonDefault = new AutonManager();
  // Give your auton a better name than this.
  private final AutonManager autonCustom1 = new AutonManager();

  @Override
  public void robotInit() {
    autonDefault.addStage(10, () -> {
      // Put auton code to run when time remaining is greater than 10 seconds
    }).addStage(5, () -> {
      // Put auton code to run when time remaining is greater than 5 seconds
    }).addStage(0, () -> {
      // Put auton code to run when time remaining is greater than 0
    });

    autonCustom1.addStage(15, () -> {
      drivetrain.drive(0, 1, 0); // Forward
    }).addStage(10, () -> {
      drivetrain.drive(1, 0, 0); // Right
    }).addStage(5, () -> {
      drivetrain.drive(0, 0, 1); // Spin clockwise
    }).addStage(0, () -> {
      drivetrain.drive(0, 0, -1); // Spin counter-clockwise
    });

    m_chooser.addDefault("Default Auton", kDefaultAuton);
    m_chooser.addObject("Custom Auton 1", kCustomAuton1);
    SmartDashboard.putData("Auton choices", m_chooser);
  }

  @Override
  public void autonomousInit() {
    m_autonSelected = m_chooser.getSelected();
    // autoSelected = SmartDashboard.getString("Auto Selector",
    // defaultAuto);
    System.out.println("Auto selected: " + m_autonSelected);
    drivetrain.startAll();
  }

  @Override
  public void autonomousPeriodic() {
    switch (m_autonSelected) {
      case kCustomAuton1:
        autonCustom1.run();
        break;
      case kDefaultAuton:
      default:
        autonDefault.run();
        break;
    }
  }

  @Override
  public void teleopInit() {
    drivetrain.startAll();
  }

  @Override
  public void teleopPeriodic() {
    drivetrain.drive(joystickLeft.getX(), joystickLeft.getY(), joystickLeft.getZ());
  }

  @Override
  public void disabledInit() {
    drivetrain.stopAll();
  }
}
