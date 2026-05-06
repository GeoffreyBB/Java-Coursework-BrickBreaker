package geometry;

/**
 * The Point class initializes and calculates the relevant values of a specific point.
 */
public class Point {

    private double x;
    private double y;

    /**
     * This method assigns the (x, y) to their respective variables in a given point.
     * @param x the x value
     * @param y the y value
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * This method calculates the distance between two points.
     * @param other the second point (i.e. the "destination")
     * @return a double value of the distance between the two points.
     */
    public double distance(Point other) {
        double xDist = (this.getX() - other.getX()) * (this.getX() - other.getX());
        double yDist = (this.getY() - other.getY()) * (this.getY() - other.getY());
        return Math.sqrt(xDist + yDist);
    }

    /**
     * This method checks if two points are equal to each other (i.e. the same point).
     * @param other the second point we are checking against
     * @return a true or false value regarding if they are the same.
     */
    public boolean equals(Point other) {
        return (this.getX() == other.getX()) && (this.getY() == other.getY());
    }

    /**
     * This method retrieves the x value from a point.
     * @return the double value of the x co-ordinate.
     */
    public double getX() {
        return this.x;
    }

    /**
     * This method retrieves the y value from a point.
     * @return the double value of the y co-ordinate.
     */
    public double getY() {
        return this.y;
    }
}

