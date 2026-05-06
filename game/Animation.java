package game;
import biuoop.DrawSurface;

/**
 * The Animation interface is used to animate objects in the game.
 */
public interface Animation {
    /**
     * This method draws one frame of the animation.
     * @param d the DrawSurface to draw on.
     */
    void doOneFrame(DrawSurface d);

    /**
     * This method returns whether the animation should stop.
     * @return boolean running
     */
    boolean shouldStop();
}
