package collisionDetection;
import sprites.Ball;
import sprites.Block;

/**
 * The ScoreTrackingListener class implements HitListener, and is used to track the score of the player.
 */
public class ScoreTrackingListener implements HitListener {

    private Counter currentScore;

    /**
     * This is a constructor method.
     * @param scoreCounter the score counter.
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    /**
     * This method is called whenever a block is hit, and increases the score by 5.
     * @param beingHit the block that is being hit.
     * @param hitter the ball that is hitting the block.
     */
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        this.currentScore.increase(5);
    }
}
