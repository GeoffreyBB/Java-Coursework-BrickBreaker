package geometry;
import java.util.ArrayList;
import java.util.List;

/**
 * The Rectangle class creates the rectangle objects that are used in the game.
 */
public class Rectangle {
    private Point upperLeft;
    private double width;
    private double height;

    /**
     * This method is a constructor which initializes the rectangle.
     * @param upperLeft the upper left point of the rectangle.
     * @param width the width of the rectangle.
     * @param height the height of the rectangle.
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
    }

    /**
     * This method returns the width of the rectangle.
     * @return the width of the rectangle.
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * This method returns the height of the rectangle.
     * @return the height of the rectangle.
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * This method returns the upper left point of the rectangle.
     * @return the upper left point of the rectangle.
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }

    /**
     * This method checks if a line intersects with the rectangle.
     * @param line the line to check.
     * @return the list of intersection points.
     */
    public java.util.List<Point> intersectionPoints(Line line) {
        List<Point> intersections = new ArrayList<Point>();
        double x = upperLeft.getX();
        double y = upperLeft.getY();

        Line[] lines = new Line[4];

        //top, bottom, left, right
        lines[0] = new Line(upperLeft, new Point(x + this.width, y));
        lines[1] = new Line(new Point(x, y + this.height), new Point(x + this.width, y + this.height));
        lines[2] = new Line(upperLeft, new Point(x, y + this.height));
        lines[3] = new Line(new Point(x + this.width, y), new Point(x + this.width, y + this.height));

        for (int i = 0; i < 4; i++) {
            if (lines[i].isIntersecting(line)) {
                Point p = new Point(lines[i].intersectionWith(line).getX(), lines[i].intersectionWith(line).getY());
                if (!this.containsPoint(p, intersections)) {
                    intersections.add(p);
                }
            }
        }
        return intersections;
    }

    /**
     * This method checks if a point is contained in a list of points.
     * @param p the point to check.
     * @param points the list of points to check.
     * @return true if the point is contained in the list, false otherwise.
     */
    private boolean containsPoint(Point p, List<Point> points) {
        boolean contains = false;
        if (points.isEmpty()) {
            return false;
        } else {
            for (int i = 0; i < points.size(); ++i) { //i++ or ++i
                if (points.get(i).equals(p)) {
                    contains = true;
                    return contains;
                }
            }
            return contains;
        }
    }
}
