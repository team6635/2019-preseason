package frc.robot.swerve;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SpeedController;

public final class SwerveWheel {
  private final SpeedController motorDrive;
  private final SpeedController motorPivot;
  private final Encoder encoderPivot;
  private final double locationX;
  private final double locationY;

  public SwerveWheel(SpeedController motorDrive, SpeedController motorPivot, Encoder encoderPivot, double locationX, double locationY) {
    this.motorDrive = motorDrive;
    this.motorPivot = motorPivot;
    this.encoderPivot = encoderPivot;
    this.locationX = locationX;
    this.locationY = locationY;
  }

  public SpeedController getDriveMotor() {
    return this.motorDrive;
  }

  public SpeedController getPivotMotor() {
    return this.motorPivot;
  }

  public Encoder getPivotEncoder() {
    return this.encoderPivot;
  }

  public double getLocationX() {
    return this.locationX;
  }

  public double getLocationY() {
    return this.locationY;
  }
}
