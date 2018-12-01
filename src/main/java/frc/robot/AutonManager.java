package frc.robot;

import java.util.Map;

import edu.wpi.first.wpilibj.Timer;
import frc.robot.utils.Utils;

/**
 * The AutonManager class represents a single autonomous implementation.
 * Each AutonManager holds a Map of stages in the auton. Stages are simply
 * lambda functions that take no parameters, and are executed when the time
 * remaining in autonomous mode is greater than the greatest timed stage.
 * 
 * <p> For example, if there was a stage with {@code t = 10} and a stage with
 * {@code t = 12}, and there were 11 seconds remaining in autonomous, the stage
 * with {@code t = 10} would run.
 */
public class AutonManager {
  private Map<Double, Runnable> stages;

  /**
   * Adds a stage to the internal map of registered stages. The stages are automatically 
   * sorted, so there is no required order to add them in; however, for readability,
   * you should add the stages in chronological order.
   * @param startWhenAfter The stage is executed if and only if the remaining auton 
   * time is greater than this parameter.
   * @param stageExec The code to execute. This should be either a void lambda or a void
   * reference to a method using the {@code ::} operator.
   */
  public AutonManager addStage(double startWhenAfter, Runnable stageExec) {
    stages.put(startWhenAfter, stageExec);
    return this;
  }

  /**
   * This should be called by your {@code Robot.autonomousPeriodic()} method.
   * @return A boolean indicating whether a stage was run or not.
   */
  public boolean run() {
    Double[] times = stages.keySet().toArray(new Double[0]);

    double targetTime = Utils.getMaxDoubleBeneathCap(times, Timer.getMatchTime());
    
    if (targetTime == 0) {
      return false;
    } else {
      stages.get(targetTime).run();
      return true;
    }
  }
}
