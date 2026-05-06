package game;
import biuoop.DrawSurface;
import java.awt.Color;

/**
 * The EndScreen class displays the end screen of the game.
 */
public class EndScreen implements Animation {
    private String string;

    /**
     * The constructor for the EndScreen class.
     * @param string the string to be displayed on the screen.
     */
    public EndScreen(String string) {
        this.string = string;
    }

    /**
     * This method draws the end screen.
     * @param d the DrawSurface to draw on.
     */
    @Override
    public void doOneFrame(DrawSurface d) {
        d.setColor(Color.BLACK);
        d.fillRectangle(0, 0, d.getWidth(), d.getHeight());
        d.setColor(Color.WHITE);
        d.drawText(200, d.getHeight() / 2, this.string, 30);
    }

    /**
     * This method returns whether the animation should stop.
     * @return a boolean value representing whether the animation should stop.
     */
    @Override
    public boolean shouldStop() {
        return false;
    }
}
