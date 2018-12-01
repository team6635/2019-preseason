package frc.robot.utils;

import java.util.Date;

public class PID {
  private Long lastTime;
  private double target;
  private double errSum;
  private double lastError;

  private double kp, ki, kd;

  public PID(double P, double I, double D) {
    kp = P;
    ki = I;
    kd = D;
  }

  public void setP(double P) {
    kp = P;
  }

  public void setI(double I) {
    ki = I;
  }

  public void setD(double D) {
    kd = D;
  }

  public void setTarget(double newTarget) {
    target = newTarget;
  }

  public double calculate(double input) {
    Long now = new Date().getTime();
    double timeChange = now - lastTime;
    double error = target - input;
    errSum += (error * timeChange);
    double dErr = (error - lastError) / timeChange;
    double output = kp * error + ki * errSum + kd * dErr;
    lastError = error;
    lastTime = now;

    return output;
  }
}
