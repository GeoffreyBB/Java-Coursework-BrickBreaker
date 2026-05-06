package levels;
import biuoop.DrawSurface;
import collisionDetection.Velocity;
import sprites.Block;
import sprites.Sprite;
import geometry.Point;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * The Green 3 level.
 */
public class Green3 implements LevelInformation {
    /**
     * The number of balls in the level.
     * @return the number of balls in the level.
     */
    @Override
    public int numberOfBalls() {
        return 2;
    }

    /**
     * The initial velocity of each ball.
     * @return a list of the initial velocities of each ball.
     */
    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocities = new ArrayList<>();
        for (int i = 0; i < numberOfBalls(); i++) {
            velocities.add(Velocity.fromAngleAndSpeed(new Random().nextInt(90) - 45, 6));
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
        return 100;
    }

    /**
     * The name of the level.
     * @return the name of the level.
     */
    @Override
    public String levelName() {
        return "Green 3";
    }

    /**
     * The background of the level.
     * @return the background of the level.
     */
    @Override
    public Sprite getBackground() {
        Sprite background = new Sprite() {
            @Override
            public void drawOn(DrawSurface d) {
                d.setColor(Color.green);
                d.fillRectangle(0, 0, d.getWidth(), d.getHeight());
                //biggest circle
                d.setColor(Color.yellow);
                d.drawCircle(112, 190, 12);
                d.fillCircle(112, 190, 12);
                //middle circle
                d.setColor(Color.orange);
                d.drawCircle(112, 190, 8);
                d.fillCircle(112, 190, 8);
                //smallest circle
                d.setColor(Color.white);
                d.drawCircle(112, 190, 4);
                d.fillCircle(112, 190, 4);
                //pillar
                d.setColor(Color.darkGray);
                d.drawRectangle(107, 201, 9, 349);
                d.fillRectangle(107, 201, 9, 349);
                //stand
                d.setColor(Color.darkGray);
                d.drawRectangle(100, 350, 25, 50);
                d.fillRectangle(100, 350, 25, 50);
                //building
                d.setColor(Color.white);
                d.drawRectangle(50, 400, 125, 200);
                d.fillRectangle(50, 400, 125, 200);
                //windows
                //edges
                d.setColor(Color.black);
                //top
                d.drawRectangle(50, 400, 125, 12);
                d.fillRectangle(50, 400, 125, 12);
                //left
                d.drawRectangle(50, 400, 12, 200);
                d.fillRectangle(50, 400, 12, 200);
                //right
                d.drawRectangle(163, 400, 12, 200);
                d.fillRectangle(163, 400, 12, 200);
                //frames
                d.setColor(Color.black);
                //horizontal
                d.drawRectangle(50, 444, 125, 7);
                d.fillRectangle(50, 444, 125, 7);
                d.drawRectangle(50, 483, 125, 7);
                d.fillRectangle(50, 483, 125, 7);
                d.drawRectangle(50, 522, 125, 7);
                d.fillRectangle(50, 522, 125, 7);
                d.drawRectangle(50, 561, 125, 7);
                d.fillRectangle(50, 561, 125, 7);
                //vertical
                d.drawRectangle(77, 400, 7, 200);
                d.fillRectangle(77, 400, 7, 200);
                d.drawRectangle(99, 400, 7, 200);
                d.fillRectangle(99, 400, 7, 200);
                d.drawRectangle(121, 400, 7, 200);
                d.fillRectangle(121, 400, 7, 200);
                d.drawRectangle(143, 400, 7, 200);
                d.fillRectangle(143, 400, 7, 200);
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
        int blockW = 48;
        int blockH = 25;
        int startX = 300;
        int startY = 150;

        List<Block> block = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Block block1 = new Block(new Point(startX + (i * blockW), startY), blockW, blockH, Color.gray);
            block.add(block1);
        }
        for (int i = 0; i < 9; i++) {
            Block block2 = new Block(new Point((startX + blockW) + (i * blockW), startY + blockH), blockW, blockH,
                    Color.red);
            block.add(block2);
        }
        for (int i = 0; i < 8; i++) {
            Block block3 = new Block(new Point((startX + (2 * blockW)) + (i * blockW), startY + (2 * blockH)),
                    blockW, blockH, Color.yellow);
            block.add(block3);
        }
        for (int i = 0; i < 7; i++) {
            Block block4 = new Block(new Point((startX + (3 * blockW)) + (i * blockW), startY + (3 * blockH)),
                    blockW, blockH, Color.blue);
            block.add(block4);
        }
        for (int i = 0; i < 6; i++) {
            Block block5 = new Block(new Point((startX + (4 * blockW)) + (i * blockW), startY + (4 * blockH)),
                    blockW, blockH, Color.white);
            block.add(block5);
        }
        return block;
    }

    /**
     * The number of blocks that should be removed before the level is considered to be "cleared".
     * @return the number of blocks that should be removed before the level is considered to be "cleared".
     */
    @Override
    public int numberOfBlocksToRemove() {
        return 40;
    }
}
