package sprites;
import biuoop.DrawSurface;
import collisionDetection.Collidable;
import collisionDetection.Velocity;
import collisionDetection.HitListener;
import collisionDetection.HitNotifier;
import game.GameLevel;
import geometry.Point;
import geometry.Rectangle;
import java.awt.Color;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * This class creates blocks, contains methods to alter the velocity of the ball.
 */
public class Block implements Collidable, Sprite, HitNotifier {
    private Rectangle rect;
    private Color color;
    private  final List<HitListener> hitListeners = new LinkedList<>();

    /**
     * Constructor method.
     * @param rect the rectangle of the block
     * @param color the color of the block
     */
    public Block(Rectangle rect, Color color) {
        this.rect = rect;
        this.color = color;
    }

    /**
     * Constructor method.
     * @param rect the rectangle of the block
     */
    public Block(Rectangle rect) {
        this.rect = rect;
    }

    /**
     * This method assigns values to the variables the sprites.Block object.
     * @param upperLeft the upper left point of the specific block.
     * @param width the width of the specific block.
     * @param height the height of the specific block.
     * @param color the colour of the specific block.
     */
    public Block(Point upperLeft, double width, double height, Color color) {
        this.rect = new Rectangle(upperLeft, width, height);
        this.color = color;
    }

    /**
     * This method returns the object the ball collided with.
     * @return the dimensions of the collision object.
     */
    public Rectangle getCollisionRectangle() {
        return this.rect;
    }

    /**
     * This method alters the velocity of the ball when it comes in contact with the block.
     * @param collisionPoint the point of collision between the ball and the block.
     * @param currentVelocity the current velocity of the ball.
     * @return the new velocity of the ball.
     */
    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        double dx = currentVelocity.getDx();
        double dy = currentVelocity.getDy();
        if (collisionPoint.getX() == this.rect.getUpperLeft().getX()) {
            dx = dx * -1; //change to -dx
        } else if (collisionPoint.getX() == this.rect.getUpperLeft().getX() + this.rect.getWidth()) {
            dx = dx * -1; //change to -dx
        } else if (collisionPoint.getY() == this.rect.getUpperLeft().getY()) {
            dy = dy * -1; //change to -dy
        } else if (collisionPoint.getY() == this.rect.getUpperLeft().getY() + this.rect.getHeight()) {
            dy = dy * -1; //change to -dy
        }
        this.notifyHit(hitter);
        return new Velocity(dx, dy);
    }

    /**
     * This method draws the block on the given DrawSurface.
     * @param d the DrawSurface to draw the block on.
     */
    public void drawOn(DrawSurface d) {
        d.setColor(this.color);
        d.fillRectangle((int) this.rect.getUpperLeft().getX(), (int) this.rect.getUpperLeft().getY(),
                (int) this.rect.getWidth(), (int) this.rect.getHeight());
        d.setColor(Color.BLACK);
        d.drawRectangle((int) this.rect.getUpperLeft().getX(), (int) this.rect.getUpperLeft().getY(),
                (int) this.rect.getWidth(), (int) this.rect.getHeight());
    }

    /**
     * This method notifies the block that time has passed.
     */
    public void timePassed() {
        //empty
    }

    /**
     * This method adds the block to the game.
     * @param game the game to add the block to.
     */
    public void addToGame(GameLevel game) {
        game.addSprite(this);
        game.addCollidable(this);
    }

    /**
     * This method removes the block from the game.
     * @param game the game to remove the block from.
     */
    public void removeFromGame(GameLevel game) {
        game.removeSprite(this);
        game.removeCollidable(this);
    }

    /**
     * This method notifies the block that it has been hit.
     * @param hitter the ball that hit the block.
     */
    private void notifyHit(Ball hitter) {
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }

    /**
     * This method adds a hit listener to the block.
     * @param hl the hit listener to add.
     */
    @Override
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    /**
     * This method removes a hit listener from the block.
     * @param hl the hit listener to remove.
     */
    @Override
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }
}
