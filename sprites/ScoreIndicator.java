package sprites;
import biuoop.DrawSurface;
import collisionDetection.Counter;
import game.GameLevel;
import geometry.Rectangle;
import java.awt.Color;

/**
 * The ScoreIndicator class implements the Sprite interface and is used to display the score of the game.
 */
public class ScoreIndicator implements Sprite {

    private Counter score;
    private Rectangle rectangle;
    private java.awt.Color color;

    /**
     * This constructor assigns values to the variables the ScoreIndicator object.
     * @param score the score of the game.
     * @param rectangle the rectangle that the score is displayed on.
     * @param color the colour of the rectangle.
     */
    public ScoreIndicator(Counter score, Rectangle rectangle, Color color) {
        this.score = score;
        this.rectangle = rectangle;
        this.color = color;
    }

    /**
     * This method draws the score on the screen.
     * @param d the surface to be drawn on.
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(this.color);
        d.fillRectangle((int) this.rectangle.getUpperLeft().getX(), (int) this.rectangle.getUpperLeft().getY(),
                (int) this.rectangle.getWidth(), (int) this.rectangle.getHeight());
        d.setColor(Color.BLACK);
        d.drawRectangle((int) this.rectangle.getUpperLeft().getX(), (int) this.rectangle.getUpperLeft().getY(),
                (int) this.rectangle.getWidth(), (int) this.rectangle.getHeight());
        d.drawText(365, 16, (String.format("Score: %d", this.score.getValue())), 15);
    }

    /**
     * This method notifies the score that time has passed.
     */
    @Override
    public void timePassed() {

    }

    /**
     * This method adds the score to the game.
     * @param game the game to be added to.
     */
    public void addToGame(GameLevel game) {
        game.addSprite(this);
    }
}
