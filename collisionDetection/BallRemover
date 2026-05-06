package collisionDetection;
import game.GameLevel;
import sprites.Ball;
import sprites.Block;

/**
 * The BallRemover class implements the HitListener interface and deals with removing balls from the game.
 */
public class BallRemover implements HitListener {

    private GameLevel game;
    private Counter remainingBalls;

    /**
     * The constructor method.
     * @param game the game
     * @param remainingBalls the number of remaining balls
     */
    public BallRemover(GameLevel game, Counter remainingBalls) {
        this.game = game;
        this.remainingBalls = remainingBalls;
    }

    /**
     * The hitEvent method removes the ball from the game and decreases the number of remaining balls.
     * @param beingHit the block that is being hit.
     * @param hitter the ball that is hitting the block.
     */
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeFromGame(game);
        this.remainingBalls.decrease(1);
    }
}
