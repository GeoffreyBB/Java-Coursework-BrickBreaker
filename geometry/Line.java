package geometry;

/**
 * The Line class initializes and calculates the relevant values of a specific line.
 */
public class Line {
    private Point start;
    private Point end;


    /**
     * This method initializes a line using start and end points.
     * @param start the start point of a line.
     * @param end the end point of a line.
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    /**
     * This method initializes a line using the (x, y) co-ordinates of the start and end points.
     * @param x1 the x value of the start point.
     * @param y1 the y value of the start point.
     * @param x2 the x value of the end point.
     * @param y2 the x value of the end point.
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
    }

    /**
     * This method calculates the length of a given line.
     * @return the double value length of a line.
     */
    public double length() {
        return this.start.distance(this.end);
    }

    /**
     * This method calculates the middle point of a given line.
     * @return the middle point of a given line.
     */
    public Point middle() {
        double x = (this.start.getX() + this.end.getX()) / 2;
        double y = (this.start.getY() + this.end.getY()) / 2;
        return new Point(x, y);
    }

    /**
     * This method retrieves the start point of a given line.
     * @return the start point of a given line.
     */
    public Point start() {
        return this.start;
    }

    /**
     * This method retrieves the end point of a given line.
     * @return the end point of a given line.
     */
    public Point end() {
        return this.end;
    }

    /**
     * This method checks if a given line intersects with another line.
     * @param other the line that is checked to see if it intersects with the given line.
     * @return a true or false value regarding whether the lines intersect.
     */
    public boolean isIntersecting(Line other) {
        double xThisMin = Math.min(this.start.getX(), this.end.getX());
        double xThisMax = Math.max(this.start.getX(), this.end.getX());
        double xOtherMin = Math.min(other.start.getX(), other.end.getX());
        double xOtherMax = Math.max(other.start.getX(), other.end.getX());

        //if both lines are vertical
        if ((xThisMin == xThisMax) && (xOtherMin == xOtherMax)) {
            if (xThisMin != xOtherMin) {
                return false;
            } else {
                return this.start.equals(other.start) || this.start.equals(other.end) || this.end.equals(other.start);
            }
            //if the lines aren't vertical
        } else {
            double yThisMin = Math.min(this.start.getY(), this.end.getY());
            double yThisMax = Math.max(this.start.getY(), this.end.getY());
            double yOtherMin = Math.min(other.start.getY(), other.end.getY());
            double yOtherMax = Math.max(other.start.getY(), other.end.getY());

            if ((this.start.getX() == this.end.getX() && ((yThisMin > other.start.getY() && yThisMin > other.end.getY())
                    || (yThisMax < other.start.getY() && yThisMax < other.end.getY())))
                    || (other.start.getX() == other.end.getX() && ((yOtherMin > this.start.getY()
                    && yOtherMin > this.end.getY())
                    || (yOtherMax < this.start.getY() && yOtherMax < this.end.getY())))) {
                return false;
            }
        }

        //the lines do not interact with each other.
        if (((xThisMin < xOtherMin && xThisMax < xOtherMin) && (xThisMin < xOtherMax && xThisMax < xOtherMax))
                || ((xThisMin > xOtherMin && xThisMax > xOtherMin) && (xThisMin > xOtherMax && xThisMax > xOtherMax))) {
            return false;
        } else {
            double xRangeMin = Math.max(xThisMin, xOtherMin);
            double xRangeMax = Math.min(xThisMax, xOtherMax);
            double yThisFromRangeMin = this.yValueX(xRangeMin);
            double yThisFromRangeMax = this.yValueX(xRangeMax);
            double yOtherFromRangeMin = other.yValueX(xRangeMin);
            double yOtherFromRangeMax = other.yValueX(xRangeMax);

            if ((yThisFromRangeMin == yOtherFromRangeMin) && (yThisFromRangeMax == yOtherFromRangeMax)) {
                return xRangeMin == xRangeMax;
            } else if ((yThisFromRangeMin > yOtherFromRangeMin && yThisFromRangeMax > yOtherFromRangeMax)
                    || (yThisFromRangeMin < yOtherFromRangeMin && yThisFromRangeMax < yOtherFromRangeMax)) {
                return false;
            }
            return true;
        }
    }

