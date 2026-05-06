package game;
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import collisionDetection.BallRemover;
import collisionDetection.BlockRemover;
import collisionDetection.Collidable;
import collisionDetection.Counter;
import collisionDetection.ScoreTrackingListener;
import collisionDetection.Velocity;
import geometry.Point;
import geometry.Rectangle;
import sprites.Ball;
import sprites.Block;
import sprites.Paddle;
import sprites.Sprite;
import sprites.SpriteCollection;
import sprites.ScoreIndicator;
import levels.LevelInformation;
import java.awt.Color;
import java.util.List;

/**
 * The GameLevel class is in charge of initializing and running a single level.
 */
public class GameLevel implements Animation {
    private final SpriteCollection sprites = new SpriteCollection();
    private final GameEnvironment environment = new GameEnvironment();
    private GUI gui;
    private Counter ballCounter;
    private Counter blockCounter;
    private Counter scoreCounter;
    private AnimationRunner runner;
    private boolean running = false;
    private KeyboardSensor keyboard;

    private LevelInformation levelInfo;
    private String end;


    /**
     * Constructor for the GameLevel class.
     * @param gui the GUI.
     * @param runner the animation runner.
     * @param keyboard the keyboard sensor.
     * @param level the level information.
     */
    public GameLevel(GUI gui, AnimationRunner runner, KeyboardSensor keyboard, LevelInformation level) {
        this.gui = gui;
        this.runner = runner;
        this.keyboard = keyboard;
        this.levelInfo = level;
    }

    /**
     * Constructor for the GameLevel class.
     * @param runner the animation runner.
     * @param keyboard the keyboard sensor.
     * @param scoreCounter the score counter.
     * @param level the level information.
     */
    public GameLevel(AnimationRunner runner, KeyboardSensor keyboard, Counter scoreCounter, LevelInformation level) {
        this.runner = runner;
        this.keyboard = keyboard;
        this.scoreCounter = scoreCounter;
        this.levelInfo = level;
    }

    /**
     * This method adds a collidable to the game environment.
     * @param c the collidable to be added.
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

    /**
     * This method adds a sprite to the game environment.
     * @param s the sprite to be added.
     */
    public void addSprite(Sprite s) {
        sprites.addSprite(s);
    }

    /**
     * This method initializes the game by creating the balls, paddle, blocks, and setting the borders. All of which are
     * added to the game.
     */
    public void initialize() {
        addSprite(levelInfo.getBackground());
        double width = this.runner.guiWidth();
        Block[] borders = borderBlocks();
        for (Block border : borders) {
            border.addToGame(this);
        }

        ballCounter = new Counter();
        BallRemover removeBall = new BallRemover(this, ballCounter);
        borders[2].addHitListener(removeBall); //bottom border to check if ball is below it

        List<Velocity> velocities = levelInfo.initialBallVelocities();
        for (int i = 0; i < levelInfo.numberOfBalls(); i++) {
            Ball ball = new Ball(400, 555, 6, Color.white);
            ball.setGameEnvironment(environment);
            ball.setVelocity(velocities.get(i));
            ballCounter.increase(1);
            ball.addToGame(this);
        }

        blockCounter = new Counter();
        BlockRemover removeBlock = new BlockRemover(this, blockCounter);
        ScoreTrackingListener scoreTrack = new ScoreTrackingListener(scoreCounter);

        for (Block block : this.levelInfo.blocks()) {
            block.addHitListener(removeBlock);
            block.addHitListener(scoreTrack);
            blockCounter.increase(1);
            block.addToGame(this);
        }

        ScoreIndicator score = new ScoreIndicator(scoreCounter, new Rectangle(new Point(0, 0), width, 20),
                Color.white);
        score.addToGame(this);

        Sprite name = new Sprite() {
            @Override
            public void drawOn(DrawSurface d) {
                d.drawText(500, 16, "Level Name: " + levelInfo.levelName(), 15);
            }
            @Override
            public void timePassed() {

            }
        };
        this.addSprite(name);
    }

    /**
     * This method creates the border blocks for the game.
     * @return the array of border blocks.
     */
    public Block[] borderBlocks() {
        double width = this.runner.guiWidth();
        double height = this.runner.guiHeight();

        Block topBorder = new Block(new Point(0, 0), width, 20, Color.lightGray);
        Block leftBorder = new Block(new Point(0, 0), 20, height, Color.lightGray);
        Block bottomBorder = new Block(new Point(0, height - 20), width, 20, Color.lightGray);
        Block rightBorder = new Block(new Point(width - 20, 0), 20, height, Color.lightGray);

        return new Block[] {topBorder, leftBorder, bottomBorder, rightBorder};
    }

    /**
     * This method removes a collidable from the game environment.
     * @param c the collidable to be removed.
     */
    public void removeCollidable(Collidable c) {
        this.environment.removeCollidable(c);
    }

    /**
     * This method removes a sprite from the game environment.
     * @param s the sprite to be removed.
     */
    public void removeSprite(Sprite s) {
        this.sprites.removeSprite(s);
    }

    /**
     * This method determines if the animation should stop.
     * @return true if the animation should stop, false otherwise.
     */
    @Override
    public boolean shouldStop() {
        return !running;
    }

    /**
     * This method runs one frame of the animation.
     * @param d the DrawSurface to draw on.
     */
    @Override
    public void doOneFrame(DrawSurface d) {
        this.sprites.drawAllOn(d);
        this.sprites.notifyAllTimePassed();

        if (ballCounter.getValue() == 0) {
            this.running = false;
            this.end = "l";
        } else if (levelInfo.numberOfBlocksToRemove() <= levelInfo.blocks().size() - blockCounter.getValue()) {
            scoreCounter.increase(100);
            this.running = false;
            this.end = "w";
        }
        if (this.keyboard.isPressed("p")) {
            this.runner.run(new KeyPressStoppableAnimation(this.keyboard, "space", new PauseScreen(keyboard)));
        }
    }

    /**
     * This method creates the balls on top of the paddle.
     */
    public void createBallsOnTopOfPaddle() {
        Paddle paddle = new Paddle(this.runner.getGUI().getKeyboardSensor(), new Rectangle(new Point(350, 560),
                levelInfo.paddleWidth(), 20), this.environment, Color.orange, levelInfo.paddleSpeed());
        paddle.addToGame(this);
    }
    /**
     * This method runs the game by drawing the sprites and notifying them of the time passed.
     */
    public void run() {
        this.createBallsOnTopOfPaddle();
        this.runner.run(new CountdownAnimation(2, 3, this.sprites, this.levelInfo,
                this.runner.getGUI()));
        this.running = true;
        runner.run(this);
    }

    /**
     * This method returns the end status of the game.
     * @return the end status of the game.
     */
    public String end() {
        return end;
    }
}
