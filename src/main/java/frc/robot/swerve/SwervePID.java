package frc.robot.swerve;

import java.util.Timer;
import java.util.TimerTask;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public abstract class SwervePID {
  protected double Kp, Ki, Kd, tolerance;
  protected long rate;
  protected double setpoint = 0;
  protected Timer timer;

  protected int cyclesSinceZero = 0;
  protected double lastError = 0;

  protected boolean isEnabled = false;
  
  public SwervePID(double Kp, double Ki, double Kd, long rate, double tolerance) {
    this.Kp = Kp;
    this.Ki = Ki;
    this.Kd = Kd;
    this.rate = rate;
    this.tolerance = tolerance;

    timer = new Timer();
    TimerTask task = new TimerTask() {
      @Override
      public void run() {
        if (!isEnabled)
          return;
        pidUseOutput(calculate(pidInputProvider()));
      }
    };
    timer.scheduleAtFixedRate(task, 1, rate);
  }
  
  public void setSetpoint(double setpoint) {
    this.setpoint = setpoint;
  }

  protected double calculate(double measured) {
    SmartDashboard.putNumber("Measured", measured);    
    SmartDashboard.putNumber("Setpoint", setpoint);

    double sError = setpoint - measured;
    double error;

    if (sError > 180) {
      error = sError - 360;
    } else if (sError < -180) {
      error = sError + 360;
    } else {
      error = sError;
    }

    // if (Math.abs(error) >= 90 && Math.abs(error) <= 180) {
    //   error = error + 180 % 360;
    // }
    
    SmartDashboard.putNumber("Error", error);
    
    if (Math.abs(error) <= tolerance) {
      cyclesSinceZero = 0;
      return 0;
    } else {
      cyclesSinceZero++;
    }

    double p = Kp * error;
    double i = Ki * (error * cyclesSinceZero);
    double d = Kd * (lastError - error / rate);

    lastError = error;
    
    SmartDashboard.putNumber("p", p);
    SmartDashboard.putNumber("i", i);
    SmartDashboard.putNumber("d", d);

    return -(p + i + d);
  }

  public abstract double pidInputProvider();

  public abstract void pidUseOutput(double output);

  public double getTolerance() {
    return tolerance;
  }

  public void setTolerance(double tolerance) {
    this.tolerance = tolerance;
  }

  public void setEnabled(boolean value) {
    isEnabled = value;
  }
}
