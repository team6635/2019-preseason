package frc.robot.swerve;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SpeedController;
import frc.robot.utils.Vector2;

public final class SwerveWheel extends SwervePID {
  private final SpeedController motorDrive;
  private final SpeedController motorPivot;
  private final Encoder encoderPivot;

  private final double locationX;
  private final double locationY;
  private final Vector2 tangentVector;
  private final Vector2 unitTangentVector;

  public SwerveWheel(SpeedController motorDrive, SpeedController motorPivot, Encoder encoderPivot, 
      double locationX, double locationY) {
    super(0.015, 0.001, 0.0025, 20, 1);
    this.motorDrive = motorDrive;
    this.motorPivot = motorPivot;
    this.encoderPivot = encoderPivot;

    this.locationX = locationX;
    this.locationY = locationY;

    this.tangentVector = new Vector2(-locationY, locationX);
    this.unitTangentVector = new Vector2(tangentVector.x / tangentVector.hypot(),
        tangentVector.y / tangentVector.hypot());
  }

  protected Vector2 calculateSwerve(double xInput, double yInput, double zInput) {
    Vector2 rVector = new Vector2(zInput * unitTangentVector.x, zInput * unitTangentVector.y);
    Vector2 resultVector = new Vector2(xInput + rVector.x, yInput + rVector.y);

    double angle = Math.atan2(resultVector.y, resultVector.x) * (180 / Math.PI) - 90 % 360;
    double speed = resultVector.hypot();

    return new Vector2(angle, speed);
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

  @Override
  public double pidInputProvider() {
    return ((encoderPivot.getDistance() % 360) + 360) % 360;
  }

  @Override
  public void pidUseOutput(double output) {
    motorPivot.set(output);
  }
}
