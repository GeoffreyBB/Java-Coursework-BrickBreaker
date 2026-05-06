package game;
import biuoop.KeyboardSensor;
import biuoop.DrawSurface;

/**
 * The KeyPressStoppableAnimation class is used to stop an animation when a key is pressed.
 */
public class KeyPressStoppableAnimation implements Animation {
    private KeyboardSensor ks;
    private String string;
    private Animation animation;
    private boolean running = true;
    private boolean isAlreadyPressed = true;

    /**
     * The constructor for the KeyPressStoppableAnimation class.
     * @param sensor the keyboard sensor
     * @param key the key to stop the animation
     * @param animation the animation to stop
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key, Animation animation) {
        this.ks = sensor;
        this.string = key;
        this.animation = animation;
    }

    /**
     * The doOneFrame method draws one frame of the animation.
     * @param d the DrawSurface to draw on.
     */
    @Override
    public void doOneFrame(DrawSurface d) {
        animation.doOneFrame(d);
        if (ks.isPressed(string)) {
            if (!isAlreadyPressed) {
                running = false;
            }
        } else {
            isAlreadyPressed = false;
        }
    }

    /**
     * The shouldStop method returns whether the animation should stop.
     * @return whether the animation should stop.
     */
    @Override
    public boolean shouldStop() {
        return !running;
    }
}
