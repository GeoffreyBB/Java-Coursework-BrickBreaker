package collisionDetection;
import geometry.Point;

/**
 * The collisionDetection.Velocity class calculates the velocity of a given ball.
 */
public class Velocity {
    private double dx;
    private double dy;

    /**
     * Assigned the distance moved by the ball, on the x-axis and y-axis, to their appropriate variables.
     * @param dx the distance covered on the x-axis.
     * @param dy the distance covered on the y-axis.
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * This method retrieves the dx variable.
     * @return the dx variable
     */
    public double getDx() {
        return this.dx;
    }

    /**
     * This method retrieves the dy variable.
     * @return the dy variable.
     */
    public double getDy() {
        return this.dy;
    }

    /**
     * This method calulates the new point of a ball based on the current point, and the dx, dy values.
     * @param p the point that will be moved.
     * @return the new point, that has since been moved.
     */
    public Point applyToPoint(Point p) {
        return new Point(p.getX() + dx, p.getY() + dy);
    }

    /**
     * This method calculates the speed of a given ball based on the angle and speed of the ball.
     * @param angle the angle the ball is moving at.
     * @param speed the speed the ball is moving at.
     * @return a new velocity based on the variables above.
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double dx = speed * Math.sin(Math.toRadians(angle));
        double dy = -1 * (speed * Math.cos(Math.toRadians(angle)));
        return new Velocity(dx, dy);
    }
}
