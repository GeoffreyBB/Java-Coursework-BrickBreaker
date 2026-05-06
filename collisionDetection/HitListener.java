package collisionDetection;
import sprites.Ball;
import sprites.Block;

/**
 * The HitListener interface is used to define the methods that are used by objects that can be collided with.
 */
public interface HitListener {

    /**
     * This method notifies further methods that the ball has come in contact with an object.
     * @param beingHit the block that is being hit.
     * @param hitter the ball that is hitting the block.
     */
    void hitEvent(Block beingHit, Ball hitter);
}
