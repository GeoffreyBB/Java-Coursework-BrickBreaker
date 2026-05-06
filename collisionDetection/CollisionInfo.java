package collisionDetection;
import geometry.Point;

/**
 * The CollisionInfo class is used to define the methods that are used by objects that can be collided with.
 */
public class CollisionInfo {

    private final Point pointCollision;
    private final Collidable objectCollision;

    /**
     * This method initializes the collision info.
     * @param pointCollision the point of collision between the ball and the object.
     * @param objectCollision the object the ball collided with.
     */
    public CollisionInfo(Point pointCollision, Collidable objectCollision) {
        this.pointCollision = pointCollision;
        this.objectCollision = objectCollision;
    }

    /**
     * This method returns the point of collision between the ball and the object.
     * @return the point of collision.
     */
    public Point collisionPoint() {
        return this.pointCollision;
    }

    /**
     * This method returns the object the ball collided with.
     * @return the collision object.
     */
    public Collidable collisionObject() {
        return this.objectCollision;
    }
}
