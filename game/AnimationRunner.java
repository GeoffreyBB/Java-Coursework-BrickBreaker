package game;
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

/**
 * The AnimationRunner class is in charge of running a single animation.
 */
public class AnimationRunner {
    private GUI gui;
    private int framesPerSecond;
    private Sleeper sleeper = new Sleeper();

    /**
     * The constructor for the AnimationRunner class.
     * @param gui the GUI to be used.
     * @param framesPerSecond the number of frames per second.
     */
    public AnimationRunner(GUI gui, int framesPerSecond) {
        this.gui = gui;
        this.framesPerSecond = framesPerSecond;
    }

    /**
     * This method runs the animation.
     * @param animation the animation to be run.
     */
    public void run(Animation animation) {
        int millisecondsPerFrame = 60;
        while (!animation.shouldStop()) {
            long startTime = System.currentTimeMillis();
            DrawSurface d = gui.getDrawSurface();

            animation.doOneFrame(d);

            gui.show(d);
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                this.sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }

    /**
     * This method retrieves the width of the GUI.
     * @return the width of the GUI.
     */
    public int guiWidth() {
        return this.gui.getDrawSurface().getWidth();
    }

    /**
     * This method retrieves the height of the GUI.
     * @return the height of the GUI.
     */
    public int guiHeight() {
        return this.gui.getDrawSurface().getHeight();
    }

    /**
     * This method retrieves the GUI.
     * @return the GUI.
     */
    public GUI getGUI() {
        return this.gui;
    }
}
