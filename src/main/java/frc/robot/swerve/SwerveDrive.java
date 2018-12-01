package frc.robot.swerve;

import edu.wpi.first.wpilibj.AnalogGyro;
import frc.robot.utils.Vector2;

public class SwerveDrive {
  private SwerveWheel[] wheels;
  // private AnalogGyro gyro; 
  // TODO: Use Gyro?

  public SwerveDrive(SwerveWheel[] wheels, AnalogGyro gyro) {
    this.wheels = wheels;
    // this.gyro = gyro;
  }

  public void drive(double xInput, double yInput, double zInput) {
    for (int i = 0; i < wheels.length; i++) {
      updateWheel(wheels[i], xInput, yInput, zInput);
    }
  }

  protected void updateWheel(SwerveWheel wheel, double xInput, double yInput, double zInput) {
    Vector2 rvector = new Vector2(zInput * wheel.getTangentVector().x, zInput * wheel.getTangentVector().y);
    Vector2 resultv = new Vector2(xInput + rvector.x, yInput + rvector.y);

    double angle = (Math.toDegrees(Math.atan2(resultv.x, resultv.y)) - 90) % 360;
    double motorSpeed = resultv.hypot() > 1 ? 1 : resultv.hypot();

    wheel.getPidThread().getPid().setTarget(angle);
    wheel.getDriveMotor().set(motorSpeed);
  }
  
  public void stopAll() {
    for (int i = 0; i < wheels.length; i++) {
      SwerveWheel wheel = wheels[i];
      wheel.getPidThread().stopThread();
      wheel.getDriveMotor().set(0);
      wheel.getPivotMotor().set(0);
    }
  }

  public void startAll() {
    for (int i = 0; i < wheels.length; i++) {
      SwerveWheel wheel = wheels[i];
      wheel.getPidThread().startThread();
    }
  }
}
