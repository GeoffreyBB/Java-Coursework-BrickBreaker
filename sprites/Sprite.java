package sprites;
import biuoop.DrawSurface;

/**
 * The Sprite interface is used to define the methods that are used by objects that can be drawn on the screen.
 */
public interface Sprite {

    /**
     * This method draws the sprite on the screen.
     * @param d the surface to be drawn on.
     */
    void drawOn(DrawSurface d);

    /**
     * This method notifies further methods that time has passed.
     */
    void timePassed();
}
