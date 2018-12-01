package frc.robot.swerve;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SpeedController;
import frc.robot.utils.PIDThread;
import frc.robot.utils.Vector2;

public final class SwerveWheel {
  private final SpeedController motorDrive;
  private final SpeedController motorPivot;
  private final Encoder encoderPivot;

  private final PIDThread pidThread;

  private final double locationX;
  private final double locationY;
  private final Vector2 tangentVector;
  private final Vector2 unitTangentVector;
  
  public SwerveWheel(SpeedController motorDrive, SpeedController motorPivot, Encoder encoderPivot, 
      double locationX, double locationY) {
    this.motorDrive = motorDrive;
    this.motorPivot = motorPivot;
    this.encoderPivot = encoderPivot;

    pidThread = new PIDThread(1, 0, 0, () -> encoderPivot.getDistance() % 360, this::pidReceiver);

    this.locationX = locationX;
    this.locationY = locationY;

    this.tangentVector = new Vector2(-locationY, locationX);
    this.unitTangentVector = new Vector2(tangentVector.x / tangentVector.hypot(),
        tangentVector.y / tangentVector.hypot());
  }

  private void pidReceiver(double r) {
    // TODO: Do something with r
  }

  public SpeedController getDriveMotor() {
    return motorDrive;
  }

  public SpeedController getPivotMotor() {
    return motorPivot;
  }

  public Encoder getPivotEncoder() {
    return encoderPivot;
  }

  public PIDThread getPidThread() {
    return pidThread;
  }

  public double getLocationX() {
    return locationX;
  }

  public double getLocationY() {
    return locationY;
  }

  public Vector2 getTangentVector() {
    return tangentVector;
  }

  public Vector2 getUnitTangentVector() {
    return unitTangentVector;
  }
}
