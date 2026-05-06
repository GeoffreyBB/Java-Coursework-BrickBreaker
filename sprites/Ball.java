package sprites;
import biuoop.DrawSurface;
import collisionDetection.Velocity;
import game.GameLevel;
import game.GameEnvironment;
import geometry.Line;
import geometry.Point;

/**
 * The Ball class initializes the balls, and preforms the requisite movements of the specific ball.
 */
public class Ball implements Sprite {
    private Point center;
    private int r;
    private java.awt.Color color;
    private Velocity v;
    private GameEnvironment theGame;

    /**
     * This method assigns values to the variables the sprites.Ball object.
     * @param center the centre point of the specific ball.
     * @param r the radius of the specific ball.
     * @param color the colour of the specific ball.
     */
    public Ball(Point center, int r, java.awt.Color color) {
        this.center = center;
        this.r = r;
        this.color = color;
    }

    /**
     * This method assigns values to the variables the sprites.Ball object.
     * @param center the centre point of the specific ball.
     * @param r the radius of the specific ball.
     * @param color the colour of the specific ball.
     * @param v the velocity of the specific ball.
     */
    public Ball(Point center, int r, java.awt.Color color, Velocity v) {
        this.center = center;
        this.r = r;
        this.color = color;
        this.v = v;
    }

    /**
     * This method assigns values to the variables the sprites.Ball object.
     * @param center the centre point of the specific ball.
     * @param r the radius of the specific ball.
     * @param color the colour of the specific ball.
     * @param v the velocity of the specific ball.
     * @param theGame the game environment of the specific ball.
     */
    public Ball(Point center, int r, java.awt.Color color, Velocity v, GameEnvironment theGame) {
        this.center = center;
        this.r = r;
        this.color = color;
        this.v = v;
        this.theGame = theGame;
    }

    /**
     * Constructor method.
     * @param x the x value of the centre of the ball.
     * @param y the y value of the centre of the ball.
     * @param r the radius of the ball.
     * @param color the colour of the ball.
     */
    public Ball(double x, double y, int r, java.awt.Color color) {
        this.center = new Point(x, y);
        this.r = r;
        this.color = color;
    }

    /**
     * This method retrieves the x value of the centre of a given ball.
     * @return the integer value of the x co-ordinate.
     */
    public int getX() {
        return (int) center.getX();
    }

    /**
     * This method retrieves the y value of the centre of a given ball.
     * @return the integer value of the y co-ordinate.
     */
    public int getY() {
        return (int) center.getY();
    }

    /**
     * This method retrieves the radius of a given ball.
     * @return the integer value of the radius.
     */
    public int getSize() {
        return r;
    }

    /**
     * This method retrieves the colour of a given ball.
     * @return the colour of the ball
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    /**
     * This method draws the ball on the given surface.
     * @param surface the "canvas" we are drawing on.
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(java.awt.Color.BLACK);
        surface.drawCircle(getX(), getY(), getSize());
        surface.setColor(getColor());
        surface.fillCircle(getX(), getY(), getSize());
    }

    /**
     * This method assigns the velocity of a given ball to its variable.
     * @param v the velocity of the ball.
     */
    public void setVelocity(Velocity v) {
        this.v = v;
    }

    /**
     * This method assigns the velocity of a given ball to their appropriate variable.
     * @param dx the velocity on the x-axis.
     * @param dy the velocity on the y-axis.
     */
    public void setVelocity(double dx, double dy) {
        this.v = new Velocity(dx, dy);
    }

    /**
     * This method retrieves the velocity of a given ball.
     * @return the velocity of the ball.
     */
    public Velocity getVelocity() {
        return this.v;
    }

    /**
     * This method sets the game environment of a given ball.
     * @param theGame the game environment of the ball.
     */
    public void setGameEnvironment(GameEnvironment theGame) {
        this.theGame = theGame;
    }

    /**
     * This method notifies the moveOneStep method of time passed.
     */
    public void timePassed() {
        this.moveOneStep();
    }

    /**
     * This method adds the ball to the game.
     * @param game the game we are adding the ball to.
     */
    public void addToGame(GameLevel game) {
        game.addSprite(this);
    }

    /**
     * This method removes the ball from the game.
     * @param game the game we are removing the ball from.
     */
    public void removeFromGame(GameLevel game) {
        game.removeSprite(this);
    }

    /**
     * This method moves the ball.
     */
    public void moveOneStep() {
        double centreX = this.center.getX();
        double centreY = this.center.getY();
        double dx = this.v.getDx();
        double dy = this.v.getDy();
        double collisionX, collisionY;
        Velocity newV;
        double epsilon = 0.0000001;
        Line trajectory = new Line(centreX, centreY, centreX + dx, centreY + dy);

        if (this.theGame.getClosestCollision(trajectory) == null) {
            this.center = this.getVelocity().applyToPoint(this.center);
        } else {
            collisionX = this.theGame.getClosestCollision(trajectory).collisionPoint().getX();
            collisionY = this.theGame.getClosestCollision(trajectory).collisionPoint().getY();

            Point collisionPoint = new Point(collisionX, collisionY);
            this.center = new Point(collisionX - epsilon * dx, collisionY - epsilon * dy);
            newV = this.theGame.getClosestCollision(trajectory).collisionObject()
                    .hit(this, collisionPoint, this.getVelocity());
            this.setVelocity(newV.getDx(), newV.getDy());
        }
    }
}
