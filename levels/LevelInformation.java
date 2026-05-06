package levels;

import collisionDetection.Velocity;
import sprites.Block;
import sprites.Sprite;
import java.util.List;

/**
 * The LevelInformation interface is used to create a level.
 */
public interface LevelInformation {
    /**
     * Stores the number of balls in the level.
     * @return the number of balls in the level
     */
    int numberOfBalls();

    /**
     * The initial velocity of each ball.
     * @return a list of velocities
     */
    List<Velocity> initialBallVelocities();

    /**
     * Stores the speed of the paddle.
     * @return the speed of the paddle
     */
    int paddleSpeed();

    /**
     * Stores the width of the paddle.
     * @return the width of the paddle
     */
    int paddleWidth();

    /**
     * Stores the name of the level.
     * @return the name of the level
     */
    String levelName();

    /**
     * Stores the background of the level.
     * @return the background of the level
     */
    Sprite getBackground();

    /**
     * Stores the blocks of the level.
     * @return a list of blocks
     */
    List<Block> blocks();

    /**
     * Stores the number of blocks to remove.
     * @return the number of blocks to remove
     */
    int numberOfBlocksToRemove();
}
