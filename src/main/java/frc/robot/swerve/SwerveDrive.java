package frc.robot.swerve;

import edu.wpi.first.wpilibj.AnalogGyro;

public class SwerveDrive {
  private SwerveWheel[] wheels;
  private AnalogGyro gyro;

  public SwerveDrive(SwerveWheel[] wheels, AnalogGyro gyro) {
    this.wheels = wheels;
    this.gyro = gyro;
  }

  public void drive(double xInput, double yInput, double zInput) {
    for (int i = 0; i < wheels.length; i++) {
      updateWheel(wheels[i], xInput, yInput, zInput);
    }
  }

  protected void updateWheel(SwerveWheel wheel, double xInput, double yInput, double zInput) {
    // TODO
  }

  private final class Vector2 {
    public double x;
    public double y;

    public Vector2(double vx, double vy) {
      x = vx;
      y = vy;
    }
  }

  private final class Vector3 {
    public double x;
    public double y;
    public double z;

    public Vector3(double vx, double vy, double vz) {
      x = vx;
      y = vy;
      z = vz;
    }
  }
}
