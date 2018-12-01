package frc.robot.utils;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.function.DoubleConsumer;
import java.util.function.DoubleSupplier;

public class PIDThread {
  private PID pid;
  private DoubleSupplier supplier;
  private DoubleConsumer consumer;
  private boolean isRunning = false;

  private ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);;

  public PIDThread(double P, double I, double D, DoubleSupplier supplier, DoubleConsumer consumer) {
    executor.scheduleAtFixedRate(this::threadRun, 0, 1000 / 60, TimeUnit.MILLISECONDS);
    
    new Thread(this::threadRun);
    pid = new PID(P, I, D);
    this.supplier = supplier;
    this.consumer = consumer;
  }

  protected void threadRun() {
    if (!this.isRunning)
      return;
    consumer.accept(pid.calculate(supplier.getAsDouble()));
  }

  public void startThread() {
    isRunning = true;
  }

  public void stopThread() {
    isRunning = false;
  }

  public PID getPid() {
    return pid;
  }
}
