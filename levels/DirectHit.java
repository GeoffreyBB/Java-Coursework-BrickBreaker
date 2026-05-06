package levels;
import biuoop.DrawSurface;
import collisionDetection.Velocity;
import geometry.Point;
import sprites.Block;
import sprites.Sprite;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * The Direct Hit level.
 */
public class DirectHit implements LevelInformation {

    /**
     * The number of balls in the level.
     * @return the number of balls in the level.
     */
    @Override
    public int numberOfBalls() {
        return 1;
    }

    /**
     * The initial velocity of each ball.
     * @return a list of the initial velocities of each ball.
     */
    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocityList = new ArrayList<>();
        Velocity vel = new Velocity(0, 6);
        velocityList.add(vel);
        return velocityList;
    }

    /**
     * The speed of the paddle.
     * @return the speed of the paddle.
     */
    @Override
    public int paddleSpeed() {
        return 20;
    }

    /**
     * The width of the paddle.
     * @return the width of the paddle.
     */
    @Override
    public int paddleWidth() {
        return 100;
    }

    /**
     * The name of the level.
     * @return the name of the level.
     */
    @Override
    public String levelName() {
        return "Direct Hit";
    }

    /**
     * A sprite with the background of the level.
     * @return the background
     */
    @Override
    public Sprite getBackground() {
        Sprite background = new Sprite() {
            @Override
            public void drawOn(DrawSurface d) {
                d.setColor(Color.BLACK);
                d.fillRectangle(0, 0, d.getWidth(), d.getHeight());
                d.setColor(Color.blue);
                d.drawCircle(400, 175, 150);
                d.drawCircle(400, 175, 100);
                d.drawCircle(400, 175, 50);
                //left line
                d.drawLine(225, 175, 400, 175);
                //right line
                d.drawLine(400, 175, 575, 175);
                //top line
                d.drawLine(400, 0, 400, 150);
                //bottom line
                d.drawLine(400, 150, 400, 340);

            }
            @Override
            public void timePassed() {

            }
        };
        return background;
    }

    /**
     * The blocks that make up this level, each block contains its size, color and location.
     * @return a list of the blocks that make up this level.
     */
    @Override
    public List<Block> blocks() {
        List<Block> block = new ArrayList<>();
        Block block1 = new Block(new Point(375, 150), 50, 50, Color.RED);
        block.add(block1);
        return block;
    }

    /**
     * The number of blocks that should be removed before the level is considered to be "cleared".
     * @return the number of blocks that should be removed before the level is considered to be "cleared".
     */
    @Override
    public int numberOfBlocksToRemove() {
        return 1;
    }
}
