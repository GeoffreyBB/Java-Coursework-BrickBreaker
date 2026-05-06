package collisionDetection;
import geometry.Point;
import geometry.Rectangle;
import sprites.Ball;
/**
 * The Collidable interface is used to define the methods that are used by objects that can be collided with.
 */
public interface Collidable {

    /**
     * This method returns the object the ball collided with.
     * @return the collision object.
     */
    Rectangle getCollisionRectangle();

    /**
     * This method notifies further methods that the ball has come in contact with an object.
     * @param hitter the ball that is hitting the object.
     * @param collisionPoint the point of collision between the ball and the object.
     * @param currentVelocity the current velocity of the ball.
     * @return the new velocity of the ball.
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}
