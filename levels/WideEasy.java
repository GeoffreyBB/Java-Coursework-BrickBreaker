package levels;
import collisionDetection.Velocity;
import geometry.Point;
import sprites.Block;
import sprites.Sprite;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * The Wide Easy level.
 */
public class WideEasy implements LevelInformation {

    /**
     * The number of balls in the level.
     * @return the number of balls in the level.
     */
    @Override
    public int numberOfBalls() {
        return 10;
    }

    /**
     * The initial velocity of each ball.
     * @return a list of the initial velocities of each ball.
     */
    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocities = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Velocity vel = new Velocity(-10 + (2 * i), -6);
            velocities.add(vel);
        }
        for (int i = 0; i < 5; i++) {
            Velocity vel = new Velocity((2 * (i + 1)), -6);
            velocities.add(vel);
        }
        return velocities;
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
        return 400;
    }

    /**
     * The name of the level.
     * @return the name of the level.
     */
    @Override
    public String levelName() {
        return "Wide Easy";
    }

    /**
     * The background of the level.
     * @return the background of the level.
     */
    @Override
    public Sprite getBackground() {
        Sprite background = new Sprite() {
            @Override
            public void drawOn(biuoop.DrawSurface d) {
                for (int i = 0; i < 126; i++) {
                    d.setColor(Color.yellow);
                    d.drawLine(125, 125, i * 7, 250);
                }
                d.setColor(Color.LIGHT_GRAY);
                d.drawCircle(125, 125, 75);
                d.fillCircle(125, 125, 75);
                d.setColor(Color.yellow);
                d.drawCircle(125, 125, 65);
                d.fillCircle(125, 125, 65);
                d.setColor(Color.orange);
                d.drawCircle(125, 125, 55);
                d.fillCircle(125, 125, 55);
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
        double blockW = 50.66;
        double blockH = 25;
        double startX = 20;
        double startY = 250;
        List<Block> block = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            Block block1 = new Block(new Point(startX + (i * blockW), startY), blockW, blockH, Color.red);
            block.add(block1);
        }
        for (int i = 2; i < 4; i++) {
            Block block2 = new Block(new Point(startX + (i * blockW), startY), blockW, blockH, Color.orange);
            block.add(block2);
        }
        for (int i = 4; i < 6; i++) {
            Block block3 = new Block(new Point(startX + (i * blockW), startY), blockW, blockH, Color.yellow);
            block.add(block3);
        }
        for (int i = 6; i < 9; i++) {
            Block block4 = new Block(new Point(startX + (i * blockW), startY), blockW, blockH, Color.green);
            block.add(block4);
        }
        for (int i = 9; i < 11; i++) {
            Block block5 = new Block(new Point(startX + (i * blockW), startY), blockW, blockH, Color.BLUE);
            block.add(block5);
        }
        for (int i = 11; i < 13; i++) {
            Block block6 = new Block(new Point(startX + (i * blockW), startY), blockW, blockH, Color.pink);
            block.add(block6);
        }
        for (int i = 13; i < 15; i++) {
            Block block7 = new Block(new Point(startX + (i * blockW), startY), blockW, blockH, Color.cyan);
            block.add(block7);
        }
        return block;
    }

    /**
     * The number of blocks that should be removed before the level is considered to be "cleared".
     * @return the number of blocks that should be removed before the level is considered to be "cleared".
     */
    @Override
    public int numberOfBlocksToRemove() {
        return 15;
    }
}
