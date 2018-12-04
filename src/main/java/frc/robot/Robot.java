package frc.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.swerve.SwerveDrive;

public class Robot extends IterativeRobot {
  // Auton
  private static final String autonIDDefault = "Default";
  private static final String autonIDCustom1 = "My Auto";
  private String autonSelected;
  private final SendableChooser<String> chooser = new SendableChooser<>();
  private final AutonManager autonDefault = new AutonManager();
  private final AutonManager autonCustom1 = new AutonManager();

  // Shortcuts to RobotMap values
  private final SwerveDrive drivetrain = RobotMap.drive;
  private final Joystick joystickLeft = RobotMap.joystickLeft;
  
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
      RobotMap.motorDriveFrontLeft.set(1.0);
    }).addStage(10, () -> {
      RobotMap.motorDriveFrontLeft.set(-1.0);      
    }).addStage(5, () -> {
      RobotMap.motorDriveFrontLeft.set(1.0);      
    }).addStage(0, () -> {
      RobotMap.motorDriveFrontLeft.set(-1.0);      
    });

    chooser.addDefault("Default Auton", autonIDDefault);
    chooser.addObject("Custom Auton 1", autonIDCustom1);
    SmartDashboard.putData("Auton choices", chooser);
  }

  @Override
  public void autonomousInit() {
    autonSelected = chooser.getSelected();
    // autoSelected = SmartDashboard.getString("Auto Selector",
    // defaultAuto);
    System.out.println("Auto selected: " + autonSelected);
    drivetrain.enable();
  }

  @Override
  public void autonomousPeriodic() {
    switch (autonSelected) {
    // This switch statement is a little messed up
    // from testing. TODO.
      case autonIDCustom1:
        break;
      case autonIDDefault:
      default:
        autonCustom1.run();
        break;
    }
  }

  @Override
  public void teleopInit() {
    drivetrain.enable();
  }

  @Override
  public void teleopPeriodic() {
    SmartDashboard.putNumber("Joystick X", joystickLeft.getX());
    SmartDashboard.putNumber("Joystick Y", joystickLeft.getY());
    SmartDashboard.putNumber("Joystick Z", joystickLeft.getZ());

    drivetrain.drive(joystickLeft.getX(), joystickLeft.getY(), joystickLeft.getZ());
  }

  @Override
  public void disabledInit() {
    drivetrain.disable();
  }

  @Override
  public void testInit() {
    // We are using this to test individual motors.
    drivetrain.disable();
    RobotMap.motorDriveFrontLeft.set(1);
  }

  @Override
  public void testPeriodic() {
  }
}
