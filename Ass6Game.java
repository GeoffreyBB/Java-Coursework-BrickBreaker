import levels.DirectHit;
import levels.WideEasy;
import levels.Green3;
import levels.LevelInformation;
import biuoop.GUI;
import game.AnimationRunner;
import game.GameFlow;
import java.util.ArrayList;
import java.util.List;

/**
 * The Ass6Game class is used to run the game.
 */
public class Ass6Game {
    /**
     * The main method aggregates the necessary levels and runs the game.
     * @param args user input from command line regarding which levels to run.
     */
    public static void main(String[] args) {
        List<LevelInformation> levelList = new ArrayList<>();

        for (String i : args) {
            int integer = 0;
            try {
                integer = Integer.parseInt(i);
            } catch (NumberFormatException e) {
                continue;
            }
            if (integer < 1 || integer > 3) {
                continue;
            }
            switch (integer) {
                case 1 -> levelList.add(new DirectHit());
                case 2 -> levelList.add(new WideEasy());
                case 3 -> levelList.add(new Green3());
                default -> {
                }
            }
        }
        LevelInformation level1 = new DirectHit();
        levelList.add(level1);
        LevelInformation level2 = new WideEasy();
        levelList.add(level2);
        LevelInformation level3 = new Green3();
        levelList.add(level3);

        GUI gameGUI = new GUI("Arkanoid", 800, 600);

        GameFlow gameFlow = new GameFlow(new AnimationRunner(gameGUI, 60), gameGUI.getKeyboardSensor());
        gameFlow.runLevels(levelList);
        gameGUI.close();
    }
}
