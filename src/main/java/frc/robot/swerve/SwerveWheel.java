package frc.robot.swerve;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SpeedController;
import frc.robot.utils.Vector2;

public final class SwerveWheel {
  private final SpeedController motorDrive;
  private final SpeedController motorPivot;
  private final Encoder encoderPivot;

  private final double locationX;
  private final double locationY;
  private final Vector2 tangentVector;
  private final Vector2 unitTangentVector;

  private final SwervePID swervePID;

  public SwerveWheel(SpeedController motorDrive, SpeedController motorPivot, Encoder encoderPivot, 
      double locationX, double locationY) {
    this.motorDrive = motorDrive;
    this.motorPivot = motorPivot;
    this.encoderPivot = encoderPivot;

    this.locationX = locationX;
    this.locationY = locationY;

    this.tangentVector = new Vector2(-locationY, locationX);
    this.unitTangentVector = new Vector2(tangentVector.x / tangentVector.hypot(),
        tangentVector.y / tangentVector.hypot());

    swervePID = new SwervePID(1, 0, 0, 50, this::pidSupplier, this::pidConsumer);
  }

  /**
   * Supplies the current measurement for PID.
   * @return
   */
  private double pidSupplier() {
    return (encoderPivot.getDistance() + 360) % 360;
  }

  /**
   * Consumes the resultant double from the PID.
   * @param result
   */
  private void pidConsumer(double result) {
    System.out.println("TODO: SwerveWheel::pidConsumer result := " + result);
  }

  protected void updateWheel(double xInput, double yInput, double zInput) {
    System.out.println("SwerveWheel::updateWheel " + xInput + ", " + yInput + ", " + zInput);
    Vector2 rvector = new Vector2(zInput * getTangentVector().x, zInput * getTangentVector().y);
    Vector2 resultv = new Vector2(xInput + rvector.x, yInput + rvector.y);

    double angle = ((Math.toDegrees(Math.atan2(resultv.x, resultv.y)) - 90) + 360) % 360;
    double motorSpeed = resultv.hypot() > 1 ? 1 : resultv.hypot();

    System.out.println("SwerveWheel::updateWheel angle := " + angle);
    System.out.println("SwerveWheel::updateWheel motorSpeed := " + motorSpeed);

    swervePID.setSetpoint(angle);
    getDriveMotor().set(motorSpeed);
  }

  public SwervePID getSwervePID() {
    return swervePID;
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
}
