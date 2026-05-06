package game;
import collisionDetection.Collidable;
import collisionDetection.CollisionInfo;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import java.util.ArrayList;
import java.util.List;

/**
 * The GameEnvironment class keeps track of all the collidable objects and checks for collisions.
 */
public class GameEnvironment {

    private final List<Collidable> collidables;

    /**
     * This method is a constructor which initializes the collidables list.
     */
    public GameEnvironment() {
        this.collidables = new ArrayList<Collidable>();
    }

    /**
     * This method adds a collidable to the collidables list.
     * @param c the collidable to be added.
     */
    public void addCollidable(Collidable c) {
        this.collidables.add(c);
    }

    /**
     * This method removes a collidable from the collidables list.
     * @param c the collidable to be removed.
     */
    public void removeCollidable(Collidable c) {
        this.collidables.remove(c);
    }

    /**
     * This method checks if the ball is going to collide with any of the collidables in the collidables list.
     * @param trajectory the line that the ball is going to travel.
     * @return the closest collision point.
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        Rectangle rect;
        double x, y;
        double minX = -1;
        double minY = -1;
        Point p;
        int i = 0;

        for (Collidable c : collidables) {
            rect = c.getCollisionRectangle();

            if (!rect.intersectionPoints(trajectory).isEmpty()) {
                x = trajectory.closestIntersectionToStartOfLine(rect).getX();
                y = trajectory.closestIntersectionToStartOfLine(rect).getY();
                p = new Point(x, y);

                if (new Point(minX, minY).equals(new Point(-1, -1))) {
                    minX = x;
                    minY = y;
                    i = collidables.indexOf(c);
                } else if (trajectory.start().distance(new Point(minX, minY)) > trajectory.start().distance(p)) {
                    minX = p.getX();
                    minY = p.getY();
                    i = collidables.indexOf(c);
                }
            }
        }
        if (new Point(minX, minY).equals(new Point(-1, -1))) {
            return null;
        } else {
            return new CollisionInfo(new Point(minX, minY), collidables.get(i));
        }
    }
}
