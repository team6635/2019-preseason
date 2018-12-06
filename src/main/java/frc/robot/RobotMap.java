package frc.robot;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PWMVictorSPX;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.swerve.SwerveDrive;
import frc.robot.swerve.SwerveWheel;

public class RobotMap {
  // Controllers/Encoders:
  // Front left
  public static final SpeedController motorDriveFrontLeft = new PWMVictorSPX(0);
  public static final SpeedController motorPivotFrontLeft = new PWMVictorSPX(1);
  public static final Encoder encoderPivotFrontLeft = new Encoder(0, 1, true, Encoder.EncodingType.k4X);
  // Front right
  public static final SpeedController motorDriveFrontRight = new PWMVictorSPX(2);
  public static final SpeedController motorPivotFrontRight = new PWMVictorSPX(3);
  public static final Encoder encoderPivotFrontRight = new Encoder(2, 3, true, Encoder.EncodingType.k4X);
  // Rear right
  public static final SpeedController motorDriveRearRight = new PWMVictorSPX(4);
  public static final SpeedController motorPivotRearRight = new PWMVictorSPX(5);
  public static final Encoder encoderPivotRearRight = new Encoder(4, 5, true, Encoder.EncodingType.k4X);
  // Rear left
  public static final SpeedController motorDriveRearLeft = new PWMVictorSPX(6);
  public static final SpeedController motorPivotRearLeft = new PWMVictorSPX(7);
  public static final Encoder encoderPivotRearLeft = new Encoder(6, 7, true, Encoder.EncodingType.k4X);

  // Other Devices:
  // Gyro
  public static final AnalogGyro gyro = new AnalogGyro(0);
  // Joysticks
  public final static XboxController xbox1 = new XboxController(0);

  // Swerve Drive:
  // Swerve Wheels
  public static final SwerveWheel wheelFrontLeft = new SwerveWheel(motorDriveFrontLeft, motorPivotFrontLeft, encoderPivotFrontLeft, -10, 12);
  public static final SwerveWheel wheelFrontRight = new SwerveWheel(motorDriveFrontRight, motorPivotFrontRight, encoderPivotFrontRight, 10, 12);
  public static final SwerveWheel wheelRearRight = new SwerveWheel(motorDriveRearRight, motorPivotRearRight, encoderPivotRearRight, 10, -12);
  public static final SwerveWheel wheelRearLeft = new SwerveWheel(motorDriveRearLeft, motorPivotRearLeft, encoderPivotRearLeft, -10, -12);
  // Drivetrain
  public static final SwerveWheel[] wheels = new SwerveWheel[] {
    wheelFrontLeft,
    // wheelFrontRight,
    // wheelRearRight,
    // wheelRearLeft,
  };

  public static final SwerveDrive drive = new SwerveDrive(wheels, gyro);
}