    /**
     * This method calculates the intersection point of two lines.
     * @param other the line that is being checked to see if it intersects with the given line.
     * @return the point of intersection between the two lines.
     */
    public Point intersectionWith(Line other) {
        //if the lines are equal or do not intersect
        if (equals(other) || !this.isIntersecting(other)) {
            return null;
        } else {
            //if the lines are vertical & intersect
            if ((this.start.getX() == this.end.getX()) && (other.start.getX() == other.end.getX())) {
                if (this.start.equals(other.start) || this.start.equals(other.end)) {
                    return this.start;
                } else {
                    if (this.end.equals(other.start) || this.end.equals(other.end)) {
                        return this.end;
                    }
                }
                //if this line is vertical
            } else if (this.start.getX() == this.end.getX()) {
                double x = this.start.getX();
                double y = other.yValueX(x);
                return new Point(x, y);
                //if the other line is vertical
            } else if (other.start.getX() == other.end.getX()) {
                double x = other.start.getX();
                double y = this.yValueX(x);
                return new Point(x, y);
            }

            //if the lines aren't vertical
            double mThis = this.slope(this.start, this.end);
            double mOther = this.slope(other.start, other.end);

            if (mThis == mOther) {
                if (this.start.equals(other.start) || this.start.equals(other.end)) {
                    return this.start;
                } else if (this.end.equals(other.start) || this.end.equals(other.end)) {
                    return this.end;
                }
            }

            double xStartThis = this.start.getX();
            double yStartThis = this.start.getY();
            double xStartOther = other.start.getX();
            double yStartOther = other.start.getY();
            double intX = ((-mOther * xStartOther) + yStartOther + (mThis * xStartThis) - yStartThis)
                    / (mThis - mOther);
            double intY = this.yValueX(intX);

            return new Point(intX, intY);
        }
    }

    /**
     * This method checks if two lines are equal to each other (i.e. the same line).
     * @param other the line that is being checked against the given line.
     * @return a true or false value regarding whether the lines are equal.
     */
    public boolean equals(Line other) {
        return this.start.equals(other.start) && this.end.equals(other.end);
    }

    /**
     * This method calculates the closest intersection point to the start of the line.
     * @param rect the rectangle that is being checked for intersection points.
     * @return the closest intersection point to the start of the line.
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        if (rect.intersectionPoints(this).isEmpty()) {
            return null;
        } else if (rect.intersectionPoints(this).size() == 1) {
            double x = rect.intersectionPoints(this).get(0).getX();
            double y = rect.intersectionPoints(this).get(0).getY();
            return new Point(x, y);
        } else {
            double x1 = rect.intersectionPoints(this).get(0).getX();
            double y1 = rect.intersectionPoints(this).get(0).getY();
            double x2 = rect.intersectionPoints(this).get(1).getX();
            double y2 = rect.intersectionPoints(this).get(1).getY();
            Point p1 = new Point(x1, y1);
            Point p2 = new Point(x2, y2);
            if (this.start.distance(p1) > this.start.distance(p2)) {
                return p2;
            } else {
                return p1;
            }
        }
    }

    /**
     * This method calculates the y value of a given x value on the line.
     * @param x the x value that is being checked.
     * @return the y value of the given x value on the line.
     */
    private double yValueX(double x) {
        double mY = this.slope(this.start, this.end);
        return this.start.getY() + mY * (x - this.start.getX());
    }

    /**
     * This method calculates the slope of a given line.
     * @param start the start point of a given line.
     * @param end the end point of a given line.
     * @return the slope of a given line.
     */
    public double slope(Point start, Point end) {
        return (end.getY() - start.getY()) / (end.getX() - start.getX());
    }
}
