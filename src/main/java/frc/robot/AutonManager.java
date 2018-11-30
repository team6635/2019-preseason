package frc.robot;

import java.util.Arrays;
import java.util.SortedMap;
import java.util.function.DoubleConsumer;

import edu.wpi.first.wpilibj.Timer;

public class AutonManager {
  private SortedMap<Double, DoubleConsumer> stages;

  public void addStage(double startWhenAfter, DoubleConsumer stageExec) {
    stages.put(startWhenAfter, stageExec);
  }

  public boolean run() {
    DoubleConsumer consumer = null;
    Double[] times = stages.keySet().toArray(new Double[0]);
    Arrays.sort(times);
    for (int i = 0; i < times.length; i++) {
      double time = times[i].doubleValue();
      if (Timer.getMatchTime() > time) {
        consumer = stages.get(time);
      }
    }
    if (consumer == null) {
      return false;
    } else {
      consumer.accept(Timer.getMatchTime());
      return true;
    }
  }
}
