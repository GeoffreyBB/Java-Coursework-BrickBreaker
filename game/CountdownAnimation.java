package game;
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;
import java.awt.Color;
import sprites.SpriteCollection;
import levels.LevelInformation;

/**
 * The CountdownAnimation class is in charge of the countdown before the game starts.
 */
public class CountdownAnimation implements Animation {
    private double numofSeconds;
    private int countFrom;
    private SpriteCollection gameScreen;
    private biuoop.GUI gui;
    private Sleeper sleeper;
    private LevelInformation level;
    private boolean stop;

    /**
     * The constructor of the CountdownAnimation class.
     * @param numOfSeconds the number of seconds the countdown will take.
     * @param countFrom the number to count from.
     * @param gameScreen the game screen.
     * @param level the level information.
     * @param gui the gui.
     */
    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen, LevelInformation level,
                              GUI gui) {
        this.numofSeconds = numOfSeconds;
        this.countFrom = countFrom;
        this.gameScreen = gameScreen;
        this.gui = gui;
        this.sleeper = new Sleeper();
        this.level = level;
        this.stop = false;
    }

    /**
     * This method draws one frame.
     * @param d the DrawSurface to draw on.
     */
    @Override
    public void doOneFrame(DrawSurface d) {
        for (int i = this.countFrom; i > 0; i--) {
            d = this.gui.getDrawSurface();

            this.level.getBackground().drawOn(d);

            this.gameScreen.drawAllOn(d);
            d.setColor(Color.red);
            d.drawText(375, 450, String.valueOf(i), 100); //check the numbers
            this.gui.show(d);
            this.sleeper.sleepFor((long) (1000 * this.numofSeconds / this.countFrom));
        }
        this.stop = true;
    }

    /**
     * This method returns whether the animation should stop or not.
     * @return whether the animation should stop or not.
     */
    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
