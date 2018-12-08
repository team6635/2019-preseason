package frc.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.swerve.SwerveDrive;

public class Robot extends IterativeRobot {
  private final SwerveDrive drivetrain = RobotMap.drive;
  private final XboxController xbox1 = RobotMap.xbox1;
  private final AutonManager testAuton = new AutonManager();
  
  @Override
  public void robotInit() {
    testAuton.addStage(10, () -> {
      drivetrain.drive(0, 1, 0);
    }).addStage(7, () -> {
      drivetrain.drive(1, 0, 0);
    }).addStage(3.5, () -> {
      drivetrain.drive(0, 0, 1);
    }).addStage(0, () -> {
      drivetrain.drive(1, 1, 0);
    });
  }

  @Override
  public void robotPeriodic() {
    
  }

  @Override
  public void autonomousInit() {
    drivetrain.enable();
  }

  @Override
  public void autonomousPeriodic() {
    testAuton.run();
  }

  @Override
  public void teleopInit() {
    drivetrain.enable();
  }

  @Override
  public void teleopPeriodic() {
    SmartDashboard.putNumber("Joystick X", xbox1.getX(Hand.kRight));
    SmartDashboard.putNumber("Joystick Y", xbox1.getY(Hand.kRight));
    SmartDashboard.putNumber("Joystick Z", xbox1.getX(Hand.kLeft));

    drivetrain.drive(xbox1.getX(Hand.kRight), -xbox1.getY(Hand.kRight), xbox1.getX(Hand.kLeft));
  }

  @Override
  public void disabledInit() {
    drivetrain.disable();
  }

  @Override
  public void testInit() {
    // We are using this to test individual motors.
    drivetrain.disable();
  }

  @Override
  public void testPeriodic() {
    RobotMap.motorDriveFrontLeft.set(1);
  }
}
