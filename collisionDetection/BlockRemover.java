package collisionDetection;
import game.GameLevel;
import sprites.Ball;
import sprites.Block;

/**
 * The BlockRemover class implements the HitListener interface and deals with removing blocks from the game.
 */
public class BlockRemover implements HitListener {
    private GameLevel game;
    private Counter remainingBlocks;

    /**
     * The constructor method.
     * @param game the game
     * @param remainingBlocks the number remaining blocks
     */
    public BlockRemover(GameLevel game, Counter remainingBlocks) {
        this.game = game;
        this.remainingBlocks = remainingBlocks;
    }

    /**
     * The hitEvent method removes the block that is being hit from the game.
     * @param beingHit the block that is being hit.
     * @param hitter the ball that is hitting the block.
     */
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        beingHit.removeFromGame(game);
        beingHit.removeHitListener(this);
        this.remainingBlocks.decrease(1);
    }
}
