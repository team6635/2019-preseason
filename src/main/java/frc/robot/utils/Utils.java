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

  public static void capValuesSymmetrically(double[] arr, double cap) {
    if (arr.length == 0)
      return;

    cap = Math.abs(cap);

    double absoluteMax = getAbsoluteMax(arr);

    if (absoluteMax > cap) {
      for (int i = 0; i < arr.length; i++) {
        if (arr[i] == 0)
          continue; // Don't want any division-by-zero happening
        double coef = arr[i] / Math.abs(arr[i]); // -1 if negative, 1 if positive
        arr[i] = arr[i] / absoluteMax * cap * coef;
      }
    }
  }

  public static double getAbsoluteMax(double[] arr) {
    return Math.max(Math.abs(getMaxDouble(arr)), Math.abs(getMinDouble(arr)));
  }

  public static double getMaxDouble(double[] arr) {
    if (arr.length == 0)
      return Double.NaN;
    double max = arr[0];
    for (int i = 0; i < arr.length; i++) {
      if (arr[i] > max)
        max = arr[i];
    }
    return max;
  }

  public static final double getMinDouble(double[] arr) {
    if (arr.length == 0)
      return Double.NaN;
    double min = arr[0];
    for (int i = 0; i < arr.length; i++) {
      if (arr[i] < min)
        min = arr[i];
    }
    return min;
  }
}
