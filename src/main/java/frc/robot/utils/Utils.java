package frc.robot.utils;

public final class Utils {
  public static double getMaxDoubleBeneathCap(Double[] arr, Double cap) {
    double max = 0;
    for (int i = 0; i < arr.length; i++) {
      if (arr[i] > max && arr[i] < cap) {
        max = arr[i];
      }
    }
    return max;
  }
}
