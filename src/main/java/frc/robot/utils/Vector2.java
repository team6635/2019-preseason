package frc.robot.utils;

public final class Vector2 {
  public double x;
  public double y;

  public Vector2(double vx, double vy) {
    x = vx;
    y = vy;
  }

  public double hypot() {
    return Math.hypot(x, y);
  }
}
