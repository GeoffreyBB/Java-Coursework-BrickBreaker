package sprites;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import collisionDetection.Collidable;
import collisionDetection.Velocity;
import game.GameLevel;
import game.GameEnvironment;
import geometry.Point;
import geometry.Rectangle;
import java.awt.Color;

/**
 * The Paddle class initializes the paddle, and preforms the requisite movements of the specific paddle.
 */
public class Paddle implements Collidable, Sprite {
    private final biuoop.KeyboardSensor keyboard;
    private Rectangle paddle;
    private GameEnvironment theGame;
    private Color color;
    private int speed;

    /**
     * The constrcutor method.
     * @param keyboard the keyboard sensor.
     * @param paddle the paddle.
     * @param theGame the game environment.
     * @param color the color of the paddle.
     * @param vel the speed of the paddle.
     */
    public Paddle(biuoop.KeyboardSensor keyboard, Rectangle paddle, GameEnvironment theGame, Color color, int vel) {
        this.keyboard = keyboard;
        this.paddle = paddle;
        this.theGame = theGame;
        this.color = color;
        this.speed = vel;
    }

    /**
     * This method deals with the movement of the paddle to the left.
     */
    public void moveLeft() {
        int left = 10;
        double paddleX = this.paddle.getUpperLeft().getX();
        double paddleY = this.paddle.getUpperLeft().getY();
        double paddleWidth = this.paddle.getWidth();
        double paddleHeight = this.paddle.getHeight();
        if (paddleX > 20) {
            this.paddle = new Rectangle(new Point(paddleX - left, paddleY), paddleWidth, paddleHeight);
        }
    }

    /**
     * This method deals with the movement of the paddle to the right.
     */
    public void moveRight() {
        int right = 10;
        double paddleX = this.paddle.getUpperLeft().getX();
        double paddleY = this.paddle.getUpperLeft().getY();
        double paddleWidth = this.paddle.getWidth();
        double paddleHeight = this.paddle.getHeight();
        if (paddleX < 780 - paddleWidth) {
            this.paddle = new Rectangle(new Point(paddleX + right, paddleY), paddleWidth, paddleHeight);
        }
    }

    /**
     * This method deals with the movement of the paddle according to the keyboard input.
     */
    public void timePassed() {
        if (this.keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            moveLeft();
        }
        if (this.keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            moveRight();
        }
    }

    /**
     * This method draws the paddle on the given DrawSurface.
     * @param d the DrawSurface to be drawn on.
     */
    public void drawOn(DrawSurface d) {
        double paddleX = this.paddle.getUpperLeft().getX();
        double paddleY = this.paddle.getUpperLeft().getY();
        double paddleWidth = this.paddle.getWidth();
        double paddleHeight = this.paddle.getHeight();
        d.setColor(Color.yellow);
        d.fillRectangle((int) paddleX, (int) paddleY, (int) paddleWidth, (int) paddleHeight);
        d.setColor(Color.BLACK);
        d.drawRectangle((int) paddleX, (int) paddleY, (int) paddleWidth, (int) paddleHeight);
    }

    /**
     * This method returns the rectangle of the paddle.
     * @return the rectangle of the paddle.
     */
    public Rectangle getCollisionRectangle() {
        return this.paddle;
    }

    /**
     * This method deals with the movement of the ball after it hits the paddle.
     * @param hitter the ball that hits the paddle.
     * @param collisionPoint the point of collision between the ball and the object.
     * @param currentVelocity the current velocity of the ball.
     * @return the new velocity of the ball.
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        double paddleDx = currentVelocity.getDx();
        double paddleDy = currentVelocity.getDy();
        double collisionX = collisionPoint.getX();
        double collisionY = collisionPoint.getY();
        double paddleW = this.paddle.getWidth();
        double paddleH = this.paddle.getHeight();
        double paddleX = this.paddle.getUpperLeft().getX();
        double paddleY = this.paddle.getUpperLeft().getY();
        double speed = Math.sqrt((paddleDx * paddleDx) + (paddleDy * paddleDy));
        double collisionWidth = this.getCollisionRectangle().getWidth() / 5;

        //region 1
        if ((collisionX >= paddleX) && (collisionX <= paddleX + collisionWidth)) {
            paddleDx = Velocity.fromAngleAndSpeed(300, speed).getDx();
            paddleDy = Velocity.fromAngleAndSpeed(300, speed).getDy();
        //region 2
        } else if ((collisionX >= paddleX + collisionWidth) && (collisionX <= (2 * collisionWidth) + paddleX)) {
            paddleDx = Velocity.fromAngleAndSpeed(330, speed).getDx();
            paddleDy = Velocity.fromAngleAndSpeed(330, speed).getDy();
        //region 3
        } else if ((collisionX >= paddleX + (2 * collisionWidth)) && (collisionX <= paddleX + (3 * collisionWidth))) {
            paddleDy = -paddleDy;
        //region 4
        } else if ((collisionX >= paddleX + (3 * collisionWidth)) && (collisionX <= paddleX + (4 * collisionWidth))) {
            paddleDx = Velocity.fromAngleAndSpeed(30, speed).getDx();
            paddleDy = Velocity.fromAngleAndSpeed(30, speed).getDy();
        } else if ((collisionX >= paddleX + (4 * collisionWidth)) && (collisionX <= paddleX + (5 * collisionWidth))) {
            paddleDx = Velocity.fromAngleAndSpeed(60, speed).getDx();
            paddleDy = Velocity.fromAngleAndSpeed(60, speed).getDy();
        } else if ((collisionX == paddleX && collisionY >= paddleY && collisionY <= paddleY + paddleH)
                || (collisionX == paddleX + paddleW && collisionY >= paddleY && collisionY <= paddleY + paddleH)) {
            paddleDx = -paddleDx;
        }
        return new Velocity(paddleDx, paddleDy);
    }

    /**
     * This method adds the paddle to the game.
     * @param g the game to be added to.
     */
    public void addToGame(GameLevel g) {
        g.addCollidable(this);
        g.addSprite(this);
    }
}
