package game;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * The PauseScreen class, initiated when the player presses the "p" key.
 */
public class PauseScreen implements Animation {
    private KeyboardSensor keyboard;
    private boolean stop = false;

    /**
     * The constructor for the PauseScreen class.
     * @param k the keyboard sensor.
     */
    public PauseScreen(KeyboardSensor k) {
        this.keyboard = k;
    }

    /**
     * The doOneFrame method for the PauseScreen class.
     * @param d the DrawSurface to draw on.
     */
    @Override
    public void doOneFrame(DrawSurface d) {
        d.drawText(135, d.getHeight() / 2, "paused -- press space to continue", 32);
    }

    /**
     * This method returns whether the animation should stop.
     * @return whether the animation should stop.
     */
    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
