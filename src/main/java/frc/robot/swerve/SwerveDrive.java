package frc.robot.swerve;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.utils.Utils;
import frc.robot.utils.Vector2;

public class SwerveDrive {
  private SwerveWheel[] wheels;

  private double controllerTolerance = 0.08;

  public SwerveDrive(SwerveWheel[] wheels, AnalogGyro gyro) {
    this.wheels = wheels;
    // TODO: Use gyro
  }

  public void drive(double xInput, double yInput, double zInput) {
    SmartDashboard.putNumber("ControllerTolerance", controllerTolerance);

    double[] speeds = new double[wheels.length];

    if (Math.abs(xInput) + Math.abs(yInput) + Math.abs(zInput) / 3 <= controllerTolerance) {
      for (int i = 0; i < speeds.length; i++) {
        speeds[i] = 0;
        wheels[i].setSetpoint(wheels[i].pidInputProvider());
      }
    } else {
      for (int i = 0; i < wheels.length; i++) {
        Vector2 calculated = wheels[i].calculateSwerve(xInput, yInput, zInput);
        wheels[i].setSetpoint(calculated.x);
        speeds[i] = calculated.y;
      }

      Utils.capValuesSymmetrically(speeds, 1.0);
    }

    for (int i = 0; i < wheels.length; i++) {
      wheels[i].getDriveMotor().set(speeds[i]);
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
