package game;
import levels.LevelInformation;
import biuoop.KeyboardSensor;
import collisionDetection.Counter;
import java.util.List;

/**
 * The GameFlow class runs the order of how the game is displayed and played.
 */
public class GameFlow {
    private AnimationRunner ar;
    private KeyboardSensor ks;
    private Counter score = new Counter();

    /**
     * Constructor method.
     * @param ar the animation runner
     * @param ks the keyboard sensor
     * @param score the score counter
     */
    public GameFlow(AnimationRunner ar, KeyboardSensor ks, Counter score) {
        this.ar = ar;
        this.ks = ks;
        this.score = score;
    }

    /**
     * Constructor method.
     * @param ar the animation runner
     * @param ks the keyboard sensor
     */
    public GameFlow(AnimationRunner ar, KeyboardSensor ks) {
        this.ar = ar;
        this.ks = ks;
    }

    /**
     * This method runs the levels in the game.
     * @param levels the list of levels to be run
     */
    public void runLevels(List<LevelInformation> levels) {
        for (LevelInformation levelInfo : levels) {
            GameLevel level = new GameLevel(ar, ks, score, levelInfo);
            level.initialize();
            level.run();
            if (level.end().equals("l")) {
                ar.run(new KeyPressStoppableAnimation(ks, "space",
                        new EndScreen("Game Over, Your score is: " + score.getValue())));
                return;
            }
        }
        ar.run(new KeyPressStoppableAnimation(ks, "space",
                new EndScreen("You Win! Your score is: " + score.getValue())));
    }
}
