package frc.robot.swerve;

import java.util.Timer;
import java.util.TimerTask;
import java.util.function.DoubleConsumer;
import java.util.function.DoubleSupplier;

public class SwervePID {
  private double Kp, Ki, Kd;
  private double lastError = 0;
  private double integral;

  private long sampleRate;
  private double setpoint = 0;

  private final Timer timer;
  private boolean isTimerEnabled = false;

  private DoubleSupplier inputSupplier;
  private DoubleConsumer outputConsumer;

  public SwervePID(double P, double I, double D, long sampleRate, DoubleSupplier inputSupplier, DoubleConsumer outputConsumer) {
    setKp(P);
    setKi(I);
    setKd(D);

    this.sampleRate = sampleRate;

    setInputSupplier(inputSupplier);
    setOutputConsumer(outputConsumer);

    timer = new Timer();
    TimerTask task = new TimerTask() {
      @Override
      public void run() {
        if (!isTimerEnabled) {
          return;
        }
        outputConsumer.accept(calculate(inputSupplier.getAsDouble()));
      }
    };

    timer.scheduleAtFixedRate(task, 0L, (long)sampleRate);
  }
  
  public double calculate(double measured) {
    double error = setpoint - measured;
    integral += error * sampleRate;
    double derivative = (error - lastError) / sampleRate;
    lastError = error;
    double output = Kp * error + Ki * integral + Kd * derivative;

    return output;
  }

  public void enable() {
    isTimerEnabled = true;
  }

  public void disable() {
    isTimerEnabled = false;
  }

  /**
   * @return the outputConsumer
   */
  public DoubleConsumer getOutputConsumer() {
    return outputConsumer;
  }

  /**
   * @param outputConsumer the outputConsumer to set
   */
  public void setOutputConsumer(DoubleConsumer outputConsumer) {
    this.outputConsumer = outputConsumer;
  }

  /**
   * @return the inputSupplier
   */
  public DoubleSupplier getInputSupplier() {
    return inputSupplier;
  }

  /**
   * @param inputSupplier the inputSupplier to set
   */
  public void setInputSupplier(DoubleSupplier inputSupplier) {
    this.inputSupplier = inputSupplier;
  }

  /**
   * @return the kp
   */
  public double getKp() {
    return Kp;
  }

  /**
   * @return the setpoint
   */
  public double getSetpoint() {
    return setpoint;
  }

  /**
   * @param setpoint the setpoint to set
   */
  public void setSetpoint(double setpoint) {
    this.setpoint = setpoint;
  }

  /**
   * @return the sampleRate
   */
  public long getSampleRate() {
    return sampleRate;
  }

  /**
   * @return the kd
   */
  public double getKd() {
    return Kd;
  }

  /**
   * @param kd the kd to set
   */
  public void setKd(double kd) {
    this.Kd = kd;
  }

  /**
   * @return the ki
   */
  public double getKi() {
    return Ki;
  }

  /**
   * @param ki the ki to set
   */
  public void setKi(double ki) {
    this.Ki = ki;
  }

  /**
   * @param kp the kp to set
   */
  public void setKp(double kp) {
    this.Kp = kp;
  }
}
