package frc.robot.swerve;

import edu.wpi.first.wpilibj.AnalogGyro;

public class SwerveDrive {
  private SwerveWheel[] wheels;

  public SwerveDrive(SwerveWheel[] wheels, AnalogGyro gyro) {
    this.wheels = wheels;
    // TODO: Use gyro
  }

  public void drive(double xInput, double yInput, double zInput) {
    System.out.println("SwerveDrive::drive " + xInput + "/" + yInput + "/" + zInput);
    for (int i = 0; i < wheels.length; i++) {
      wheels[i].updateWheel(xInput, yInput, zInput);
    }
  }
  
  public void disable() {
    for (int i = 0; i < wheels.length; i++) {
      wheels[i].setEnabled(false);
    }
  }

  public void enable() {
    for (int i = 0; i < wheels.length; i++) {
      wheels[i].setEnabled(true);
    }
  }
}
